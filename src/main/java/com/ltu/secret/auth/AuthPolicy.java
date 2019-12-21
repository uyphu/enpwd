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
package com.ltu.secret.auth;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * AuthPolicy receives a set of allowed and denied methods and generates a valid
 * AWS policy for the API Gateway authorizer. The constructor receives the calling
 * user principal, the AWS account ID of the API owner, and an apiOptions object.
 * The apiOptions can contain an API Gateway RestApi Id, a region for the RestApi, and a
 * stage that calls should be allowed/denied for. For example
 *
 * new AuthPolicy(principalId, AuthPolicy.PolicyDocument.getDenyAllPolicy(region, awsAccountId, restApiId, stage));
 *
 * @author uyphu
 * created on May 20, 2017
 */
public class AuthPolicy {

    /** The Constant VERSION. */
    // IAM Policy Constants
    public static final String VERSION = "Version";
    
    /** The Constant STATEMENT. */
    public static final String STATEMENT = "Statement";
    
    /** The Constant EFFECT. */
    public static final String EFFECT = "Effect";
    
    /** The Constant ACTION. */
    public static final String ACTION = "Action";
    
    /** The Constant NOT_ACTION. */
    public static final String NOT_ACTION = "NotAction";
    
    /** The Constant RESOURCE. */
    public static final String RESOURCE = "Resource";
    
    /** The Constant NOT_RESOURCE. */
    public static final String NOT_RESOURCE = "NotResource";
    
    /** The Constant CONDITION. */
    public static final String CONDITION = "Condition";

    /** The principal id. */
    String principalId;
    
    /** The policy document object. */
    transient AuthPolicy.PolicyDocument policyDocumentObject;
    
    /** The policy document. */
    Map<String, Object> policyDocument;

    /**
     * Instantiates a new auth policy.
     *
     * @param principalId the principal id
     * @param policyDocumentObject the policy document object
     */
    public AuthPolicy(String principalId, PolicyDocument policyDocumentObject) {
        this.principalId = principalId;
        this.policyDocumentObject = policyDocumentObject;
    }

    /**
     * Instantiates a new auth policy.
     */
    public AuthPolicy() {
    }

    /**
     * Gets the principal id.
     *
     * @return the principal id
     */
    public String getPrincipalId() {
        return principalId;
    }

    /**
     * Sets the principal id.
     *
     * @param principalId the new principal id
     */
    public void setPrincipalId(String principalId) {
        this.principalId = principalId;
    }

    /**
     * IAM Policies use capitalized field names, but Lambda by default will serialize object members using camel case
     * 
     * This method implements a custom serializer to return the IAM Policy as a well-formed JSON document, with the correct field names.
     *
     * @return IAM Policy as a well-formed JSON document
     */
    @SuppressWarnings("unchecked")
	public Map<String, Object> getPolicyDocument() {
        Map<String, Object> serializablePolicy = new HashMap<>();
        serializablePolicy.put(VERSION, policyDocumentObject.Version);
        Statement[] statements = policyDocumentObject.getStatement();
        Map<String, Object>[] serializableStatementArray = new Map[statements.length];
        for (int i = 0; i < statements.length; i++) {
            Map<String, Object> serializableStatement = new HashMap<>();
            AuthPolicy.Statement statement = statements[i];
            serializableStatement.put(EFFECT, statement.Effect);
            serializableStatement.put(ACTION, statement.Action);
            serializableStatement.put(RESOURCE, statement.getResource());
            serializableStatement.put(CONDITION, statement.getCondition());
            serializableStatementArray[i] = serializableStatement;
        }
        serializablePolicy.put(STATEMENT, serializableStatementArray);
        return serializablePolicy;
    }

    /**
     * Sets the policy document.
     *
     * @param policyDocumentObject the new policy document
     */
    public void setPolicyDocument(PolicyDocument policyDocumentObject) {
        this.policyDocumentObject = policyDocumentObject;
    }

    /**
     * PolicyDocument represents an IAM Policy, specifically for the execute-api:Invoke action
     * in the context of a API Gateway Authorizer
     * 
     * Initialize the PolicyDocument with
     *   the region where the RestApi is configured,
     *   the AWS Account ID that owns the RestApi,
     *   the RestApi identifier
     *   and the Stage on the RestApi that the Policy will apply to.
     */
    public static class PolicyDocument {

