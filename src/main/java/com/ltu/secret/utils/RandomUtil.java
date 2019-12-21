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
package com.ltu.secret.utils;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * Utility class for generating random Strings.
 * @author PhuLTU
 * created on May 22, 2017
 */
public final class RandomUtil {

    /** The Constant DEF_COUNT. */
    private static final int DEF_COUNT = 20;

    /**
     * Instantiates a new random util.
     */
    private RandomUtil() {
    	
    }

    /**
     * Generates a password.
     *
     * @return the generated password
     */
    public static String generatePassword() {
        return RandomStringUtils.randomAlphanumeric(DEF_COUNT);
    }

    /**
     * Generates an activation key.
     *
     * @return the generated activation key
     */
    public static String generateActivationKey() {
        return RandomStringUtils.randomNumeric(DEF_COUNT);
    }
    
    /**
     * Generate activation key.
     *
     * @param numCount the num count
     * @return the string
     */
    public static String generateActivationKey(int numCount) {
        return RandomStringUtils.randomNumeric(numCount);
    }

    /**
    * Generates a reset key.
    *
    * @return the generated reset key
    */
   public static String generateResetKey() {
       return RandomStringUtils.randomNumeric(DEF_COUNT);
   }
   
}
