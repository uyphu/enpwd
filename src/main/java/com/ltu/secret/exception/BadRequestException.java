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
 * This exception should be thrown whenever requests fail validation. The exception sets a default pattern in the string
 * "BAD_REQ: .*" that can be easily matched from the API Gateway for mapping.
 * @author Uy Phu
 * created on May 18, 2017
 */
public class BadRequestException extends Exception {
  
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3428755124639467178L;
	
	/** The Constant PREFIX. */
	private static final String PREFIX = "BAD_REQ: ";
    
    /**
     * Instantiates a new bad request exception.
     *
     * @param s the s
     * @param e the e
     */
    public BadRequestException(String s, Exception e) {
        super(PREFIX + s, e);
    }

    /**
     * Instantiates a new bad request exception.
     *
     * @param s the s
     */
    public BadRequestException(String s) {
        super(PREFIX + s);
    }
    
}