        /** The Constant EXECUTE_API_ARN_FORMAT. */
        static final String EXECUTE_API_ARN_FORMAT = "arn:aws:execute-api:%s:%s:%s/%s/%s/%s";

        /** The Version. */
        String Version = "2012-10-17"; // override if necessary

        /** The allow statement. */
        private Statement allowStatement;
        
        /** The deny statement. */
        private Statement denyStatement;
        
        /** The statements. */
        private List<Statement> statements;

        /** The region. */
        // context metadata
        transient String region;
        
        /** The aws account id. */
        transient String awsAccountId;
        
        /** The rest api id. */
        transient String restApiId;
        
        /** The stage. */
        transient String stage;

        /**
         * Creates a new PolicyDocument with the given context,
         * and initializes two base Statement objects for allowing and denying access to API Gateway methods.
         *
         * @param region the region where the RestApi is configured
         * @param awsAccountId the AWS Account ID that owns the RestApi
         * @param restApiId the RestApi identifier
         * @param stage and the Stage on the RestApi that the Policy will apply to
         */
        public PolicyDocument(String region, String awsAccountId, String restApiId, String stage) {
            this.region = region;
            this.awsAccountId = awsAccountId;
            this.restApiId = restApiId;
            this.stage = stage;
            allowStatement = Statement.getEmptyInvokeStatement("Allow");
            denyStatement = Statement.getEmptyInvokeStatement("Deny");
            this.statements = new ArrayList<>();
        }

        /**
         * Gets the version.
         *
         * @return the version
         */
        public String getVersion() {
            return Version;
        }

        /**
         * Sets the version.
         *
         * @param version the new version
         */
        public void setVersion(String version) {
            Version = version;
        }

        /**
         * Gets the statement.
         *
         * @return the statement
         */
        public AuthPolicy.Statement[] getStatement() {
            statements.add(allowStatement);
            statements.add(denyStatement);
            return statements.toArray(new AuthPolicy.Statement[statements.size()]);
        }

        /**
         * Allow method.
         *
         * @param httpMethod the http method
         * @param resourcePath the resource path
         */
        public void allowMethod(HttpMethod httpMethod, String resourcePath) {
            addResourceToStatement(allowStatement, httpMethod, resourcePath);
        }

        /**
         * Deny method.
         *
         * @param httpMethod the http method
         * @param resourcePath the resource path
         */
        public void denyMethod(HttpMethod httpMethod, String resourcePath) {
            addResourceToStatement(denyStatement, httpMethod, resourcePath);
        }

        /**
         * Adds the statement.
         *
         * @param statement the statement
         */
        public void addStatement(AuthPolicy.Statement statement) {
            statements.add(statement);
        }

        /**
         * Adds the resource to statement.
         *
         * @param statement the statement
         * @param httpMethod the http method
         * @param resourcePath the resource path
         */
        private void addResourceToStatement(Statement statement, HttpMethod httpMethod, String resourcePath) {
            // resourcePath must start with '/'
            // to specify the root resource only, resourcePath should be an empty string
            if (resourcePath.equals("/")) {
                resourcePath = "";
            }
            String resource = resourcePath.startsWith("/") ? resourcePath.substring(1) : resourcePath;
            String method = httpMethod == HttpMethod.ALL ? "*" : httpMethod.toString();
            statement.addResource(String.format(EXECUTE_API_ARN_FORMAT, region, awsAccountId, restApiId, stage, method, resource));
        }

        // Static methods

        /**
         * Generates a new PolicyDocument with a single statement that allows the requested method/resourcePath.
         *
         * @param region API Gateway region
         * @param awsAccountId AWS Account that owns the API Gateway RestApi
         * @param restApiId RestApi identifier
         * @param stage Stage name
         * @param method HttpMethod to allow
         * @param resourcePath Resource path to allow
         * @return new PolicyDocument that allows the requested method/resourcePath
         */
        public static PolicyDocument getAllowOnePolicy(String region, String awsAccountId, String restApiId, String stage, HttpMethod method, String resourcePath) {
            AuthPolicy.PolicyDocument policyDocument = new AuthPolicy.PolicyDocument(region, awsAccountId, restApiId, stage);
            policyDocument.allowMethod(method, resourcePath);
            return policyDocument;

        }


