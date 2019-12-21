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

package com.ltu.secret.model.secret;

import java.util.List;

import com.ltu.secret.exception.DAOException;

/**
 * This interface defines the methods required for an implementation of the SecretDAO object.
 *
 * @author uy phu
 * created on May 19, 2017
 */
public interface SecretDAO {

    /**
     * Creates a new secret in the data store.
     *
     * @param secret The new secret information
     * @return The secretname of the secret that was created
     * @throws DAOException the DAO exception
     */
    String createSecret(Secret secret) throws DAOException;
    
    
    /**
     * Insert.
     *
     * @param secret the secret
     * @return the device
     * @throws DAOException the DAO exception
     */
    Secret insert(Secret secret) throws DAOException;
    
    /**
     * Update.
     *
     * @param secret the secret
     * @return the device
     * @throws DAOException the DAO exception
     */
    Secret update(Secret secret) throws DAOException;
    
    /**
     * Merge.
     *
     * @param secret the secret
     * @return the device
     * @throws DAOException the DAO exception
     */
    Secret merge(Secret secret) throws DAOException;
    
    /**
     * Delete.
     *
     * @param id the id
     * @throws DAOException the DAO exception
     */
    void delete(String id) throws DAOException;
    
    /**
     * Gets the.
     *
     * @param id the id
     * @return the device
     */
    Secret find(String id);
    
    /**
     * Find.
     *
     * @param userId the user id
     * @param domain the domain
     * @param username the username
     * @return the secret
     */
    Secret find(String userId, String domain, String username);
    
    /**
     * Search.
     *
     * @param query the query
     * @param limit the limit
     * @param cursor the cursor
     * @return the list
     */
    List<Secret> search(String query, int limit, String cursor, String indexStr);
    
    /**
     * Mapper scan.
     *
     * @param query the query
     * @param limit the limit
     * @param cursor the cursor
     * @return the list
     */
    public List<Secret> mapperScan(String query, int limit, String cursor, String indexStr);
    
    /**
     * Scan.
     *
     * @param query the query
     * @param limit the limit
     * @param cursor the cursor
     * @return the list
     */
    public List<Secret> scan(String query, int limit, String cursor, String indexStr);
}
