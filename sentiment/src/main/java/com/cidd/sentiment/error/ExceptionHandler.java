package com.cidd.sentiment.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * General error handler for the application.
 */
@ControllerAdvice
class ExceptionControllerHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(ExceptionControllerHandler.class);


	/**
	 * Handle exceptions thrown by handlers.
	 */
	@ExceptionHandler(value = Exception.class)
	public ModelAndView exception(Exception exception, WebRequest request) {
		ModelAndView modelAndView = new ModelAndView("generalError");
		logger.error("An error has occured", exception);
		modelAndView.addObject("errorMessage", exception.getMessage());
		return modelAndView;
	}
	
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ModelAndView exceptionx(MethodArgumentNotValidException exception, WebRequest request) {
		ModelAndView modelAndView = new ModelAndView("generalError");
		logger.error("An error has occured", exception);
		modelAndView.addObject("errorMessage", exception.getMessage());
		return modelAndView;
	}
}