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

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.google.gson.JsonObject;
import com.ltu.secret.action.AbstractSecretAction;
import com.ltu.secret.auth.AWSAuth;
import com.ltu.secret.auth.Token;
import com.ltu.secret.auth.TokenProvider;
import com.ltu.secret.configuration.ExceptionMessages;
import com.ltu.secret.constants.Constants;
import com.ltu.secret.dao.factory.DAOFactory;
import com.ltu.secret.exception.AuthorizationException;
import com.ltu.secret.exception.BadRequestException;
import com.ltu.secret.exception.DAOException;
import com.ltu.secret.exception.InternalErrorException;
import com.ltu.secret.helper.PasswordHelper;
import com.ltu.secret.model.action.user.LoginUserRequest;
import com.ltu.secret.model.action.user.LoginUserResponse;
import com.ltu.secret.model.user.User;
import com.ltu.secret.model.user.UserCredentials;
import com.ltu.secret.model.user.UserDAO;
import com.ltu.secret.model.user.UserIdentity;
import com.ltu.secret.provider.CredentialsProvider;
import com.ltu.secret.provider.ProviderFactory;

/**
 * The Class LoginAction.
 * @author uyphu
 * created on Jun 11, 2017
 */
public class LoginAction extends AbstractSecretAction{
	private LambdaLogger logger;
	
	private CredentialsProvider cognito = ProviderFactory.getCredentialsProvider();

    @Override
	public String handle(JsonObject request, Context lambdaContext) throws BadRequestException, InternalErrorException {
    	logger = lambdaContext.getLogger();

        LoginUserRequest input = getGson().fromJson(request, LoginUserRequest.class);

        if (input == null ||
                input.getEmail() == null ||
                input.getEmail().trim().equals("") ||
                input.getPassword() == null ||
                input.getPassword().trim().equals("")) {
            throw new BadRequestException(ExceptionMessages.EX_INVALID_INPUT);
        }

        UserDAO dao = DAOFactory.getUserDAO();
        User loggedUser;
        try {
            loggedUser = dao.getUserByEmail(input.getEmail());
        } catch (final DAOException e) {
            logger.log("Error while loading user\n" + e.getMessage());
            throw new InternalErrorException(ExceptionMessages.EX_DAO_ERROR);
        }

        if (loggedUser == null) {
            throw new BadRequestException(ExceptionMessages.EX_EMAIL_NOT_EXIST);
        }
        
        if (!Constants.YES_STATUS.equals(loggedUser.getStatus())) {
            throw new BadRequestException(ExceptionMessages.EX_USER_NOT_ACTIVATED);
        }

        try {
            if (!PasswordHelper.authenticate(input.getPassword(), loggedUser.getPasswordBytes(), loggedUser.getSaltBytes())) {
                throw new BadRequestException(ExceptionMessages.EX_PASSWORD_INCORRECT);
            }
        } catch (final NoSuchAlgorithmException e) {
            logger.log("No algrithm found for password encryption\n" + e.getMessage());
            throw new InternalErrorException(ExceptionMessages.EX_PWD_SALT);
        } catch (final InvalidKeySpecException e) {
            logger.log("No KeySpec found for password encryption\n" + e.getMessage());
            throw new InternalErrorException(ExceptionMessages.EX_PWD_ENCRYPT);
        }
        
        //Output token
        TokenProvider provider = TokenProvider.getInstance();
    	Token token = provider.createToken(loggedUser);
    	
    	UserIdentity identity;
        UserCredentials credentials;
        try {
            identity = cognito.getUserIdentity(loggedUser);
            loggedUser.setIdentity(identity);
            credentials = cognito.getUserCredentials(loggedUser);
        } catch (final AuthorizationException e) {
            logger.log("Error while getting oidc token through Cognito\n" + e.getMessage());
            throw new InternalErrorException(ExceptionMessages.EX_NO_COGNITO_IDENTITY);
        }
        
        //FIXME need to check cognito
        AWSAuth auth = new AWSAuth(loggedUser.getCognitoIdentityId(), identity.getOpenIdToken(), credentials);
    	
    	LoginUserResponse output = new LoginUserResponse();
    	output.setExpires(token.getExpires());
    	output.setToken(token.getToken());
    	output.setType(token.getType());
    	output.setItem(loggedUser);
    	output.setAuth(auth);
        return getGsonExcludeFields().toJson(output);
    }
    
}


