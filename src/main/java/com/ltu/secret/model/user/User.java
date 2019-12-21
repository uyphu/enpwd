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
package com.ltu.secret.model.user;

import java.nio.ByteBuffer;
import java.util.Date;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.ltu.secret.configuration.DynamoDBConfiguration;

/**
 * User object model - this class is annotated to be used with the DynamoDB
 * object mapper.
 * @author uyphu
 * created on May 20, 2017
 */
@DynamoDBTable(tableName = DynamoDBConfiguration.USERS_TABLE_NAME)
public class User implements Comparable<User>{
	/** The id. */
	@DynamoDBHashKey(attributeName = "id")
	@DynamoDBAutoGeneratedKey
	@Expose
	private String id;

	/** The email. */
	@DynamoDBIndexHashKey
	@DynamoDBAttribute(attributeName = "email")
	@JsonProperty(value="email", required=true)
	@Expose
	private String email;

	/** The password. */
	@DynamoDBAttribute(attributeName = "password")
	private ByteBuffer password;

	/** The salt. */
	@DynamoDBAttribute(attributeName = "salt")
	private ByteBuffer salt;

	/** The display name. */
	@DynamoDBAttribute(attributeName = "displayName")
	@Expose
	private String displayName;

	/** The profile image url. */
	@DynamoDBAttribute(attributeName = "imageUrl")
	@Expose
	private String imageUrl;

	/** The type. */
	@DynamoDBAttribute(attributeName = "type")
	@Expose
	private String type;

	/** The activate key. */
	@DynamoDBAttribute(attributeName = "activateCode")
	private String activateCode;

	/** The status. */
	@DynamoDBAttribute(attributeName = "status")
	@Expose
	private String status;
	
	/** The status. */
	@DynamoDBAttribute(attributeName = "secretKey")
	@Expose
	private String secretKey;
	
	/** The identity. */
	private UserIdentity identity;

	/** The created at. */
	@DynamoDBAttribute(attributeName = "createdAt")
	@Expose
	private Date createdAt;
	
	/**
	 * Instantiates a new user.
	 */
	public User() {

	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public ByteBuffer getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(ByteBuffer password) {
		this.password = password;
	}

	/**
	 * Gets the salt.
	 *
	 * @return the salt
	 */
	public ByteBuffer getSalt() {
		return salt;
	}

	/**
	 * Sets the salt.
	 *
	 * @param salt the new salt
	 */
	public void setSalt(ByteBuffer salt) {
		this.salt = salt;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	/**
	 * Gets the activate code.
	 *
	 * @return the activate code
	 */
	public String getActivateCode() {
		return activateCode;
	}

	/**
	 * Sets the activate code.
	 *
	 * @param activateCode the new activate code
	 */
	public void setActivateCode(String activateCode) {
		this.activateCode = activateCode;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Gets the created at.
	 *
	 * @return the created at
	 */
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * Sets the created at.
	 *
	 * @param createdAt the new created at
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * Gets the password bytes.
	 *
	 * @return the password bytes
	 */
	@DynamoDBIgnore
	public byte[] getPasswordBytes() {
		return password.array();
	}
	
	/**
	 * Gets the salt bytes.
	 *
	 * @return the salt bytes
	 */
	@DynamoDBIgnore
	public byte[] getSaltBytes() {
		return salt.array();
	}
	
	/**
	 * Gets the identity.
	 *
	 * @return the identity
	 */
	@DynamoDBIgnore
    public UserIdentity getIdentity() {
        return identity;
    }

    /**
     * Sets the identity.
     *
     * @param identity the new identity
     */
    public void setIdentity(UserIdentity identity) {
        this.identity = identity;
    }
    
    /**
     * Gets the cognito identity id.
     *
     * @return the cognito identity id
     */
    @DynamoDBAttribute(attributeName = "identityId")
    public String getCognitoIdentityId() {
        if (this.identity == null) {
            return null;
        }
        return this.identity.getIdentityId();
    }

    /**
     * Sets the cognito identity id.
     *
     * @param cognitoIdentityId the new cognito identity id
     */
    public void setCognitoIdentityId(String cognitoIdentityId) {
        if (this.identity == null) {
            this.identity = new UserIdentity();
        }
        this.identity.setIdentityId(cognitoIdentityId);
    }
    
	/**
	 * Gets the secret key.
	 *
	 * @return the secret key
	 */
	public String getSecretKey() {
		return secretKey;
	}

	/**
	 * Sets the secret key.
	 *
	 * @param secretKey the new secret key
	 */
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	/**
	 * Instantiates a new user.
	 *
	 * @param id the id
	 * @param email the email
	 * @param password the password
	 * @param salt the salt
	 * @param displayedName the displayed name
	 * @param profileImageUrl the profile image url
	 * @param pmCode the pm code
	 * @param type the type
	 * @param activateCode the activate code
	 * @param status the status
	 * @param createdAt the created at
	 */
	public User(String id, String email, ByteBuffer password, ByteBuffer salt, String displayName, String imageUrl,
			String type, String activateCode, String status, Date createdAt) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.salt = salt;
		this.displayName = displayName;
		this.imageUrl = imageUrl;
		this.type = type;
		this.activateCode = activateCode;
		this.status = status;
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", salt=" + salt + ", displayName="
				+ displayName + ", imageUrl=" + imageUrl + ", type=" + type + ", activateCode=" + activateCode
				+ ", status=" + status + ", identity=" + identity + ", createdAt=" + createdAt + "]";
	}

	@Override
	public int compareTo(User other) {
		return this.displayName.compareTo(other.displayName);
	}
	
}


