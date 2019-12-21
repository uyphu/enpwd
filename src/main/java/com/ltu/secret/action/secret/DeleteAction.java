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
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.google.gson.JsonObject;
import com.ltu.secret.action.AbstractSecretAction;
import com.ltu.secret.configuration.ExceptionMessages;
import com.ltu.secret.dao.factory.DAOFactory;
import com.ltu.secret.exception.BadRequestException;
import com.ltu.secret.exception.DAOException;
import com.ltu.secret.exception.InternalErrorException;
import com.ltu.secret.model.action.secret.DeleteSecretRequest;
import com.ltu.secret.model.action.secret.DeleteSecretResponse;
import com.ltu.secret.model.secret.SecretDAO;

/**
 * The Class DeleteAction.
 *
 * @author uyphu
 * @date Nov 19, 2017
 */
public class DeleteAction extends AbstractSecretAction{
	
	/** The logger. */
	private LambdaLogger logger;

    @Override
	public String handle(JsonObject request, Context lambdaContext) throws BadRequestException, InternalErrorException {
        logger = lambdaContext.getLogger();

        DeleteSecretRequest input = getGson().fromJson(request, DeleteSecretRequest.class);

        if (input == null ||
                input.getId() == null ||
                input.getId().trim().equals("")) {
            throw new BadRequestException(ExceptionMessages.EX_INVALID_INPUT);
        }

        SecretDAO dao = DAOFactory.getSecretDAO();

        try {
        	dao.delete(input.getId());
        } catch (final DAOException e) {
            logger.log("Error while creating new secret\n" + e.getMessage());
            throw new InternalErrorException(ExceptionMessages.EX_DAO_ERROR);
        }

        DeleteSecretResponse output = new DeleteSecretResponse();
        output.setItem(null);

        return getGson().toJson(output);
    }
}
