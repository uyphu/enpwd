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
package com.ltu.secret.model.action.secret;

/**
 * The Class InsertSecretRequest.
 *
 * @author FPT LA
 * @date Nov 19, 2017
 */
public class InsertSecretRequest {


	/** The email. */
	private String userId;
	
	/** The domain. */
	private String domain;
	
	/** The username. */
	private String username;
	
	/** The password. */
	private String password;

	/** The note. */
	private String note;

	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * Sets the user id.
	 *
	 * @param userId the new user id
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * Gets the domain.
	 *
	 * @return the domain
	 */
	public String getDomain() {
		return domain;
	}

	/**
	 * Sets the domain.
	 *
	 * @param domain the new domain
	 */
	public void setDomain(String domain) {
		this.domain = domain;
	}

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username.
	 *
	 * @param username the new username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the note.
	 *
	 * @return the note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * Sets the note.
	 *
	 * @param note the new note
	 */
	public void setNote(String note) {
		this.note = note;
	}

	/**
	 * Instantiates a new insert secret request.
	 *
	 * @param userId the user id
	 * @param domain the domain
	 * @param username the username
	 * @param password the password
	 * @param note the note
	 */
	public InsertSecretRequest(String userId, String domain, String username, String password, String note) {
		super();
		this.userId = userId;
		this.domain = domain;
		this.username = username;
		this.password = password;
		this.note = note;
	}

	/**
	 * Instantiates a new insert secret request.
	 */
	public InsertSecretRequest() {
		
	}
	
}
