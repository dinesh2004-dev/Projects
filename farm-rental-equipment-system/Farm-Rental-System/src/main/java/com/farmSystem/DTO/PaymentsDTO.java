package com.farmSystem.DTO;

import java.time.LocalDateTime;

import com.farmSystem.entity.Bookings;
import com.farmSystem.enums.PaymentStatus;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PaymentsDTO {
	
	private int id;
	private Bookings booking;
	private double amount;
	private String razorPayOrderId;
	private String razorPayPayMentId;
	private String razorPayRefundId;
	private PaymentStatus paymentStatus;
	private LocalDateTime createdAt;
	private boolean payoutStatus;
}
