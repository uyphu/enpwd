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

import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestSecretHandler extends TestCase {

	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public TestSecretHandler(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static junit.framework.Test suite() {
		return new TestSuite(TestSecretHandler.class);
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
	 * Test app.
	 */
//	public void stestInsert() {
//		System.out.println("testInsert calling...");
//		Context context = createContext();
//		String output = "src/test/java/com/ltu/secret/action/test/secret.output.json";
//		TestUtils.callAPI(context, "secret.input.json", output);
//		
//		assertTrue(true);
//	}
	
//	public void testBuildGet() {
//		
//		System.out.println("testBuildGet calling...");
//		buildGetRequest("secret.output.json", "src/test/java/com/ltu/secret/action/test/secret.get.output.json");
//		assertTrue(true);
//	}
	
//	public void testGet() {
//		
//		System.out.println("testGet calling...");
//		Context context = createContext();
//		TestUtils.callAPI(context, "secret.get.output.json", "src/test/java/com/ltu/secret/action/test/secret.output.json");
//		assertTrue(true);
//	}
	
//	public void testBuildDelete() {
//
//		System.out.println("testBuildDelete calling...");
//		buildDeleteRequest("secret.output.json", "src/test/java/com/ltu/secret/action/test/delete.get.output.json");
//		assertTrue(true);
//	}
	
	public void testDelete() {
		
		System.out.println("testDelete calling...");
		Context context = createContext();
		//TestUtils.callAPI(context, "delete.get.output.json", "src/test/java/com/ltu/secret/action/test/secret.output.json");
		TestUtils.callAPI(context, "search.input.json", "src/test/java/com/ltu/secret/action/test/search.output.json");
		
		assertTrue(true);
	}
//	
//	
//	
//	/**
//	 * Builds the get request.
//	 *
//	 * @param inputFile the input file
//	 * @param outputFile the output file
//	 */
//	private void buildGetRequest(String inputFile, String outputFile) {
//		JsonParser parser = new JsonParser();
//		JsonObject inputObj;
//		try {
//			InputStream request = TestUtils.class.getResourceAsStream(inputFile);
//			inputObj = parser.parse(IOUtils.toString(request)).getAsJsonObject();
//			StringBuilder output = new StringBuilder();
//			output.append("{");
//			output.append("\"action\":\"com.ltu.secret.action.secret.GetAction\",");
//			output.append("\"body\":{");
//			JsonObject item = inputObj.get("item").getAsJsonObject();
//			output.append("\"id\":\"" + item.get("id").getAsString()+"\"");
//			output.append("}");
//			output.append("}");
//			OutputStream response = new FileOutputStream(outputFile);
//			IOUtils.write(output, response);
//			
//			response.close();
//			response.flush();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	private void buildDeleteRequest(String inputFile, String outputFile) {
//		JsonParser parser = new JsonParser();
//		JsonObject inputObj;
//		try {
//			InputStream request = TestUtils.class.getResourceAsStream(inputFile);
//			inputObj = parser.parse(IOUtils.toString(request)).getAsJsonObject();
//			StringBuilder output = new StringBuilder();
//			output.append("{");
//			output.append("\"action\":\"com.ltu.secret.action.secret.DeleteAction\",");
//			output.append("\"body\":{");
//			JsonObject item = inputObj.get("item").getAsJsonObject();
//			output.append("\"id\":\"" + item.get("id").getAsString()+"\"");
//			output.append("}");
//			output.append("}");
//			OutputStream response = new FileOutputStream(outputFile);
//			IOUtils.write(output, response);
//			response.flush();
//			response.close();
//			request.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
}
