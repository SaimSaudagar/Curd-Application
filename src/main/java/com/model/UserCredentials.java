package com.model;

import org.springframework.lang.NonNull;

public class UserCredentials {
	@NonNull
	private String username;
	@NonNull
	private String password;
	
	public UserCredentials(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public UserCredentials() {
		super();
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
