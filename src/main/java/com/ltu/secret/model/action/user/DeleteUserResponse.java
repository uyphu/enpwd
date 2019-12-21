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
package com.ltu.secret.model.action.user;

import com.ltu.secret.model.user.User;

/**
 * Bean for the pet creation response.
 * @author uy phu
 * created on May 20, 2017
 */
public class DeleteUserResponse {
    
    /** The item. */
    private User item;

	/**
	 * Gets the item.
	 *
	 * @return the item
	 */
	public User getItem() {
		return item;
	}

	/**
	 * Sets the item.
	 *
	 * @param item the new item
	 */
	public void setItem(User item) {
		this.item = item;
	}
}
