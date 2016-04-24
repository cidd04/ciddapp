package com.cidd.sentiment.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.social.security.SocialUser;

import com.cidd.sentiment.common.SocialMediaService;

public class SimpleSocialUser extends SocialUser {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String email;
	
	private String role;
	
	private String name;
	
	private String accountImageUrl;
	
	private SocialMediaService socialSignInProvider;
	
	public SimpleSocialUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public SocialMediaService getSocialSignInProvider() {
		return socialSignInProvider;
	}

	public void setSocialSignInProvider(SocialMediaService socialSignInProvider) {
		this.socialSignInProvider = socialSignInProvider;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccountImageUrl() {
		return accountImageUrl;
	}

	public void setAccountImageUrl(String accountImageUrl) {
		this.accountImageUrl = accountImageUrl;
	}
}
