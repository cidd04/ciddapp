package com.cidd.sentiment.dto;

import org.hibernate.validator.constraints.*;

import com.cidd.sentiment.model.Account;

public class Signup {

	private static final String NOT_BLANK_MESSAGE = "{notBlank.message}";
	private static final String EMAIL_MESSAGE = "{email.message}";
	
	@NotBlank(message = Signup.NOT_BLANK_MESSAGE)
	private String name;

	@NotBlank(message = Signup.NOT_BLANK_MESSAGE)
	@Email(message = Signup.EMAIL_MESSAGE)
	private String email;

    @NotBlank(message = Signup.NOT_BLANK_MESSAGE)
	private String password;
    
    @NotBlank(message = Signup.NOT_BLANK_MESSAGE)
	private String password2;
    
    private String captchaCodeTextBox;
    
    private String referredBy;
    
    private Boolean isSocialSignup;
    
    private String accountImageUrl;

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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
	
	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public String getCaptchaCodeTextBox() {
		return captchaCodeTextBox;
	}

	public void setCaptchaCodeTextBox(String captchaCodeTextBox) {
		this.captchaCodeTextBox = captchaCodeTextBox;
	}

	public String getReferredBy() {
		return referredBy;
	}

	public void setReferredBy(String referredBy) {
		this.referredBy = referredBy;
	}

	public Boolean getIsSocialSignup() {
		return isSocialSignup;
	}

	public void setIsSocialSignup(Boolean isSocialSignup) {
		this.isSocialSignup = isSocialSignup;
	}

	public String getAccountImageUrl() {
		return accountImageUrl;
	}

	public void setAccountImageUrl(String accountImageUrl) {
		this.accountImageUrl = accountImageUrl;
	}

	public Account createAccount() {
        return new Account(getName(), getEmail(), getPassword(), "ROLE_USER", getAccountImageUrl());
	}
}
