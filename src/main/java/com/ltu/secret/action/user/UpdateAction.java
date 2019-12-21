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
import com.ltu.secret.model.action.user.UpdateUserRequest;
import com.ltu.secret.model.action.user.UserResponse;
import com.ltu.secret.model.user.User;
import com.ltu.secret.model.user.UserDAO;

/**
 * The Class UpdateAction.
 *
 * @author uyphu
 * @date Jun 11, 2017
 */
public class UpdateAction extends AbstractSecretAction{
	
	/** The logger. */
	private LambdaLogger logger;

    @Override
	public String handle(JsonObject request, Context lambdaContext) throws BadRequestException, InternalErrorException {
        logger = lambdaContext.getLogger();

        UpdateUserRequest input = getGson().fromJson(request, UpdateUserRequest.class);

        if (input == null ||
                input.getId() == null ||
                input.getId().trim().equals("")) {
            throw new BadRequestException(ExceptionMessages.EX_INVALID_INPUT);
        }
        
        if (input == null ||
                input.getDisplayName() == null ||
                input.getDisplayName().trim().equals("")) {
            throw new BadRequestException(ExceptionMessages.EX_INVALID_INPUT);
        }

        UserDAO dao = DAOFactory.getUserDAO();

        User updateUser;
		

        try {
        	updateUser = dao.find(input.getId());
        	updateUser.setDisplayName(input.getDisplayName());
            updateUser.setImageUrl(input.getImageUrl());
        	updateUser = dao.update(updateUser);
        } catch (final DAOException e) {
            logger.log("Error while creating new device\n" + e.getMessage());
            throw new InternalErrorException(ExceptionMessages.EX_DAO_ERROR);
        }

        if (updateUser.getId() == null || updateUser.getId().trim().equals("")) {
            logger.log("UserID is null or empty");
            throw new InternalErrorException(ExceptionMessages.EX_DAO_ERROR);
        }

        UserResponse output = new UserResponse();
        output.setItem(updateUser);

        return getGson().toJson(output);
    }

}