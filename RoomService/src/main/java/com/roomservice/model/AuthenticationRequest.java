package com.roomservice.model;

public class AuthenticationRequest {
	private String userName;
	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return "AuthenticationRequest [userName=" + userName + ", password=" + password + "]";
	}

	public AuthenticationRequest() {
		super();
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public AuthenticationRequest(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

}
