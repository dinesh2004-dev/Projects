package com.farmSystem.service;

import com.razorpay.RazorpayException;

public interface RefundService {
	
	void instantRefund(String paymentId,int amount) throws RazorpayException;
	
	public void refund(String paymentId,int amount) throws RazorpayException;

}
