package com.ltu.secret.model.action.user;


/**
 * The Class InsertDeviceRequest.
 */
public class RegisterUserRequest {

	private String email;
	private String password;
	private String displayName;
	private String imageUrl;
	private String secretKey;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public RegisterUserRequest() {
		
	}
	
	public String getSecretKey() {
		return secretKey;
	}
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
	public RegisterUserRequest(String email, String password, String displayName, String imageUrl, String secretKey) {
		super();
		this.email = email;
		this.password = password;
		this.displayName = displayName;
		this.imageUrl = imageUrl;
		this.secretKey = secretKey;
	}
	
}
