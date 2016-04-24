package com.cidd.sentiment.common;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.cidd.sentiment.service.common.ValidatorService;

public class ValueExistValidator implements ConstraintValidator<ValueExist, Object> {
	
	private Class<? extends ValidatorService<?>> serviceClass;
	
	@Autowired
	private ApplicationContext appContext;
	
	@Override
	public void initialize(ValueExist constraintAnnotation) {
		this.serviceClass = constraintAnnotation.serviceClass();
	}
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext constraintContext) {
		@SuppressWarnings("unchecked")
		ValidatorService<Object> validatorService = 
			(ValidatorService<Object>) appContext.getBean(serviceClass);
		return validatorService.exists(value);
	}

}
