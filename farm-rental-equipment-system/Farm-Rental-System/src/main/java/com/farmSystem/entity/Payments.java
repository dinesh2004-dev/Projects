package com.farmSystem.entity;

import java.time.LocalDateTime;

import com.farmSystem.enums.PaymentStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity(name = "payments")
public class Payments extends Base{
	
	@OneToOne
	@JoinColumn(name = "booking_id",nullable = false)
	private Bookings booking;
	
	@Column(nullable = false)
	private double amount;
	
	@Column(nullable = false)
	private String razorPayOrderId;
	
	@Column(nullable = false)
	private String razorPayPayMentId;
	
	private String razorPayRefundId;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private PaymentStatus paymentStatus;
	
	 @Column(insertable = false, updatable = false, 
	            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime createdAt;
	
	@Column(nullable = false)
	private boolean payoutStatus;
}
