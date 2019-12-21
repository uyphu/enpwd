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
package com.ltu.secret.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.ltu.secret.exception.CommonException;
import com.ltu.secret.utils.MailUtil;
import com.ltu.secret.utils.S3ResourceLoaderUtil;

public class HelloLambda implements RequestHandler<Integer, String>{
   
	@Override
	public String handleRequest(Integer myCount, Context arg1) {
		System.out.println(System.getenv("S3_BUCKET"));
		System.out.println(S3ResourceLoaderUtil.getProperty("senderEmail"));
		System.out.println(S3ResourceLoaderUtil.getProperty("smtpUsername"));
		System.out.println(S3ResourceLoaderUtil.getProperty("smtpPassword"));
		System.out.println(S3ResourceLoaderUtil.getProperty("hostMail"));
		System.out.println(S3ResourceLoaderUtil.getProperty("activationMessage"));
		String output = System.getenv("S3_BUCKET");
		output += S3ResourceLoaderUtil.getProperty("senderEmail");
		output += S3ResourceLoaderUtil.getProperty("smtpUsername");
		output += S3ResourceLoaderUtil.getProperty("smtpPassword");
		output += S3ResourceLoaderUtil.getProperty("hostMail");
		output += S3ResourceLoaderUtil.getProperty("activationMessage");
		try {
			MailUtil.sendActivateEmail("uyphu@yahoo.com", "12345");
		} catch (CommonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return String.valueOf(output + myCount);
	}
}
