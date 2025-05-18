package com.farmSystem.service.impl;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.farmSystem.service.RefundService;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Service
public class RefundServiceImpl implements RefundService {
	
	@Value("${razorpay.api.key}")
	private String key;
	
	@Value("${razorpay.key.secret}")
	private  String secret;

	@Override
	public void instantRefund(String paymentId,int amount) throws RazorpayException {

		JSONObject refundRequest = new JSONObject();
		refundRequest.put("amount",amount);
		refundRequest.put("speed","optimum");
		
		RazorpayClient razorpayClient = new RazorpayClient(key,secret);
		
		razorpayClient.payments.refund(paymentId, refundRequest);              

	}
	
	@Override
	public void refund(String paymentId,int amount) throws RazorpayException {

		JSONObject refundRequest = new JSONObject();
		refundRequest.put("amount",amount);
		refundRequest.put("speed","normal");
		
		RazorpayClient razorpayClient = new RazorpayClient(key,secret);
		
		razorpayClient.payments.refund(paymentId, refundRequest);              

	}

}
