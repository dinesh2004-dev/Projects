package com.farmSystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.farmSystem.service.EmailSevice;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailSevice {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private TemplateEngine templateEngine;
	@Override
	public void sendSimpleMessage(String to, String subject, String text) throws MessagingException {
		// TODO Auto-generated method stub
		
		Context context = new Context();
		context.setVariable("name", "John");
		context.setVariable("otp", "123456");

		String html = templateEngine.process("emailTemplate", context);

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setTo(to);
		helper.setSubject("Email with Template");
		helper.setText(html, true);

		mailSender.send(message);


	}

}
