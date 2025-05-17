package com.farmSystem.service;

import java.time.LocalDateTime;

import jakarta.mail.MessagingException;

public interface EmailService {
	
	void sendSimpleMessage(String to,String subject,String text) throws MessagingException;
	
	void sendWelcomeMessage(String to);

	void sendBookingRequestToLender(String email, String lenderName, String userName, String EquipmentName,
			LocalDateTime startDate, LocalDateTime endDate, String location);
	
	void sendBookingSatusNotificationToRenter(String email,String renterName,String status,String lenderName,String equipmentName,LocalDateTime startDate,LocalDateTime endDate);
}
