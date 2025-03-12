package com.farmSystem.dao;

import java.time.LocalDateTime;

import com.farmSystem.entity.Equipment;
import com.farmSystem.entity.User;
import com.farmSystem.enums.BookingStatus;
import com.farmSystem.enums.PaymentStatus;

public class BookingDAO {
	
	private Equipment equipment;

	
	private User renter;

	private LocalDateTime start_date;

	
	private LocalDateTime end_date;

	
	private BookingStatus bookingStatus;
	
	
	private PaymentStatus paymentStatus;
	
	
	private double totalCost;
	
	
	private LocalDateTime createdAt;


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
