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
package com.ltu.secret.action.secret;

import com.amazonaws.services.lambda.runtime.Context;
import com.google.gson.JsonObject;
import com.ltu.secret.action.AbstractSecretAction;
import com.ltu.secret.configuration.ExceptionMessages;
import com.ltu.secret.dao.factory.DAOFactory;
import com.ltu.secret.exception.BadRequestException;
import com.ltu.secret.exception.InternalErrorException;
import com.ltu.secret.model.action.secret.GetSecretRequest;
import com.ltu.secret.model.action.secret.SecretResponse;
import com.ltu.secret.model.secret.Secret;
import com.ltu.secret.model.secret.SecretDAO;

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

        GetSecretRequest input = getGson().fromJson(request, GetSecretRequest.class);

        if (input == null ||
                input.getId() == null ||
                input.getId().trim().equals("")) {
            throw new BadRequestException(ExceptionMessages.EX_INVALID_INPUT);
        }

        SecretDAO dao = DAOFactory.getSecretDAO();

        Secret getSecret = dao.find(input.getId());

        if (getSecret == null) {
            throw new InternalErrorException(ExceptionMessages.EX_USER_NOT_FOUND);
		}

        SecretResponse output = new SecretResponse();
        output.setItem(getSecret);

        return getGson().toJson(output);
    }
    
}
