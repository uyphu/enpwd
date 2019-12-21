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
 * The Class SearchSecretRequest.
 *
 * @author uyphu
 * @date Nov 19, 2017
 */
public class SearchSecretRequest {

	/** The query. */
	private String query;
	
	/** The limit. */
	private int limit;
	
	/** The cursor. */
	private String cursor;
	
	/**
	 * Gets the query.
	 *
	 * @return the query
	 */
	public String getQuery() {
		return query;
	}
	
	/**
	 * Sets the query.
	 *
	 * @param query the new query
	 */
	public void setQuery(String query) {
		this.query = query;
	}
	
	/**
	 * Gets the limit.
	 *
	 * @return the limit
	 */
	public int getLimit() {
		return limit;
	}
	
	/**
	 * Sets the limit.
	 *
	 * @param limit the new limit
	 */
	public void setLimit(int limit) {
		this.limit = limit;
	}
	
	/**
	 * Gets the cursor.
	 *
	 * @return the cursor
	 */
	public String getCursor() {
		return cursor;
	}
	
	/**
	 * Sets the cursor.
	 *
	 * @param cursor the new cursor
	 */
	public void setCursor(String cursor) {
		this.cursor = cursor;
	}
	
	/**
	 * Instantiates a new search user request.
	 *
	 * @param query the query
	 * @param limit the limit
	 * @param cursor the cursor
	 */
	public SearchSecretRequest(String query, int limit, String cursor) {
		this.query = query;
		this.limit = limit;
		this.cursor = cursor;
	}
	
}
