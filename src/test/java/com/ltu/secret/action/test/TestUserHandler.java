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

import com.amazonaws.services.lambda.runtime.Context;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestUserHandler extends TestCase {

	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public TestUserHandler(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(TestUserHandler.class);
	}
	
	/**
	 * Creates the context.
	 * 
	 * @return the context
	 */
	private Context createContext() {
		TestContext ctx = new TestContext();

		ctx.setFunctionName("Your Function Name");

		return ctx;
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testApp() {		
		Context context = createContext();
		String output = "src/test/java/com/ltu/secret/action/test/user.output.json";
		TestUtils.callAPI(context, "forget.pwd.input.json", "src/test/java/com/ltu/secret/action/test/user.output.json");
		//buildGetRequest(output, "src/test/java/com/ltu/secret/action/test/user.get.output.json");
		assertTrue(true);
	}
	
//	private void buildGetRequest(String inputFile, String outputFile) {
//		JsonParse parser = new JsonParser();
//		JsonObject inputObj;
//		try {
//			InputStream request = TestUtils.class.getResourceAsStream(inputFile);
//			inputObj = parser.parse(IOUtils.toString(request)).getAsJsonObject();
//			StringBuilder output = new StringBuilder(); 
//			output.append("{");
//			output.append("\"action\":\"com.ltu.secret.action.secret.GetAction\",");
//			output.append("\"body\":{");
//			output.append("\"id\":"+inputObj.get("id").getAsString());
//			output.append("}");
//			output.append("}");
//			OutputStream response = new FileOutputStream(outputFile);
//			IOUtils.write(output, response);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		
//	}
}
