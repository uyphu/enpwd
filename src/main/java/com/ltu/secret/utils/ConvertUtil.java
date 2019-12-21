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
package com.ltu.secret.utils;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.ltu.secret.model.secret.Secret;
import com.ltu.secret.model.user.User;

/**
 * The Class ConvertUtil.
 * 
 * @author uyphu
 * created on May 20, 2017
 */
public class ConvertUtil {

	/** The log. */
	private static final Logger logger = LogManager.getLogger("ConvertUtil");

	/**
	 * To user.
	 *
	 * @param item the item
	 * @return the user
	 */
	public static User toUser(Map<String, AttributeValue> item) {
		User user = new User();
		try {
			user.setId(item.get("id").getS());
			user.setEmail(item.get("email") != null ? item.get("email").getS() : null);
			user.setPassword(item.get("password") != null ? item.get("password").getB() : null);
			user.setSalt(item.get("salt") != null ? item.get("salt").getB() : null);
			user.setType(item.get("type") != null ? item.get("type").getS() : null);
			user.setDisplayName(item.get("displayName") != null ? item.get("displayName").getS() : null);
			user.setActivateCode(item.get("activateCode") != null ? item.get("activateCode").getS() : null);
			user.setImageUrl(item.get("imageUrl") != null ? item.get("imageUrl").getS() : null);
			user.setSecretKey(item.get("secretKey") != null ? item.get("secretKey").getS() : null);
			user.setStatus(item.get("status").getS());
			user.setCreatedAt(item.get("createdAt") != null ? AppUtil.toDate(item.get("createdAt").getS()) : null);
			user.setCognitoIdentityId(item.get("identityId") != null ? item.get("identityId").getS() : null);
		} catch (Exception e) {
			logger.error(e.getMessage(), e.getCause());
		}
		return user;
	}
	
	public static Secret toSecret(Map<String, AttributeValue> item) {
		Secret secret = new Secret();
		try {
			secret.setId(item.get("id").getS());
			secret.setUserId(item.get("userId") != null ? item.get("userId").getS() : null);
			secret.setDomain(item.get("domain") != null ? item.get("domain").getS() : null);
			secret.setUsername(item.get("username") != null ? item.get("username").getS() : null);
			secret.setPassword(item.get("password") != null ? item.get("password").getS() : null);
			secret.setNote(item.get("note") != null ? item.get("note").getS() : null);
			secret.setCreatedAt(item.get("createdAt") != null ? AppUtil.toDate(item.get("createdAt").getS()) : null);
		} catch (Exception e) {
			logger.error(e.getMessage(), e.getCause());
		}
		return secret;
	}
	
}

