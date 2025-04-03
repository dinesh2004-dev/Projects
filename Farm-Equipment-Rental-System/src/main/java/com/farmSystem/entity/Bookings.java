package com.farmSystem.entity;

import java.time.LocalDateTime;



import com.farmSystem.enums.BookingStatus;
import com.farmSystem.enums.PaymentStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "Bookings")
public class Bookings extends Base {

	@ManyToOne
	@JoinColumn(name = "equipment_id", referencedColumnName = "id", nullable = false)
	private Equipment equipment;

	@ManyToOne
	@JoinColumn(name = "renter_id", nullable = false)
	private User renter;
	
	
	@Column(nullable = false)
	private LocalDateTime start_date;
	
	
	@Column(nullable = false)
	private LocalDateTime end_date;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private BookingStatus bookingStatus;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private PaymentStatus paymentStatus;
	
	@Column(nullable = false)
	private double totalCost;
	
	 @Column(insertable = false, updatable = false, 
	            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime createdAt;
	 
	 public Bookings() {
		 
	 }
	 
	 public Bookings(Equipment equipment,User renter,LocalDateTime start_date,LocalDateTime end_date,
			 BookingStatus bookingStatus,PaymentStatus paymentStatus,double totalCost) {
		 
		 this.equipment = equipment;
		 
		 this.renter = renter;
		 
		 this.start_date = start_date;
		 
		 this.end_date = end_date;
		 
		 this.bookingStatus = BookingStatus.Pending;
		 
		 this.paymentStatus = PaymentStatus.PENDING;
		 
		 this.totalCost = totalCost;
		 
	 }

	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	public User getRenter() {
		return renter;
	}

	public void setRenter(User renter) {
		this.renter = renter;
	}

	public LocalDateTime getStart_date() {
		return start_date;
	}

	public void setStart_date(LocalDateTime start_date) {
		this.start_date = start_date;
	}

	public LocalDateTime getEnd_date() {
		return end_date;
	}

	public void setEnd_date(LocalDateTime end_date) {
		this.end_date = end_date;
	}

	public BookingStatus getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(BookingStatus bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	 

}
