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
package com.ltu.secret.auth;

// TODO: Auto-generated Javadoc
/**
 * Object representation of input to an implementation of an API Gateway custom authorizer
 * of type TOKEN.
 *
 * @author uyphu
 * created on May 20, 2017
 */
public class TokenAuthorizerContext {

    /** The type. */
    String type;
    
    /** The authorization token. */
    String authorizationToken;
    
    /** The method arn. */
    String methodArn;

    /**
     * JSON input is deserialized into this object representation.
     *
     * @param type Static value - TOKEN
     * @param authorizationToken - Incoming bearer token sent by a client
     * @param methodArn - The API Gateway method ARN that a client requested
     */
    public TokenAuthorizerContext(String type, String authorizationToken, String methodArn) {
        this.type = type;
        this.authorizationToken = authorizationToken;
        this.methodArn = methodArn;
    }

    /**
     * Instantiates a new token authorizer context.
     */
    public TokenAuthorizerContext() {

    }

    /**
     * Gets the type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type.
     *
     * @param type the new type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the authorization token.
     *
     * @return the authorization token
     */
    public String getAuthorizationToken() {
        return authorizationToken;
    }

    /**
     * Sets the authorization token.
     *
     * @param authorizationToken the new authorization token
     */
    public void setAuthorizationToken(String authorizationToken) {
        this.authorizationToken = authorizationToken;
    }

    /**
     * Gets the method arn.
     *
     * @return the method arn
     */
    public String getMethodArn() {
        return methodArn;
    }

    /**
     * Sets the method arn.
     *
     * @param methodArn the new method arn
     */
    public void setMethodArn(String methodArn) {
        this.methodArn = methodArn;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TokenAuthorizerContext [type=" + type + ", authorizationToken=" + authorizationToken + ", methodArn="
				+ methodArn + "]";
	}
    
}
