package com.flexylabs.jwt.springboot.model;



public class AuthenticationRequest {
	
	private String userName;
	private String password;
	
	public AuthenticationRequest() {}
	
	public AuthenticationRequest(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}
	
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
