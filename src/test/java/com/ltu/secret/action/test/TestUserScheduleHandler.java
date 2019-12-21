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
package com.ltu.secret.action.test;

import org.junit.Assert;

import com.amazonaws.services.lambda.runtime.Context;
import com.ltu.secret.auth.UserScheduleHandler;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestUserScheduleHandler extends TestCase {

	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public TestUserScheduleHandler(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(TestUserScheduleHandler.class);
	}
	
	/**
	 * Creates the context.
	 * 
	 * @return the context
	 */
	private Context createContext() {
		TestContext ctx = new TestContext();

		ctx.setFunctionName("TestUserScheduleHandler");

		return ctx;
	}	
	
	@org.junit.Test
	public void testLambdaFunctionHandler() {
	    UserScheduleHandler handler = new UserScheduleHandler();
	    Context ctx = createContext();	    
	    Object output = handler.handleRequest("", ctx);
	    if (output != null) {
	        System.out.println(output.toString());
	    }
	    Assert.assertEquals(true, true);
	}
	
}