        /**
         * Generates a new PolicyDocument with a single statement that denies the requested method/resourcePath.
         *
         * @param region API Gateway region
         * @param awsAccountId AWS Account that owns the API Gateway RestApi
         * @param restApiId RestApi identifier
         * @param stage Stage name
         * @param method HttpMethod to deny
         * @param resourcePath Resource path to deny
         * @return new PolicyDocument that denies the requested method/resourcePath
         */
        public static PolicyDocument getDenyOnePolicy(String region, String awsAccountId, String restApiId, String stage, HttpMethod method, String resourcePath) {
            AuthPolicy.PolicyDocument policyDocument = new AuthPolicy.PolicyDocument(region, awsAccountId, restApiId, stage);
            policyDocument.denyMethod(method, resourcePath);
            return policyDocument;

        }

        /**
         * Gets the allow all policy.
         *
         * @param region the region
         * @param awsAccountId the aws account id
         * @param restApiId the rest api id
         * @param stage the stage
         * @return the allow all policy
         */
        public static AuthPolicy.PolicyDocument getAllowAllPolicy(String region, String awsAccountId, String restApiId, String stage) {
            return getAllowOnePolicy(region, awsAccountId, restApiId, stage, HttpMethod.ALL, "*");
        }

        /**
         * Gets the deny all policy.
         *
         * @param region the region
         * @param awsAccountId the aws account id
         * @param restApiId the rest api id
         * @param stage the stage
         * @return the deny all policy
         */
        public static PolicyDocument getDenyAllPolicy(String region, String awsAccountId, String restApiId, String stage) {
            return getDenyOnePolicy(region, awsAccountId, restApiId, stage, HttpMethod.ALL, "*");
        }

		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "PolicyDocument [Version=" + Version + ", allowStatement=" + allowStatement + ", denyStatement="
					+ denyStatement + ", statements=" + statements + "]";
		}
        
    }

    /**
     * The Enum HttpMethod.
     */
    enum HttpMethod {
        
        /** The get. */
        GET, 
 /** The post. */
 POST, 
 /** The put. */
 PUT, 
 /** The delete. */
 DELETE, 
 /** The patch. */
 PATCH, 
 /** The head. */
 HEAD, 
 /** The options. */
 OPTIONS, 
 /** The all. */
 ALL
    }

    /**
     * The Class Statement.
     */
    static class Statement {

        /** The Effect. */
        String Effect;
        
        /** The Action. */
        String Action;
        
        /** The Condition. */
        Map<String, Map<String, Object>> Condition;

        /** The resource list. */
        private List<String> resourceList;

        /**
         * Instantiates a new statement.
         */
        public Statement() {

        }

        /**
         * Instantiates a new statement.
         *
         * @param effect the effect
         * @param action the action
         * @param resourceList the resource list
         * @param condition the condition
         */
        public Statement(String effect, String action, List<String> resourceList, Map<String, Map<String, Object>> condition) {
            this.Effect = effect;
            this.Action = action;
            this.resourceList = resourceList;
            this.Condition = condition;
        }
        
        /**
         * Gets the empty invoke statement.
         *
         * @param effect the effect
         * @return the empty invoke statement
         */
        public static Statement getEmptyInvokeStatement(String effect) {
            return new Statement(effect, "execute-api:Invoke", new ArrayList<String>(), new HashMap<String, Map<String, Object>>());
        }

        /**
         * Gets the effect.
         *
         * @return the effect
         */
        public String getEffect() {
            return Effect;
        }

        /**
         * Sets the effect.
         *
         * @param effect the new effect
         */
        public void setEffect(String effect) {
            this.Effect = effect;
        }

        /**
         * Gets the action.
         *
         * @return the action
         */
        public String getAction() {
            return Action;
        }

        /**
         * Sets the action.
         *
         * @param action the new action
         */
        public void setAction(String action) {
            this.Action = action;
        }

        /**
         * Gets the resource.
         *
         * @return the resource
         */
        public String[] getResource() {
            return resourceList.toArray(new String[resourceList.size()]);
        }

        /**
         * Adds the resource.
         *
         * @param resource the resource
         */
        public void addResource(String resource) {
            resourceList.add(resource);
        }

        /**
         * Gets the condition.
         *
         * @return the condition
         */
        public Map<String, Map<String,Object>> getCondition() {
            return Condition;
        }

        /**
         * Adds the condition.
         *
         * @param operator the operator
         * @param key the key
         * @param value the value
         */
        public void addCondition(String operator, String key, Object value) {
            Condition.put(operator, Collections.singletonMap(key, value));
        }

    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AuthPolicy [principalId=" + principalId + ", policyDocument=" + policyDocument + "]";
	}
}
