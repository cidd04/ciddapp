package com.cidd.sentiment.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import botdetect.web.Captcha;

import com.cidd.sentiment.dto.MessageHelper;
import com.cidd.sentiment.dto.Signup;
import com.cidd.sentiment.model.Account;
import com.cidd.sentiment.service.business.UserService;
import com.cidd.sentiment.service.common.MailerService;

@Controller
public class LoginController {

	private static final String SIGNUP_VIEW_NAME = "signup";
	private static final String CAPTCHA_NAME = "captchaCodeImage";
	private static final String REDIRECT_HOME = "redirect:/";

	@Autowired
	private UserService userService;

	@Autowired
	private MailerService mailerService;
	
	/**
	 * The signin page
	 */
	@RequestMapping(value = "signin")
	public void signin() {
	}

	/**
	 * 
	 * If this is called from callback made by social api, connection is read
	 * and signup form will be pre-populated. Otherwise connection from social
	 * api will be null and method will just redirect to signup page.
	 * 
	 */
	@RequestMapping(value = "signup")
	public String signup(Model model, WebRequest request, Boolean social) {
		Signup signup = new Signup();
//		Connection<?> connection = providerSignInUtils.getConnectionFromSession(request);
//		if (connection != null) {
//			Facebook facebook = (Facebook) connection.getApi();
//			User profile = facebook.fetchObject("me", User.class, "id", "first_name", "last_name", "email");
//			UserProfile socialMediaProfile = new UserProfileBuilder().setName(profile.getName())
//					.setFirstName(profile.getFirstName()).setLastName(profile.getLastName())
//					.setEmail(profile.getEmail()).build();
//			// UserProfile socialMediaProfile = connection.fetchUserProfile();
//			String name = StringUtils.isBlank(socialMediaProfile.getFirstName()) ? socialMediaProfile.getLastName() : 
//							socialMediaProfile.getFirstName() + " " + socialMediaProfile.getLastName();
//			signup.setName(name.trim());
//			signup.setEmail(socialMediaProfile.getEmail());
//			if (social == null || social) {
//				signup.setIsSocialSignup(true);
//				MessageHelper.addSuccessAttribute(model, "user.facebook.signin");
//			}			
//		}
		model.addAttribute(signup);
		return SIGNUP_VIEW_NAME;
	}

	/**
	 * 
	 * Signup post will create a new account. When signing up using social api,
	 * account values are saved in the database Upon signup, captcha value is
	 * validated. Successful account creation will send an email notification to
	 * the user.
	 * 
	 */
	@RequestMapping(value = "signup", method = RequestMethod.POST)
	public String signup(@Valid @ModelAttribute Signup signup, Errors errors, Model model,
			HttpServletRequest httpRequest, WebRequest request, RedirectAttributes ra) {
		if (errors.hasErrors()) {
			if (signup.getIsSocialSignup() != null && signup.getIsSocialSignup()) {
				MessageHelper.addSuccessAttribute(model, "user.facebook.signin");
			}
			return SIGNUP_VIEW_NAME;
		}
		if (!(signup.getIsSocialSignup() != null && signup.getIsSocialSignup())) {
			// validate the Captcha to check we're not dealing with a bot
			Captcha captcha = Captcha.load(httpRequest, CAPTCHA_NAME);
			boolean isHuman = captcha.validate(httpRequest, signup.getCaptchaCodeTextBox());
			if (!isHuman) {
				MessageHelper.addErrorAttribute(model, "retype.captcha");
				return SIGNUP_VIEW_NAME;
			}
		}
		
		if (!signup.getPassword().equals(signup.getPassword2())) {
			MessageHelper.addErrorAttribute(model, "retype.mismatch");
			return SIGNUP_VIEW_NAME;
		}
		// save user in account and UserConnection table
		try {
//			Connection<?> connection = providerSignInUtils.getConnectionFromSession(request);
//			if (connection != null) {
//				Facebook facebook = (Facebook) connection.getApi();
//				User profile = facebook.fetchObject("me", User.class, "id");
//				String accountImageUrl = userService.saveAccountImage(
//						facebook.fetchImage(profile.getId(), "picture", ImageType.SQUARE));
//				signup.setAccountImageUrl(accountImageUrl);
//			}
			Account account = userService.saveNewAccount(signup.createAccount());
//			providerSignInUtils.doPostSignUp(account.getEmail(), request);
			if (signup.getIsSocialSignup() != null && signup.getIsSocialSignup()) {
				userService.signin(account);
			} else {
				MessageHelper.addSuccessAttribute(ra, "user.creation.successful");
			}
			//mailerService.sendGreetings(account, hash);
		} catch (Exception e) {
			MessageHelper.addErrorAttribute(model, "unique.username.email");
			return SIGNUP_VIEW_NAME;
		}
		return REDIRECT_HOME;
	}
	
}
