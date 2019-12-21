package com.ltu.secret.service.dynamodb;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.ltu.secret.configuration.AppConfiguration;
import com.ltu.secret.utils.S3ResourceLoaderUtil;

/**
 * The Class AmazonDynamoDBClientUtil.
 * @author uyphu
 */
public class AmazonDynamoDBClientUtil {

	/** The dynamo db. */
	private static AmazonDynamoDBClient dynamoDB = null;

	/**
	 * Gets the single instance of AmazonDynamoDBClientUtil.
	 *
	 * @return single instance of AmazonDynamoDBClientUtil
	 */
	public static AmazonDynamoDBClient getInstance() {
		if (dynamoDB == null) {
			dynamoDB = new AmazonDynamoDBClient();		
			String region = S3ResourceLoaderUtil.getProperty(AppConfiguration.REGION_KEY);
			if (region != null) {
				dynamoDB.setRegion(Region.getRegion(Regions.fromName(region)));
			}
			
		}
		return dynamoDB;
	}
	
}
