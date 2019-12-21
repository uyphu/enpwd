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
package com.ltu.secret.dao;

import java.util.ArrayList;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.LocalSecondaryIndex;
import com.amazonaws.services.dynamodbv2.model.Projection;
import com.amazonaws.services.dynamodbv2.model.ProjectionType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.ltu.secret.configuration.DynamoDBConfiguration;
import com.ltu.secret.service.dynamodb.AmazonDynamoDBClientUtil;

/**
 * The Class InitDao.
 * @author uy phu
 * created on May 19, 2017
 */
public class InitDao {

	/** The client. */
	static AmazonDynamoDBClient client = AmazonDynamoDBClientUtil.getInstance();

	/** The mapper. */
	static DynamoDBMapper mapper = new DynamoDBMapper(client);

	/** The log. */
	// private static Logger log = Logger.getLogger(InitDao.class);

	/** The dynamo db. */
	static DynamoDB dynamoDB = new DynamoDB(client);

	/**
	 * Inits the tables.
	 */
	public static void initTables() {
		try {

			deleteTable(DynamoDBConfiguration.USERS_TABLE_NAME);
			deleteTable(DynamoDBConfiguration.SECRET_TABLE_NAME);
			
			createTable(DynamoDBConfiguration.USERS_TABLE_NAME, 10L, 2L, "id", "S", null, null);
			createTable(DynamoDBConfiguration.SECRET_TABLE_NAME, 10L, 2L, "id", "S", null, null);

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Success.");
	}

	/**
	 * Delete table.
	 * 
	 * @param tableName
	 *            the table name
	 */
	private static void deleteTable(String tableName) {
		Table table = dynamoDB.getTable(tableName);
		try {
			System.out.println("Issuing DeleteTable request for " + tableName);
			table.delete();
			System.out.println("Waiting for " + tableName + " to be deleted...this may take a while...");
			table.waitForDelete();

		} catch (Exception e) {
			System.err.println("DeleteTable request failed for " + tableName);
			System.err.println(e.getMessage());
		}
	}


	/**
	 * Creates the table.
	 *
	 * @param tableName the table name
	 * @param readCapacityUnits the read capacity units
	 * @param writeCapacityUnits the write capacity units
	 * @param partitionKeyName the partition key name
	 * @param partitionKeyType the partition key type
	 * @param sortKeyName the sort key name
	 * @param sortKeyType the sort key type
	 */
	private static void createTable(String tableName, long readCapacityUnits, long writeCapacityUnits, String partitionKeyName,
			String partitionKeyType, String sortKeyName, String sortKeyType) {

		try {

			ArrayList<KeySchemaElement> keySchema = new ArrayList<KeySchemaElement>();
			keySchema.add(new KeySchemaElement().withAttributeName(partitionKeyName).withKeyType(KeyType.HASH));
			// Partition
			// key

			ArrayList<AttributeDefinition> attributeDefinitions = new ArrayList<AttributeDefinition>();
			attributeDefinitions.add(new AttributeDefinition().withAttributeName(partitionKeyName).withAttributeType(
					partitionKeyType));

			if (sortKeyName != null) {
				keySchema.add(new KeySchemaElement().withAttributeName(sortKeyName).withKeyType(KeyType.RANGE));
				// Sort
				// key
				attributeDefinitions.add(new AttributeDefinition().withAttributeName(sortKeyName)
						.withAttributeType(sortKeyType));
			}

			CreateTableRequest request = new CreateTableRequest()
					.withTableName(tableName)
					.withKeySchema(keySchema)
					.withProvisionedThroughput(
							new ProvisionedThroughput().withReadCapacityUnits(readCapacityUnits).withWriteCapacityUnits(
									writeCapacityUnits));

			// If this is the Reply table, define a local secondary index
			if ("Reply1".equals(tableName)) {

				attributeDefinitions.add(new AttributeDefinition().withAttributeName("PostedBy").withAttributeType("S"));

				ArrayList<LocalSecondaryIndex> localSecondaryIndexes = new ArrayList<LocalSecondaryIndex>();
				localSecondaryIndexes.add(new LocalSecondaryIndex().withIndexName("PostedBy-Index")
						.withKeySchema(new KeySchemaElement().withAttributeName(partitionKeyName).withKeyType(KeyType.HASH),
						// Partition
						// key
								new KeySchemaElement().withAttributeName("PostedBy").withKeyType(KeyType.RANGE))
						// Sort
						// key
						.withProjection(new Projection().withProjectionType(ProjectionType.KEYS_ONLY)));

				request.setLocalSecondaryIndexes(localSecondaryIndexes);
			}

			request.setAttributeDefinitions(attributeDefinitions);

			System.out.println("Issuing CreateTable request for " + tableName);
			Table table = dynamoDB.createTable(request);
			System.out.println("Waiting for " + tableName + " to be created...this may take a while...");
			table.waitForActive();

		} catch (Exception e) {
			System.err.println("CreateTable request failed for " + tableName);
			System.err.println(e.getMessage());
		}
	}
	
}

