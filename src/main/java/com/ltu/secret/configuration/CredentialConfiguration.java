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

import com.amazonaws.auth.BasicAWSCredentials;
import com.ltu.secret.utils.S3ResourceLoaderUtil;

/**
 * The Class CredentialConfiguration.
 * @author uyphu
 * created on May 22, 2017
 */
public class CredentialConfiguration {

	/**
	 * Gets the aws credentials.
	 *
	 * @return the aws credentials
	 */
	public static BasicAWSCredentials getAwsCredentials() {
		String accessKey = S3ResourceLoaderUtil.getProperty("accessKey");
		String secretAccess = S3ResourceLoaderUtil.getProperty("secretAccess");
		BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, secretAccess);
		return credentials;
	}
	
}
