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
 * This object is used to store temporary AWS credentials.
 * @author uy phu
 * created on May 19, 2017
 */
public class UserCredentials {
    
    /** The access key. */
    private String accessKey;
    
    /** The secret key. */
    private String secretKey;
    
    /** The session token. */
    private String sessionToken;
    
    /** The expiration. */
    private long expiration;

    /**
     * Gets the access key.
     *
     * @return the access key
     */
    public String getAccessKey() {
        return accessKey;
    }

    /**
     * Sets the access key.
     *
     * @param accessKey the new access key
     */
    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    /**
     * Gets the secret key.
     *
     * @return the secret key
     */
    public String getSecretKey() {
        return secretKey;
    }

    /**
     * Sets the secret key.
     *
     * @param secretKey the new secret key
     */
    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    /**
     * Gets the session token.
     *
     * @return the session token
     */
    public String getSessionToken() {
        return sessionToken;
    }

    /**
     * Sets the session token.
     *
     * @param sessionToken the new session token
     */
    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    /**
     * Gets the expiration.
     *
     * @return the expiration
     */
    public long getExpiration() {
        return expiration;
    }

    /**
     * Sets the expiration.
     *
     * @param expiration the new expiration
     */
    public void setExpiration(long expiration) {
        this.expiration = expiration;
    }
    
}
