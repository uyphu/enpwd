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
 * Configuration parameters for the DynamoDB DAO objects
 * @author uy phu
 * created on May 19, 2017
 */
public class DynamoDBConfiguration {
    // TODO: Specify the name of the Users table in DynamoDB
    public static final String USERS_TABLE_NAME = "x_users";
    
    /** The Constant SECRET_TABLE_NAME. */
    // TODO: Specify the name of the Secrets table in DynamoDB
    public static final String SECRET_TABLE_NAME = "x_secrets";
    
    public static final int SCAN_LIMIT = 50;
    
    /** The Constant SECRET_USERID_INDEX. */
    public static final String SECRET_USERID_INDEX = "userId-domain-index";
    
    /** The Constant USER_EMAIL_INDEX. */
    public static final String USER_EMAIL_INDEX = "email-index";
}
