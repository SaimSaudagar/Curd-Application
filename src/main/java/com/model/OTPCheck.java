package com.model;

import org.springframework.lang.NonNull;

public class OTPCheck {
	@NonNull
	private String email;
	@NonNull
	private String otp;
	
	public OTPCheck() {
		super();
	}
	public OTPCheck(String email, String otp) {
		super();
		this.email = email;
		this.otp = otp;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getOTP() {
		return otp;
	}
	public void setOTP(String otp) {
		this.otp = otp;
	}
	
	
}
