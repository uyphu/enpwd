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
 * This exception should bot be exposed to Lambda or the client application. It's used internally to identify a DAO issue
 * @author Uy Phu
 * created on May 18, 2017
 */
public class DAOException extends Exception {
    
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2194070705225880244L;

	/**
     * Instantiates a new DAO exception.
     *
     * @param s the s
     * @param e the e
     */
    public DAOException(String s, Exception e) {
        super(s, e);
    }

    /**
     * Instantiates a new DAO exception.
     *
     * @param s the s
     */
    public DAOException(String s) {
        super(s);
    }
}
