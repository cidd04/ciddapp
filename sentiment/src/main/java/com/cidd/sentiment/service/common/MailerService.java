package com.cidd.sentiment.service.common;

import java.util.Map;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

@Service
public class MailerService {

	private final String defaultFromName = "Pasabuy";

	@Autowired
	public JavaMailSender javaMailSender;

	@Autowired
	public VelocityEngine velocityEngine;

	public void send(String fromEmailAddress, String toEmailAddresses, String subject, String velocityTemplateName,
			Map<String, Object> model) {
		try {
			MimeMessagePreparator preparator = new MimeMessagePreparator() {
				public void prepare(MimeMessage mimeMessage) throws Exception {
					MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
					message.setTo(toEmailAddresses);
					message.setFrom(new InternetAddress(fromEmailAddress, defaultFromName));
					message.setSubject(subject);
					String body = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, velocityTemplateName,
							"UTF-8", model);
					message.setText(body, true);
				}
			};
			javaMailSender.send(preparator);
		} catch (MailException ex) {
			// simply log it and go on...
			ex.printStackTrace();
			System.err.println(ex.getMessage());
		}

	}

	@Async
	public void sendGreetings(String fromEmailAddress, String toEmailAddress, Map<String, Object> model) {
		send(fromEmailAddress, toEmailAddress, "Greetings From Pasabuy", "greetings.vm", model);
	}

}