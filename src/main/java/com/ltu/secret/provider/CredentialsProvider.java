/*
 * Copyright 2015 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file except in compliance
 * with the License. A copy of the License is located at
 *
 * http://aws.amazon.com/apache2.0/
 *
 * or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES
 * OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package com.ltu.secret.provider;

import com.ltu.secret.exception.AuthorizationException;
import com.ltu.secret.model.user.User;
import com.ltu.secret.model.user.UserCredentials;
import com.ltu.secret.model.user.UserIdentity;

/**
 * The CredentialsProvider interface used by the Login and Registration methods to retrieve temporary AWS credentials
 * for a user
 * @author uyphu
 * created on Jun 11, 2017
 */
public interface CredentialsProvider {
    /**
     * Generates and returns a set of temporary AWS credentials for an end user
     *
     * @param user The end user object. The identity property in the User object needs to be populated with a valid
     *             identityId and openID Token
     * @return A populated UserCredentials object
     * @throws AuthorizationException
     */
    UserCredentials getUserCredentials(User user) throws AuthorizationException;

    /**
     * Gets a unique identifier and a valid OpenID token for a user
     *
     * @param user The user that is logging in or registering
     * @return A populated user identity object with an OpenID token and identityId
     * @throws AuthorizationException
     */
    UserIdentity getUserIdentity(User user) throws AuthorizationException;
}
