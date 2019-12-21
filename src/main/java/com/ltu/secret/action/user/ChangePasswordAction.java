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
import com.ltu.secret.auth.Token;
import com.ltu.secret.auth.TokenProvider;
import com.ltu.secret.configuration.ExceptionMessages;
import com.ltu.secret.dao.factory.DAOFactory;
import com.ltu.secret.exception.BadRequestException;
import com.ltu.secret.exception.DAOException;
import com.ltu.secret.exception.InternalErrorException;
import com.ltu.secret.helper.PasswordHelper;
import com.ltu.secret.model.action.user.ChangePasswordRequest;
import com.ltu.secret.model.action.user.LoginUserResponse;
import com.ltu.secret.model.user.User;
import com.ltu.secret.model.user.UserDAO;


/**
 * The Class ChangePasswordAction.
 *
 * @author uyphu
 * @date Jun 11, 2017
 */
public class ChangePasswordAction extends AbstractSecretAction{
	
	/** The logger. */
	private LambdaLogger logger;

    @Override
	public String handle(JsonObject request, Context lambdaContext) throws BadRequestException, InternalErrorException {
        logger = lambdaContext.getLogger();
        
        ChangePasswordRequest input = getGson().fromJson(request, ChangePasswordRequest.class);
        validateInput(input);
        
        LoginUserResponse output = changePassword(input);
        return getGson().toJson(output);
    }

	private void validateInput(ChangePasswordRequest input) throws BadRequestException {
		if (input == null) {
            throw new BadRequestException(ExceptionMessages.EX_INVALID_INPUT);
        }
        
        if (input.getEmail() == null ||
                input.getEmail().trim().equals("")) {
            throw new BadRequestException(ExceptionMessages.EX_PARAM_EMAIL_REQUIRED);
        }
        
        if (input.getPassword() == null ||
                input.getPassword().trim().equals("")) {
            throw new BadRequestException(ExceptionMessages.EX_PARAM_PASSWORD_REQUIRED);
        }
        
        if (input.getChangeKey() == null ||
                input.getChangeKey().trim().equals("")) {
            throw new BadRequestException(ExceptionMessages.EX_PARAM_CHANGE_KEY_REQUIRED);
        }
	}
    
    private LoginUserResponse changePassword(ChangePasswordRequest input) throws BadRequestException, InternalErrorException {
    	UserDAO dao = DAOFactory.getUserDAO();
		User user = dao.findByEmail(input.getEmail());
		try {
			if (user == null) {
				throw new BadRequestException(ExceptionMessages.EX_USER_NOT_FOUND);
			}
			if (!input.getChangeKey().equals(user.getActivateCode())) {
				throw new BadRequestException(ExceptionMessages.EX_CHANGE_KEY_INVALID);
			}
			
			byte[] salt = PasswordHelper.generateSalt();
            byte[] encryptedPassword = PasswordHelper.getEncryptedPassword(input.getPassword(), salt);
            user.setPassword(ByteBuffer.wrap(encryptedPassword));
            user.setSalt(ByteBuffer.wrap(salt));
            user.setActivateCode(null);
            dao.update(user);
            
            TokenProvider provider = TokenProvider.getInstance();
            Token token = provider.createToken(user);
        	LoginUserResponse output = new LoginUserResponse();
        	output.setExpires(token.getExpires());
        	output.setToken(token.getToken());
        	output.setType(token.getType());
        	output.setItem(user);
        	return output;
			
		} catch (final NoSuchAlgorithmException e) {
            logger.log("No algrithm found for password encryption\n" + e.getMessage());
            throw new InternalErrorException(ExceptionMessages.EX_PWD_SALT);
        } catch (final InvalidKeySpecException e) {
            logger.log("No KeySpec found for password encryption\n" + e.getMessage());
            throw new InternalErrorException(ExceptionMessages.EX_PWD_ENCRYPT);
        } catch (final DAOException e) {
        	logger.log("Error changing password " + e.getMessage());
			throw new InternalErrorException(ExceptionMessages.EX_CHANGE_PASSWORD);
		} 

	}

}

