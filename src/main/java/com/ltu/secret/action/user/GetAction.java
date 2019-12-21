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
import com.google.gson.JsonObject;
import com.ltu.secret.action.AbstractSecretAction;
import com.ltu.secret.configuration.ExceptionMessages;
import com.ltu.secret.dao.factory.DAOFactory;
import com.ltu.secret.exception.BadRequestException;
import com.ltu.secret.exception.InternalErrorException;
import com.ltu.secret.model.action.user.GetUserRequest;
import com.ltu.secret.model.action.user.UserResponse;
import com.ltu.secret.model.user.User;
import com.ltu.secret.model.user.UserDAO;

/**
 * The Class GetAction.
 * @author uyphu
 * created on May 22, 2017
 */
public class GetAction extends AbstractSecretAction {
	//private LambdaLogger logger;

	@Override
	public String handle(JsonObject request, Context lambdaContext) throws BadRequestException, InternalErrorException {
        //logger = lambdaContext.getLogger();

        GetUserRequest input = getGson().fromJson(request, GetUserRequest.class);

        if (input == null ||
                input.getId() == null ||
                input.getId().trim().equals("")) {
            throw new BadRequestException(ExceptionMessages.EX_INVALID_INPUT);
        }

        UserDAO dao = DAOFactory.getUserDAO();

        User getUser = dao.find(input.getId());

        if (getUser == null) {
            throw new InternalErrorException(ExceptionMessages.EX_USER_NOT_FOUND);
		}

        UserResponse output = new UserResponse();
        output.setItem(getUser);
        String json = getGson().toJson(output);
        
        System.out.println(json);
        return getGson().toJson(output);
    }
    
}
