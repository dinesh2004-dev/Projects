package com.farmSystem.service;

import jakarta.mail.MessagingException;

public interface EmailSevice {
	
	void sendSimpleMessage(String to,String subject,String text) throws MessagingException ;
}
