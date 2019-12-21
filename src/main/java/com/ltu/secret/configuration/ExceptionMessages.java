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
package com.ltu.secret.configuration;

// TODO: Auto-generated Javadoc
/**
 * Static list of error messages when exceptions are thrown.
 * @author uy phu
 * created on May 19, 2017
 */
public class ExceptionMessages {
    
	public static final String EX_INVALID_INPUT = "Invalid input parameters";
    public static final String EX_PWD_SALT = "Cannot generate password salt";
    public static final String EX_PWD_ENCRYPT = "Failed to encrypt password";
    public static final String EX_PWD_SAVE = "Failed to save password";
    public static final String EX_NO_COGNITO_IDENTITY = "Cannot retrieve Cognito identity";
    public static final String EX_DAO_ERROR = "Error in DAO";
    public static final String EX_SEND_MAIL_ERROR = "Error send email";
    
    public static final String EX_DUPLICATED_ITEM = "Duplicated item";
    
    public static final String EX_USER_NOT_ACTIVATED = "User not activated";
    public static final String EX_PASSWORD_INCORRECT = "Password incorrect!";
    public static final String EX_CREATE_TOKBOX_SESSION = "Error creating session.";
    public static final String EX_CREATE_TOKBOX_TOKEN = "Error creating token.";
    
    public static final String EX_SEND_NOTIFICATION = "Error sending notification";
    public static final String EX_SEND_INVITATION = "Error sending invitation email";
    public static final String EX_USER_NOT_FOUND = "User not found";
    public static final String EX_UPDATE_DEVICE = "Error update device";
    public static final String EX_EMAIL_EXIST = "Email exists.";
    public static final String EX_UPDATE_CONFERENCE = "Error update conference";
    public static final String EX_UPDATE_USER = "Error update user";
    public static final String EX_CHANGE_PASSWORD= "Error change password";
    public static final String EX_LOGIN_FACEBOOK= "Error login with Facebook. Email was registered";
    public static final String EX_REGISTER_FACEBOOK= "Error registering Facebook user";
    public static final String EX_LOGIN_GOOGLE= "Error login with Google. Email was registered";
    public static final String EX_REGISTER_GOOGLE= "Error registering Google user";
    public static final String EX_CLOSED_CONFERENCE= "Closed conference";
    public static final String EX_LEFT_CONFERENCE = "Left confernce";
    public static final String EX_KICKED_OUT_CONFERENCE = "Kicked out confernce";
    public static final String EX_CONFERENCE_NOT_FOUND = "Conference not found";
    public static final String EX_CONFERENCE_ALREADY_ENDED = "Conference already ended";
    public static final String EX_EMAIL_NOT_EXIST = "Email not exist";
    public static final String EX_PUSH_NOTIFICATION = "Error pushing notification";
    
    public static final String EX_PARAM_TYPE_INVALID = "Type Invalid";
    public static final String EX_PARAM_DATE_INVALID = "Date Invalid. current < start < end";
    
    //Insert Event
    public static final String EX_PARAM_EMAIL_REQUIRED = "Email required";
    public static final String EX_PARAM_CREATED_BY_REQUIRED = "CreatedBy required";
    public static final String EX_PARAM_START_DATE_REQUIRED = "StartDate required";
    public static final String EX_PARAM_END_DATE_REQUIRED = "EndDate required";
    public static final String EX_PARAM_TITLE_REQUIRED = "Title required";
    public static final String EX_EVENT_NOT_FOUND = "Event not found";
    public static final String EX_NOT_ALLOWED_UPDATE = "Not allowed to update";
    
    public static final String EX_PARAM_QUERY_REQUIRED = "Query required";
    
    public static final String EX_USER_OF_NUM_EXCEEDED = "Number of users exceed";
    
    //Insert Contact
    public static final String EX_PARAM_OWNER_ID_REQUIRED = "OwnerId required";
    public static final String EX_PARAM_USER_ID_REQUIRED = "UserId required";
    public static final String EX_PARAM_RECEIVER_ID_REQUIRED = "ReceiverId required";
    public static final String EX_PARAM_SENDER_ID_REQUIRED = "ReceiverId required";
    public static final String EX_CONTACT_NOT_FOUND = "Contact not found";
    
    //User
    public static final String EX_PARAM_EMAIL_NOT_EXIST_OR_ACTIVATED = "Email not exist or activated";
    public static final String EX_PARAM_DISPLAYED_NAME_REQUIRED = "DisplayedName required";
    public static final String EX_PARAM_SECRET_KEY_REQUIRED = "Secret Key required";
    public static final String EX_PARAM_TOKEN_REQUIRED = "Token required";
    
    public static final String EX_PARAM_LOGIN_WITH_FACEBOOK_OR_GOOGLE = "User login with facebook or google";
    
    public static final String EX_PARAM_EMAIL_INVALID = " Email invalid";
    
    public static final String EX_AUTHENTICATE_GOOGLE = "Error authenticate google";
    public static final String EX_AUTHENTICATE_FACEBOOK = "Error authenticate facebook";
    
    public static final String EX_PARAM_FACEBOOK_ID_REQUIRED = "Facebook Id required";
    public static final String EX_PARAM_GOOGLE_ID_REQUIRED = "Google Id required";
    public static final String EX_PARAM_ID_REQUIRED = "Id required";
    
    public static final String EX_PARAM_EMAIL_NOT_FOUND = " not found. please add contact first.";
    public static final String EX_PARAM_PASSWORD_REQUIRED = "Password required";
    public static final String EX_EMAIL_INVALID = "Email invalid";
    public static final String EX_PARAM_CHANGE_KEY_REQUIRED = "Change Key required";
    public static final String EX_CHANGE_KEY_INVALID = "Change Key invalid";
    
    //General
    public static final String EX_UNDER_DEVELOPMENT = "This feature is currently under development";
    
}
