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

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.google.gson.JsonObject;
import com.ltu.secret.action.AbstractSecretAction;
import com.ltu.secret.configuration.ExceptionMessages;
import com.ltu.secret.dao.factory.DAOFactory;
import com.ltu.secret.exception.BadRequestException;
import com.ltu.secret.exception.DAOException;
import com.ltu.secret.exception.InternalErrorException;
import com.ltu.secret.model.action.user.UpdateSecretKeyRequest;
import com.ltu.secret.model.action.user.UserResponse;
import com.ltu.secret.model.user.User;
import com.ltu.secret.model.user.UserDAO;


/**
 * Action that creates a new User
 * <p/>
 * POST to /pets/
 */
public class UpdateSecretKeyAction extends AbstractSecretAction{
	private LambdaLogger logger;

    @Override
	public String handle(JsonObject request, Context lambdaContext) throws BadRequestException, InternalErrorException {
        logger = lambdaContext.getLogger();

        logger.log("Calling UpdateSecretKeyAction...");
        UpdateSecretKeyRequest input = getGson().fromJson(request, UpdateSecretKeyRequest.class);
        
        if (input == null ||
                input.getUserId() == null ||
                input.getUserId().trim().equals("")) {
            throw new BadRequestException(ExceptionMessages.EX_INVALID_INPUT);
        }

        if (input == null ||
                input.getSecretKey() == null ||
                input.getSecretKey().trim().equals("")) {
            throw new BadRequestException(ExceptionMessages.EX_INVALID_INPUT);
        }
        
        UserDAO dao = DAOFactory.getUserDAO();
        User user = dao.find(input.getUserId());
        
        if (user == null) {
        	throw new BadRequestException(ExceptionMessages.EX_USER_NOT_FOUND);
		}
        
        try {
        	user.setSecretKey(input.getSecretKey());
        	dao.update(user);
        } catch (final DAOException e) {
            logger.log("Error while updating user\n" + e.getMessage());
            throw new InternalErrorException(ExceptionMessages.EX_DAO_ERROR);
        } 

        if (user.getId() == null || user.getId().trim().equals("")) {
            logger.log("UserID is null or empty");
            throw new InternalErrorException(ExceptionMessages.EX_DAO_ERROR);
        }

        UserResponse output = new UserResponse();
        output.setItem(user);

        return getGson().toJson(output);
    }
    
}
