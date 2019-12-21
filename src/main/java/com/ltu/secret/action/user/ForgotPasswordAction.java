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
package com.ltu.secret.action.user;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.google.gson.JsonObject;
import com.ltu.secret.action.AbstractSecretAction;
import com.ltu.secret.configuration.ExceptionMessages;
import com.ltu.secret.constants.Constants;
import com.ltu.secret.dao.factory.DAOFactory;
import com.ltu.secret.exception.BadRequestException;
import com.ltu.secret.exception.CommonException;
import com.ltu.secret.exception.DAOException;
import com.ltu.secret.exception.InternalErrorException;
import com.ltu.secret.model.action.user.FogotPasswordRequest;
import com.ltu.secret.model.action.user.UserResponse;
import com.ltu.secret.model.user.User;
import com.ltu.secret.model.user.UserDAO;
import com.ltu.secret.utils.MailUtil;
import com.ltu.secret.utils.RandomUtil;


/**
 * The Class ForgotPasswordAction.
 * @author uyphu
 * created on May 22, 2017
 */
public class ForgotPasswordAction extends AbstractSecretAction{
	
	/** The logger. */
	private LambdaLogger logger;

    /* (non-Javadoc)
     * @see com.ltu.secret.action.SecretAction#handle(com.google.gson.JsonObject, com.amazonaws.services.lambda.runtime.Context)
     */
    @Override
	public String handle(JsonObject request, Context lambdaContext) throws BadRequestException, InternalErrorException {
        logger = lambdaContext.getLogger();
        
        FogotPasswordRequest input = getGson().fromJson(request, FogotPasswordRequest.class);
        User user = validateInput(input);
        user = callAPI(user);

        return output(user);
    }

	/**
	 * Output.
	 *
	 * @param user the user
	 * @return the string
	 */
	private String output(User user) {
		UserResponse output = new UserResponse();
        output.setItem(user);
        return getGson().toJson(output);
	}

	/**
	 * Call API.
	 *
	 * @param user the user
	 * @return the user
	 * @throws InternalErrorException the internal error exception
	 */
	private User callAPI(User user) throws InternalErrorException {
		UserDAO dao = DAOFactory.getUserDAO();
		String changePasswordKey = RandomUtil.generateActivationKey(Constants.NUM_COUNT);
    	user.setActivateCode(changePasswordKey);
    	try {
    		String message = buildMessage(user.getEmail(), changePasswordKey);  
    		MailUtil.sendEmail(user.getEmail(), "[Secret App] Change password key", message);
    		user = dao.update(user);
		} catch (CommonException ce) {
			logger.log("Error sending email " + ce.getMessage());
			throw new InternalErrorException(ExceptionMessages.EX_SEND_MAIL_ERROR);
		} catch (DAOException ex) {
			logger.log("Error update user " + ex.getMessage());
			throw new InternalErrorException(ExceptionMessages.EX_UPDATE_DEVICE);
		}
		return user;
	}
	
	/**
	 * Builds the message.
	 *
	 * @param email the email
	 * @param changePasswordKey the change password key
	 * @return the string
	 */
	public static String buildMessage(String email, String changePasswordKey) {
		StringBuilder builder = new StringBuilder();
//		builder.append("Hi, \nIn order to change password, please go to this link: ");
//		builder.append(S3ResourceLoaderUtil.getProperty(AppConfiguration.WEB_URL_KEY));
//		builder.append("changepassword/?email="+email+"&changekey="+changePasswordKey);
		builder.append("Hi, \nIn order to change password, please use change key: ");
		builder.append(changePasswordKey);
		return builder.toString();
	}

	/**
	 * Validate input.
	 *
	 * @param input the input
	 * @return the user
	 * @throws BadRequestException the bad request exception
	 */
	private User validateInput(FogotPasswordRequest input) throws BadRequestException {
		if (input == null ||
                input.getEmail() == null ||
                input.getEmail().trim().equals("")) {
            throw new BadRequestException(ExceptionMessages.EX_INVALID_INPUT);
        }
		
		UserDAO dao = DAOFactory.getUserDAO();
        User user = dao.findByEmail(input.getEmail());
        
        if (user == null || !Constants.YES_STATUS.equals(user.getStatus())){
            throw new BadRequestException(ExceptionMessages.EX_PARAM_EMAIL_NOT_EXIST_OR_ACTIVATED);
		} 
        
        if (!Constants.USER_TYPE.equals(user.getType())){
            throw new BadRequestException(ExceptionMessages.EX_PARAM_LOGIN_WITH_FACEBOOK_OR_GOOGLE);
		} 
        
        return user;
	}
    
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		System.out.println(buildMessage("uyphu@yahoo.com", "34342"));
	}

}

