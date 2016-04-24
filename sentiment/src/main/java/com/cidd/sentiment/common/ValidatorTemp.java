package com.cidd.sentiment.common;

import org.springframework.stereotype.Component;

import com.cidd.sentiment.service.common.ValidatorService;

@Component
public class ValidatorTemp implements ValidatorService<String> {

	@Override
	public Boolean exists(String value) {
		// TODO Auto-generated method stub
		return false;
	}

}
