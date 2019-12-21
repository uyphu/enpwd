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
import com.ltu.secret.exception.BadRequestException;
import com.ltu.secret.exception.DAOException;
import com.ltu.secret.exception.InternalErrorException;
import com.ltu.secret.helper.PasswordHelper;
import com.ltu.secret.model.action.user.InsertUserRequest;
import com.ltu.secret.model.action.user.UserResponse;
import com.ltu.secret.model.user.User;
import com.ltu.secret.model.user.UserDAO;
import com.ltu.secret.utils.RandomUtil;

/**
 * Action that creates a new User.
 *
 * @author uyphu
 * created on May 22, 2017
 */
public class InsertAction extends AbstractSecretAction {
	
	/** The logger. */
	private LambdaLogger logger;

    @Override
	public String handle(JsonObject request, Context lambdaContext) throws BadRequestException, InternalErrorException {
        logger = lambdaContext.getLogger();

        InsertUserRequest input = getGson().fromJson(request, InsertUserRequest.class);

        if (input == null ||
                input.getEmail() == null ||
                input.getEmail().trim().equals("")) {
            throw new BadRequestException(ExceptionMessages.EX_INVALID_INPUT);
        }
        
        if (input == null ||
                input.getPassword() == null ||
                input.getPassword().trim().equals("")) {
            throw new BadRequestException(ExceptionMessages.EX_INVALID_INPUT);
        }
        
        if (input == null ||
                input.getDisplayName() == null ||
                input.getDisplayName().trim().equals("")) {
            throw new BadRequestException(ExceptionMessages.EX_INVALID_INPUT);
        }

        UserDAO dao = DAOFactory.getUserDAO();

        User newUser = new User();
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
        	newUser = dao.insert(newUser);
        } catch (final DAOException e) {
            logger.log("Error while creating new device\n" + e.getMessage());
            throw new InternalErrorException(ExceptionMessages.EX_DAO_ERROR);
        }

        if (newUser.getId() == null || newUser.getId().trim().equals("")) {
            logger.log("UserID is null or empty");
            throw new InternalErrorException(ExceptionMessages.EX_DAO_ERROR);
        }

        UserResponse output = new UserResponse();
        output.setItem(newUser);

        return getGson().toJson(output);
    }

}
