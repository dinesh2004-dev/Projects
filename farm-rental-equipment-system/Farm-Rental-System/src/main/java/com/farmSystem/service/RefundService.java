package com.farmSystem.service;

import com.razorpay.RazorpayException;

public interface RefundService {
	
	void instantRefund(String paymentId,int amount) throws RazorpayException;
	
	public String refund(String paymentId,int amount) throws RazorpayException;

}
