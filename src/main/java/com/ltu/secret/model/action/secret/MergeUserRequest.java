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

import java.nio.ByteBuffer;


/**
 * The Class MergeUserRequest.
 *
 * @author FPT LA
 * @date Nov 19, 2017
 */
public class MergeUserRequest {
	
	/** The id. */
	private String id;

	/** The email. */
	private String userId;
	
	/** The domain. */
	private String domain;
	
	/** The username. */
	private String username;
	
	/** The password. */
	private ByteBuffer password;

	/** The salt. */
	private ByteBuffer salt;
	
	/** The note. */
	private String note;
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

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
	public ByteBuffer getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(ByteBuffer password) {
		this.password = password;
	}

	/**
	 * Gets the salt.
	 *
	 * @return the salt
	 */
	public ByteBuffer getSalt() {
		return salt;
	}

	/**
	 * Sets the salt.
	 *
	 * @param salt the new salt
	 */
	public void setSalt(ByteBuffer salt) {
		this.salt = salt;
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
	 * Instantiates a new merge user request.
	 *
	 * @param id the id
	 * @param userId the user id
	 * @param domain the domain
	 * @param username the username
	 * @param password the password
	 * @param salt the salt
	 * @param note the note
	 */
	public MergeUserRequest(String id, String userId, String domain, String username, ByteBuffer password,
			ByteBuffer salt, String note) {
		this.id = id;
		this.userId = userId;
		this.domain = domain;
		this.username = username;
		this.password = password;
		this.salt = salt;
		this.note = note;
	}

	/**
	 * Instantiates a new merge user request.
	 */
	public MergeUserRequest() {
	}	
}
