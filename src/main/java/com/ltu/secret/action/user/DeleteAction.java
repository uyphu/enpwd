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
import com.ltu.secret.model.action.user.DeleteUserRequest;
import com.ltu.secret.model.action.user.DeleteUserResponse;
import com.ltu.secret.model.user.UserDAO;

/**
 * The Class DeleteAction.
 *
 * @author uyphu
 * @date Jun 11, 2017
 */
public class DeleteAction extends AbstractSecretAction{
	private LambdaLogger logger;

    @Override
	public String handle(JsonObject request, Context lambdaContext) throws BadRequestException, InternalErrorException {
        logger = lambdaContext.getLogger();

        DeleteUserRequest input = getGson().fromJson(request, DeleteUserRequest.class);

        if (input == null ||
                input.getId() == null ||
                input.getId().trim().equals("")) {
            throw new BadRequestException(ExceptionMessages.EX_INVALID_INPUT);
        }

        UserDAO dao = DAOFactory.getUserDAO();

        try {
        	dao.delete(input.getId());
        } catch (final DAOException e) {
            logger.log("Error while creating new device\n" + e.getMessage());
            throw new InternalErrorException(ExceptionMessages.EX_DAO_ERROR);
        }

        DeleteUserResponse output = new DeleteUserResponse();
        output.setItem(null);

        return getGson().toJson(output);
    }
}
