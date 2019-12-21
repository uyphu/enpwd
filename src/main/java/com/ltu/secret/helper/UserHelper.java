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
package com.ltu.secret.helper;

import com.ltu.secret.constants.Constants;
import com.ltu.secret.model.user.User;

/**
 * The Class UserHelper.
 * 
 * @author uyphu
 * created on May 21, 2017
 */
public class UserHelper {

	/**
	 * Convert to user.
	 * 
	 * @param user
	 *            the user
	 * @return the user
	 */
	public static User convertToUser(com.restfb.types.User user) {
		User obj = new User();
		//obj.setEmail(user.getId() + Constants.FACEBOOK_EMAIL);
		obj.setEmail(user.getEmail());
		obj.setActivateCode(user.getId());
		obj.setType(Constants.FACEBOOK_TYPE);
		return obj;
	}

	/**
	 * From json.
	 * 
	 * @param json
	 *            the json
	 * @return the user
	 */
//	public static User fromJson(JSONObject json) {
//		return null;
//	}

}
