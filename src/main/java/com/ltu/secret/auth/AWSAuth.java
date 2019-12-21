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

import com.google.gson.annotations.Expose;
import com.ltu.secret.model.user.UserCredentials;

// TODO: Auto-generated Javadoc
/**
 * The Class AWSAuth.
 * @author uyphu
 * created on May 20, 2017
 */
public class AWSAuth {

	/** The identity id. */
	@Expose
	private String identityId;
	
	/** The token. */
	@Expose
	private String token;
	
	/** The credentials. */
	@Expose
	private UserCredentials credentials;

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

	/**
	 * Gets the token.
	 *
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * Sets the token.
	 *
	 * @param token the new token
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * Gets the credentials.
	 *
	 * @return the credentials
	 */
	public UserCredentials getCredentials() {
		return credentials;
	}

	/**
	 * Sets the credentials.
	 *
	 * @param credentials the new credentials
	 */
	public void setCredentials(UserCredentials credentials) {
		this.credentials = credentials;
	}

	/**
	 * Instantiates a new AWS auth.
	 */
	public AWSAuth() {
		
	}
	
	/**
	 * Instantiates a new AWS auth.
	 *
	 * @param identityId the identity id
	 * @param token the token
	 * @param credentials the credentials
	 */
	public AWSAuth(String identityId, String token, UserCredentials credentials) {
		this.identityId = identityId;
		this.token = token;
		this.credentials = credentials;
	}
}
