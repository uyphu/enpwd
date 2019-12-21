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
package com.ltu.secret.model.user;

/**
 * The user identity bean - this is used by the credentials provider to store a unique identity id (for examaple a Cognito id)
 * and an OpenID token for the user. The OpenID token can be exchanged for temporary AWS credentials.
 * @author uy phu
 * created on May 19, 2017
 */
public class UserIdentity {
    
    /** The open id token. */
    private String openIdToken;
    
    /** The identity id. */
    private String identityId;

    /**
     * Gets the open id token.
     *
     * @return the open id token
     */
    public String getOpenIdToken() {
        return openIdToken;
    }

    /**
     * Sets the open id token.
     *
     * @param openIdToken the new open id token
     */
    public void setOpenIdToken(String openIdToken) {
        this.openIdToken = openIdToken;
    }

    /**
     * Gets the identity id.
     *
     * @return the identity id
     */
    public String getIdentityId() {
        return identityId;
    }

    /**
     * Sets the identity id.
     *
     * @param identityId the new identity id
     */
    public void setIdentityId(String identityId) {
        this.identityId = identityId;
    }
    
}
