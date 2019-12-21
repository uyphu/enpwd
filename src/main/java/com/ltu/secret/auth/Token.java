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

import com.ltu.secret.utils.AppUtil;


/**
 * The security token.
 * @author uyphu
 * created on May 20, 2017
 */
public class Token {

    /** The token. */
    private String token;
    
    /** The expires. */
    private long expires;
    
    /** The type. */
    private String type;
	
	/**
	 * Instantiates a new token.
	 *
	 * @param token the token
	 * @param expires the expires
	 * @param type the type
	 */
	public Token(String token, long expires, String type) {
		this.token = token;
		this.expires = expires;
		this.type = type;
	}
	
	/**
	 * Instantiates a new token.
	 */
	public Token() {
		
	}
	
	/**
	 * Gets the token.
	 *
	 * @return the token
	 */
	public final String getToken() {
		return this.token;
	}
	
	/**
	 * Sets the token.
	 *
	 * @param token the new token
	 */
	public final void setToken(String token) {
		this.token = token;
	}
	
	/**
	 * Gets the expires.
	 *
	 * @return the expires
	 */
	public final long getExpires() {
		return this.expires;
	}
	
	/**
	 * Sets the expires.
	 *
	 * @param expires the new expires
	 */
	public final void setExpires(long expires) {
		this.expires = expires;
	}
	
	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public final String getType() {
		return this.type;
	}
	
	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public final void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "Token [token=" + this.token + ", expires=" + this.expires + ", type=" + this.type + "]";
	}
	
	public String toJSON() {
		return AppUtil.toJSON(this);
	}
	
}