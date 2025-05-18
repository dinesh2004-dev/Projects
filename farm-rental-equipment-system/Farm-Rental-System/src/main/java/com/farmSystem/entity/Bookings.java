package com.farmSystem.entity;

import java.time.LocalDateTime;

import com.farmSystem.enums.BookingStatus;
import com.farmSystem.enums.PaymentStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Bookings")
public class Bookings extends Base {
	
	@NonNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "equipment_id", referencedColumnName = "id", nullable = false)
	private Equipment equipment;
	
	@NonNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "renter_id", nullable = false)
	private User renter;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lender_id", nullable = false)
	private User lender;
	
	@NonNull
	@Column(nullable = false)
	private LocalDateTime start_date;
	
	@NonNull
	@Column(nullable = false)
	private LocalDateTime end_date;
	
	@NonNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private BookingStatus bookingStatus;
	
	@NonNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private PaymentStatus paymentStatus;
	
	
	@Column(nullable = false)
	private double totalCost;
	
	 @Column(insertable = false, updatable = false, 
	            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime createdAt;
	 
	 @OneToOne(mappedBy = "booking", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	 private Payments payment;
	 

	
	 

}
