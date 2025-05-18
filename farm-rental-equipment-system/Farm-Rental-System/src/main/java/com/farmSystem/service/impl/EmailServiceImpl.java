package com.farmSystem.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.farmSystem.Util.DateFormatUtil;
import com.farmSystem.service.EmailService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	@Autowired
	private DateFormatUtil dateFormatUtil;
	
	public void sendMessage(String email,String html,String subject) {
		
		try {

			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			helper.setTo(email);
			helper.setSubject(subject);
			helper.setText(html, true);

			mailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
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
	
	@Override
	public void sendWelcomeMessage(String email){
		
		Context context = new Context();
		context.setVariable("email",email);
		
		String html = templateEngine.process("WelcomeEmailTemplate", context);
		
		sendMessage(email,html,"Welcome");
	}
	
	@Override
	public void sendBookingRequestToLender(String email,String lenderName,String userName,String EquipmentName,LocalDateTime startDate,LocalDateTime endDate,String location){
		String bookingStartDate = dateFormatUtil.formatLocalDateTimeTo(startDate);
		String bookingEndDate = dateFormatUtil.formatLocalDateTimeTo(startDate);
		
		Context context = new Context();
		context.setVariable("lenderName", lenderName);
		context.setVariable("userName", userName);
		context.setVariable("equipmentName",EquipmentName);
		context.setVariable("bookingStartDate",bookingStartDate);
		context.setVariable("bookingEndDate", bookingEndDate);
		context.setVariable("location",location);
		
		String  html = templateEngine.process("EquipmentBookingRequest", context);
		
		sendMessage(email,html,"New Booking Request");
		
		
	}
	
	@Override
	public void sendBookingSatusNotificationToRenter(String email,String renterName,String status,String lenderName,String equipmentName,LocalDateTime startDate,LocalDateTime endDate,String location) {
		
		String bookingStartDate = dateFormatUtil.formatLocalDateTimeTo(startDate);
		String bookingEndDate = dateFormatUtil.formatLocalDateTimeTo(startDate);
		
		Context context = new Context();
		
		context.setVariable("lenderName", lenderName);
		context.setVariable("userName", renterName);
		context.setVariable("equipmentName",equipmentName);
		context.setVariable("bookingStartDate",bookingStartDate);
		context.setVariable("bookingEndDate", bookingEndDate);
		context.setVariable("status", status);
		context.setVariable("location",location);
		
		String  html = templateEngine.process("LenderActionNotification", context);
		
		
		sendMessage(email,html,"Updated Booking Status");
		
		
	}

}
