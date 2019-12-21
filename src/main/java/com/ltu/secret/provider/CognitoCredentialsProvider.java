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
package com.ltu.secret.provider;

import com.amazonaws.services.cognitoidentity.AmazonCognitoIdentityClient;
import com.amazonaws.services.cognitoidentity.model.GetCredentialsForIdentityRequest;
import com.amazonaws.services.cognitoidentity.model.GetCredentialsForIdentityResult;
import com.amazonaws.services.cognitoidentity.model.GetOpenIdTokenForDeveloperIdentityRequest;
import com.amazonaws.services.cognitoidentity.model.GetOpenIdTokenForDeveloperIdentityResult;
import com.ltu.secret.configuration.CognitoConfiguration;
import com.ltu.secret.exception.AuthorizationException;
import com.ltu.secret.model.user.User;
import com.ltu.secret.model.user.UserCredentials;
import com.ltu.secret.model.user.UserIdentity;

/**
 * Cognito implementation of the CredentialsProvider interface. The configuration for the Cognito credentials provider
 * is in the CognitoConfiguration class in the com.amazonaws.apigatewaydemo.configuration package
 *
 * @author uyphu
 * @date Jun 11, 2017
 */
public class CognitoCredentialsProvider implements CredentialsProvider {
    
    /** The instance. */
    private static CognitoCredentialsProvider instance = null;

    /** The identity client. */
    //private static AmazonCognitoIdentityClient identityClient = new AmazonCognitoIdentityClient(CredentialConfiguration.getAwsCredentials());
    private static AmazonCognitoIdentityClient identityClient = new AmazonCognitoIdentityClient();

    /**
     * Gets the initialized instance of the CognitoCredentialsProvider. This provider should be accessed through the
     * ProviderFactory and used through the CredentialsProvider interface.
     *
     * @return The singleton instance of the CognitoCredentialsProvider object
     */
    public static CognitoCredentialsProvider getInstance() {
        if (instance == null) {
            instance = new CognitoCredentialsProvider();
        }
        return instance;
    }

    /**
     * Instantiates a new cognito credentials provider.
     */
    protected CognitoCredentialsProvider() {
        // protected constructor that should not be called outside the class
    }

    /**
     * Retreives a set of AWS temporary credentials from Amazon Cognito using Developer Authenticated Identities.
     *
     * @param user The end user object. The identity property in the User object needs to be populated with a valid
     *             identityId and openID Token
     * @return A valid set of temporary AWS credentials
     * @throws AuthorizationException the authorization exception
     */
    @Override
	public UserCredentials getUserCredentials(User user) throws AuthorizationException {
        if (user == null || user.getCognitoIdentityId() == null || user.getCognitoIdentityId().trim().equals("")) {
            throw new AuthorizationException("Invalid user");
        }

        GetCredentialsForIdentityRequest credsRequest = new GetCredentialsForIdentityRequest();
        credsRequest.setIdentityId(user.getCognitoIdentityId());
        credsRequest.addLoginsEntry(
                CognitoConfiguration.COGNITO_PROVIDER_NAME,
                user.getIdentity().getOpenIdToken()
        );

        GetCredentialsForIdentityResult resp = identityClient.getCredentialsForIdentity(credsRequest);
        if (resp == null) {
            throw new AuthorizationException("Empty GetCredentialsForIdentity response");
        }

        UserCredentials creds = new UserCredentials();
        creds.setAccessKey(resp.getCredentials().getAccessKeyId());
        creds.setSecretKey(resp.getCredentials().getSecretKey());
        creds.setSessionToken(resp.getCredentials().getSessionToken());
        creds.setExpiration(resp.getCredentials().getExpiration().getTime());

        return creds;
    }

    /**
     * Returns a Cognito IdentityID for the end user and a OpenID Token based on the custom developer authenticated
     * identity provider configured in the CognitoConfiguration class. The OpenID token in the UserIdentity object is
     * only used to retrieve credentials for the user with the getUserCredentials method and is very short lived.
     *
     * @param user The user that is logging in or registering
     * @return A populated UserIdentity object.
     * @throws AuthorizationException the authorization exception
     */
    @Override
	public UserIdentity getUserIdentity(User user) throws AuthorizationException {
        if (user == null || user.getEmail() == null || user.getEmail().trim().equals("")) {
            throw new AuthorizationException("Invalid user");
        }

        GetOpenIdTokenForDeveloperIdentityRequest oidcRequest = new GetOpenIdTokenForDeveloperIdentityRequest();
        oidcRequest.setIdentityPoolId(CognitoConfiguration.IDENTITY_POOL_ID);
        if (user.getCognitoIdentityId() != null && !user.getCognitoIdentityId().trim().equals("")) {
            oidcRequest.setIdentityId(user.getCognitoIdentityId());
        }

        oidcRequest.addLoginsEntry(
                CognitoConfiguration.CUSTOM_PROVIDER_NAME,
                user.getEmail()
        );

        GetOpenIdTokenForDeveloperIdentityResult resp = identityClient.getOpenIdTokenForDeveloperIdentity(oidcRequest);

        if (resp == null) {
            throw new AuthorizationException("Empty GetOpenIdTokenForDeveloperIdentity response");
        }

        UserIdentity identity = new UserIdentity();
        identity.setIdentityId(resp.getIdentityId());
        identity.setOpenIdToken(resp.getToken());
        return identity;
    }
}
