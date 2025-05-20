package com.farmSystem.service.impl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.farmSystem.DTO.BookingsDTO;
import com.farmSystem.Util.DateFormatUtil;
import com.farmSystem.service.EmailService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;

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
	
	@Override
	public void sendBookingConfirmed(BookingsDTO booking) {
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		Map<String, Object> payload = new HashMap<>();
		payload.put("renterEmail", booking.getRenterDTO().getEmailId());
		payload.put("renterName", booking.getRenterDTO().getFullName());
		payload.put("equipmentName", booking.getEquipmentDTO().getName());
		payload.put("startDate", booking.getStart_date().toString());
		payload.put("endDate", booking.getEnd_date().toString());

		HttpEntity<Map<String, Object>> request = new HttpEntity<>(payload, headers);

		String webhookUrl = "http://localhost:5678/webhook/send-booking-email";
		restTemplate.postForEntity(webhookUrl, request, String.class);
	}

}
