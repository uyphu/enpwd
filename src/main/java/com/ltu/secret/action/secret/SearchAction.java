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

import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.google.gson.JsonObject;
import com.ltu.secret.action.AbstractSecretAction;
import com.ltu.secret.configuration.DynamoDBConfiguration;
import com.ltu.secret.configuration.ExceptionMessages;
import com.ltu.secret.dao.factory.DAOFactory;
import com.ltu.secret.exception.BadRequestException;
import com.ltu.secret.exception.InternalErrorException;
import com.ltu.secret.model.action.secret.SearchSecretRequest;
import com.ltu.secret.model.action.secret.SearchSecretResponse;
import com.ltu.secret.model.secret.Secret;
import com.ltu.secret.model.secret.SecretDAO;

/**
 * The Class GetAction.
 * @author uyphu
 * created on May 22, 2017
 */
public class SearchAction extends AbstractSecretAction {
	//private LambdaLogger logger;

	@Override
	public String handle(JsonObject request, Context lambdaContext) throws BadRequestException, InternalErrorException {
        //logger = lambdaContext.getLogger();

        SearchSecretRequest input = getGson().fromJson(request, SearchSecretRequest.class);

        if (input == null ||
                input.getQuery() == null ||
                input.getQuery().trim().equals("")) {
            throw new BadRequestException(ExceptionMessages.EX_INVALID_INPUT);
        }

        SecretDAO dao = DAOFactory.getSecretDAO();

        List<Secret> secrets = dao.search(input.getQuery(), input.getLimit(), input.getCursor(), DynamoDBConfiguration.SECRET_USERID_INDEX);
        
        //List<Secret> secrets = dao.search(input.getQuery(), input.getLimit(), input.getCursor(), null);

        if (secrets == null) {
            throw new InternalErrorException(ExceptionMessages.EX_USER_NOT_FOUND);
		}

        SearchSecretResponse output = new SearchSecretResponse();
        output.setItems(secrets);

        return getGson().toJson(output);
    }
    
}
