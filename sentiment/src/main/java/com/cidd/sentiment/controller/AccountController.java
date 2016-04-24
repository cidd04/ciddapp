package com.cidd.sentiment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cidd.sentiment.model.Account;
import com.cidd.sentiment.service.business.UserService;

@Controller
class AccountController {

	@Autowired
	UserService userService;

	/**
	 * 
	 * REST call. Get user by id.
	 * 
	 */
	@RequestMapping(value = "/api/user/display/{userId}", method = RequestMethod.GET)
	@ResponseBody
	public Account displayUser(@PathVariable Integer userId) {
		Account account = userService.getUserById(userId);
		return account;
	}
	
}
