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
package com.ltu.secret.dao.factory;

import com.ltu.secret.model.secret.DDBSecretDAO;
import com.ltu.secret.model.secret.SecretDAO;
import com.ltu.secret.model.user.DDBUserDAO;
import com.ltu.secret.model.user.UserDAO;

// TODO: Auto-generated Javadoc
/**
 * The DAO Factory object to abstract the implementation of DAO interfaces.
 * @author uyphu
 * created on May 20, 2017
 */
public class DAOFactory {
    
    /**
     * Contains the implementations of the DAO objects. By default we only have a DynamoDB implementation
     *
     * @author FPT LA
     * @date Nov 19, 2017
     */
    public enum DAOType {
        
        /** The Dynamo db. */
        DynamoDB
    }

    /**
     * Returns the default UserDAO object.
     *
     * @return The default implementation of the UserDAO object - by default this is the DynamoDB implementation
     */
    public static UserDAO getUserDAO() {
        return getUserDAO(DAOType.DynamoDB);
    }

    /**
     * Returns a UserDAO implementation.
     *
     * @param daoType A value from the DAOType enum
     * @return The corresponding UserDAO implementation
     */
    public static UserDAO getUserDAO(DAOType daoType) {
        UserDAO dao = null;
        switch (daoType) {
            case DynamoDB:
                dao = DDBUserDAO.getInstance();
                break;
        }

        return dao;
    }
    
    /**
     * Gets the secret DAO.
     *
     * @return the secret DAO
     */
    public static SecretDAO getSecretDAO() {
        return getSecretDAO(DAOType.DynamoDB);
    }
    
    /**
     * Gets the secret DAO.
     *
     * @param daoType the dao type
     * @return the secret DAO
     */
    public static SecretDAO getSecretDAO(DAOType daoType) {
        SecretDAO dao = null;
        switch (daoType) {
            case DynamoDB:
                dao = DDBSecretDAO.getInstance();
                break;
        }

        return dao;
    }
    
}
