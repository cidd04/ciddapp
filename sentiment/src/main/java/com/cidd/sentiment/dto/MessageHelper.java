package com.cidd.sentiment.dto;

import static com.cidd.sentiment.dto.Message.MESSAGE_ATTRIBUTE;
import static com.cidd.sentiment.dto.Message.MESSAGE_LIST_ATTRIBUTE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public final class MessageHelper {

    private MessageHelper() {

    }
    
    public static void addErrorAttribute(RedirectAttributes ra, Exception e) {
    	if (e instanceof ConstraintViolationException) {
    		ConstraintViolationException cve = (ConstraintViolationException) e;
    		List<Message> messages = new ArrayList<>();
    		for (ConstraintViolation<?> cv : cve.getConstraintViolations()) {
    			messages.add(new Message(cv.getMessage(), Message.Type.DANGER));
			}
			MessageHelper.addAttribute(ra, messages);
    	}
    }
    
    public static void addErrorAttribute(Model model, Exception e) {
    	if (e instanceof ConstraintViolationException) {
    		ConstraintViolationException cve = (ConstraintViolationException) e;
    		List<Message> messages = new ArrayList<>();
    		for (ConstraintViolation<?> cv : cve.getConstraintViolations()) {
    			messages.add(new Message(cv.getMessage(), Message.Type.DANGER));
			}
			MessageHelper.addAttribute(model, messages);
    	}
    }

    public static void addSuccessAttribute(RedirectAttributes ra, String message, Object... args) {
        addAttribute(ra, message, Message.Type.SUCCESS, args);
    }

    public static void addErrorAttribute(RedirectAttributes ra, String message, Object... args) {
        addAttribute(ra, message, Message.Type.DANGER, args);
    }

    public static void addInfoAttribute(RedirectAttributes ra, String message, Object... args) {
        addAttribute(ra, message, Message.Type.INFO, args);
    }

    public static void addWarningAttribute(RedirectAttributes ra, String message, Object... args) {
        addAttribute(ra, message, Message.Type.WARNING, args);
    }

    public static void addSuccessAttribute(Model model, String message, Object... args) {
        addAttribute(model, message, Message.Type.SUCCESS, args);
    }

    public static void addErrorAttribute(Model model, String message, Object... args) {
        addAttribute(model, message, Message.Type.DANGER, args);
    }

    public static void addInfoAttribute(Model model, String message, Object... args) {
        addAttribute(model, message, Message.Type.INFO, args);
    }

    public static void addWarningAttribute(Model model, String message, Object... args) {
        addAttribute(model, message, Message.Type.WARNING, args);
    }

    private static void addAttribute(Model model, String message, Message.Type type, Object... args) {
        model.addAttribute(MESSAGE_ATTRIBUTE, new Message(message, type, args));
    }
    
    private static void addAttribute(RedirectAttributes ra, String message, Message.Type type, Object... args) {
    	ra.addFlashAttribute(MESSAGE_ATTRIBUTE, new Message(message, type, args));
    }
    
    private static void addAttribute(Model model, List<Message> messages) {
        model.addAttribute(MESSAGE_LIST_ATTRIBUTE, messages);
    }
    
    private static void addAttribute(RedirectAttributes ra, List<Message> messages) {
        ra.addFlashAttribute(MESSAGE_LIST_ATTRIBUTE, messages);
    }
    
    public static void returnOk(Model model) {
    	model.addAttribute("status", "ok");
    }
    
    public static void returnOk(RedirectAttributes ra) {
    	ra.addFlashAttribute("status", "ok");
    }
    
    public static void returnErrorMessages(Exception e, Model model) {
    	model.addAttribute("status", "error");
    	if (e instanceof ConstraintViolationException) {
    		ConstraintViolationException cve = (ConstraintViolationException) e;
    		List<String> messages = new ArrayList<>();
    		if (cve.getConstraintViolations() != null) {
	    		for (ConstraintViolation<?> cv : cve.getConstraintViolations()) {
	    			messages.add(cv.getMessage());
				}
    		}
    		model.addAttribute("errors", messages);
    	}    	
    }
    
    public static void returnErrorMessages(Exception e, RedirectAttributes ra) {
    	ra.addFlashAttribute("status", "error");
    	if (e instanceof ConstraintViolationException) {
    		ConstraintViolationException cve = (ConstraintViolationException) e;
    		List<String> messages = new ArrayList<>();
    		if (cve.getConstraintViolations() != null) {
	    		for (ConstraintViolation<?> cv : cve.getConstraintViolations()) {
	    			messages.add(cv.getMessage());
				}
    		}
    		ra.addFlashAttribute("errors", messages);
    	}    	
    }
    
    public static Map<String, Object> returnAsMap(Model model) {
    	return createAndCleanMap(model.asMap());
    }

    public static Map<String, Object> returnAsMap(RedirectAttributes ra) {
    	return createAndCleanMap(ra.asMap());
    }
    
    private static Map<String, Object> createAndCleanMap(Map<String, Object> map) {
    	Map<String, Object> newMap = new HashMap<>(map);
    	Iterator<Entry<String, Object>> iter = newMap.entrySet().iterator();
    	while (iter.hasNext()) {
    	    Entry<String, Object> entry = iter.next();
    	    if(entry.getKey().contains("org.springframework.validation")){
    	        iter.remove();
    	    }
    	}
    	return newMap;
    }

}
