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
package com.ltu.secret.exception;

/**
 * This exception is thrown whenever this service is not authorize to communicate with another AWS service, it should not
 * be exposed to Lambda or returned to the client. When this exception is caught we should throw an InternalErrorException
 * @author uyphu
 * created on May 21, 2017
 */
@SuppressWarnings("serial")
public class CommonException extends Exception {
	
	/** The Constant PREFIX. */
	private static final String PREFIX = "COMMON_ERROR: ";
    
    /**
     * Instantiates a new common exception.
     *
     * @param s the s
     * @param e the e
     */
    public CommonException(String s, Exception e) {
        super(PREFIX + s, e);
    }

    /**
     * Instantiates a new common exception.
     *
     * @param s the s
     */
    public CommonException(String s) {
        super(PREFIX + s);
    }
}
