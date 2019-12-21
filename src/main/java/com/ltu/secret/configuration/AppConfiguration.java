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
package com.ltu.secret.configuration;

/**
 * The Class AppConfiguration.
 * @author uy phu
 * created on May 21, 2017
 */
public class AppConfiguration {
	

	/** The Constant CONFIG_FILE_NAME. */
	public static final String CONFIG_FILE_NAME = "config.properties";

	/** The Constant BUCKET_NAME. */
	//DEV
	//public static final String BUCKET_NAME = "config-no-deleting";
	//PROD
	public static final String BUCKET_NAME = "secret-us-east-1";
	
	public static final String S3_BUCKET = "S3_BUCKET";
	
	/** The web url key. */
	public static String WEB_URL_KEY = "webUrl";
	
	/** The access key. */
	public static String ACCESS_KEY = "accessKey";
	
	/** The secret access key. */
	public static String SECRET_ACCESS_KEY = "secretAccess";
	
	/** The region key. */
	public static String REGION_KEY = "region";
	
	/** The Constant SMTP_USERNAME. */
	public static final String SMTP_USERNAME = "smtpUsername";
	
	/** The Constant SMTP_PASSWORD. */
	public static final String SMTP_PASSWORD = "smtpPassword";
	
	/** The Constant HOST_MAIL. */
	public static final String HOST_MAIL = "hostMail";
	
	/** The Constant FROM_MAIL. */
	public static final String FROM_MAIL = "senderEmail";
	
	/** The Constant ACTIVATION_MESSAGE. */
	public static final String ACTIVATION_MESSAGE = "activationMessage";
	
	/** The Constant IDENTITY_POOL_ID. */
	public static final String IDENTITY_POOL_ID = "identityPoolId";
		
	/** The Constant CUSTOM_PROVIDER_NAME. */
	public static final String CUSTOM_PROVIDER_NAME = "customProviderName";
	
}
