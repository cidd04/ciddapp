package com.cidd.sentiment.common;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.cidd.sentiment.service.common.ValidatorService;

@Target({ ElementType.FIELD, ElementType.METHOD, 
	ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValueExistValidator.class)
@Documented
public @interface ValueExist {

	String message() default "{com.cidd.sentiment.product.ValueExist.message}";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

	Class<? extends ValidatorService<?>> serviceClass();

	@Target({ ElementType.FIELD, ElementType.METHOD, 
		ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	@interface List {
		ValueExist[] value();
	}
}

