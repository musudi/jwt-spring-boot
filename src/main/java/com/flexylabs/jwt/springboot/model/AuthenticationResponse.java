package com.flexylabs.jwt.springboot.model;

public class AuthenticationResponse {
	
	private String jwtToken;
	
	public AuthenticationResponse(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	/**
	 * @return the jwtToken
	 */
	public String getJwtToken() {
		return jwtToken;
	}
}
