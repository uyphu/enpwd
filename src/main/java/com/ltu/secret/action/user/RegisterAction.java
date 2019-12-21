/*
 * Copyright 2017 ltu.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file except in compliance
 * with the License. A copy of the License is located at
 *
 * http://ltu.com/apache2.0/
 *
 * or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES
 * OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package com.ltu.secret.action.user;

import java.nio.ByteBuffer;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.google.gson.JsonObject;
import com.ltu.secret.action.AbstractSecretAction;
import com.ltu.secret.configuration.ExceptionMessages;
import com.ltu.secret.constants.Constants;
import com.ltu.secret.dao.factory.DAOFactory;
import com.ltu.secret.exception.AuthorizationException;
import com.ltu.secret.exception.BadRequestException;
import com.ltu.secret.exception.CommonException;
import com.ltu.secret.exception.DAOException;
import com.ltu.secret.exception.InternalErrorException;
import com.ltu.secret.helper.PasswordHelper;
import com.ltu.secret.model.action.user.RegisterUserRequest;
import com.ltu.secret.model.action.user.RegisterUserResponse;
import com.ltu.secret.model.user.User;
import com.ltu.secret.model.user.UserDAO;
import com.ltu.secret.model.user.UserIdentity;
import com.ltu.secret.provider.CredentialsProvider;
import com.ltu.secret.provider.ProviderFactory;
import com.ltu.secret.utils.AppUtil;
import com.ltu.secret.utils.MailUtil;
import com.ltu.secret.utils.RandomUtil;

/**
 * The Class RegisterAction.
 *
 * @author uyphu
 * @date Jun 11, 2017
 */
public class RegisterAction extends AbstractSecretAction {
	
	/** The logger. */
	private LambdaLogger logger;
	
	/** The cognito. */
	private CredentialsProvider cognito = ProviderFactory.getCredentialsProvider();

    @Override
	public String handle(JsonObject request, Context lambdaContext) throws BadRequestException, InternalErrorException {
        logger = lambdaContext.getLogger();
        
        RegisterUserRequest input = getGson().fromJson(request, RegisterUserRequest.class);
        logger.log("RegisterAction calling ... ");
        if (input == null ||
                input.getEmail() == null ||
                input.getEmail().trim().equals("")) {
            throw new BadRequestException(ExceptionMessages.EX_PARAM_EMAIL_REQUIRED);
        }
        
        if (input == null ||
                input.getPassword() == null ||
                input.getPassword().trim().equals("")) {
            throw new BadRequestException(ExceptionMessages.EX_PARAM_PASSWORD_REQUIRED);
        }
        
        if (input == null ||
                input.getDisplayName() == null ||
                input.getDisplayName().trim().equals("")) {
            throw new BadRequestException(ExceptionMessages.EX_PARAM_DISPLAYED_NAME_REQUIRED);
        }
        
        if (!AppUtil.validateEmail(input.getEmail())) {
            throw new BadRequestException(ExceptionMessages.EX_PARAM_EMAIL_INVALID);
        }

        UserDAO dao = DAOFactory.getUserDAO();
        User newUser = dao.findByEmail(input.getEmail());
        
        if (newUser != null && Constants.YES_STATUS.equals(newUser.getStatus())){
            throw new InternalErrorException(ExceptionMessages.EX_EMAIL_EXIST);
		} 

        if (newUser == null) {
        	newUser = new User();
		}
        newUser.setEmail(input.getEmail());
        try {
            byte[] salt = PasswordHelper.generateSalt();
            byte[] encryptedPassword = PasswordHelper.getEncryptedPassword(input.getPassword(), salt);
            newUser.setPassword(ByteBuffer.wrap(encryptedPassword));
            newUser.setSalt(ByteBuffer.wrap(salt));
        } catch (final NoSuchAlgorithmException e) {
            logger.log("No algrithm found for password encryption\n" + e.getMessage());
            throw new InternalErrorException(ExceptionMessages.EX_PWD_SALT);
        } catch (final InvalidKeySpecException e) {
            logger.log("No KeySpec found for password encryption\n" + e.getMessage());
            throw new InternalErrorException(ExceptionMessages.EX_PWD_ENCRYPT);
        }
        
        newUser.setDisplayName(input.getDisplayName());
        newUser.setType(Constants.USER_TYPE);
        newUser.setActivateCode(RandomUtil.generateActivationKey(Constants.NUM_COUNT));
        newUser.setStatus(Constants.PENDING_STATUS);
        newUser.setImageUrl(input.getImageUrl());
        try {
        	UserIdentity identity;

        	identity = cognito.getUserIdentity(newUser);

            if (identity == null || identity.getIdentityId() == null || identity.getIdentityId().trim().equals("")) {
                logger.log("Could not load Cognito identity ");
                throw new InternalErrorException(ExceptionMessages.EX_NO_COGNITO_IDENTITY);
            }
            newUser.setIdentity(identity);
            
        	newUser = dao.merge(newUser);
        	MailUtil.sendActivateEmail(newUser.getEmail(), newUser.getActivateCode());
        } catch (final DAOException e) {
            logger.log("Error while creating new device\n" + e.getMessage());
            throw new InternalErrorException(ExceptionMessages.EX_DAO_ERROR);
        } catch (CommonException e) {
        	logger.log("Error while creating new device\n" + e.getMessage());
        	//FIXME PhuLTU need to handle transaction here
        	deleteUser(newUser);
            throw new InternalErrorException(ExceptionMessages.EX_SEND_MAIL_ERROR);
		} catch (final AuthorizationException e) {
            logger.log("Error while accessing Cognito\n" + e.getMessage());
            throw new InternalErrorException(ExceptionMessages.EX_NO_COGNITO_IDENTITY);
        }

        if (newUser.getId() == null || newUser.getId().trim().equals("")) {
            logger.log("UserID is null or empty");
            throw new InternalErrorException(ExceptionMessages.EX_DAO_ERROR);
        }

        RegisterUserResponse output = new RegisterUserResponse();
        output.setItem(newUser);
        return getGson().toJson(output);
    }
    
    /**
     * Delete user.
     *
     * @param user the user
     */
    private void deleteUser(User user) {
    	try {
    		UserDAO dao = DAOFactory.getUserDAO();
			dao.delete(user.getId());
		} catch (DAOException e1) {
			logger.log("Error deleting new device\n" + e1.getMessage());
		}
    }
    
}

