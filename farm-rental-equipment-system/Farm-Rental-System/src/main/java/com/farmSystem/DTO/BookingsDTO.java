package com.farmSystem.DTO;

import java.time.LocalDateTime;

import com.farmSystem.enums.BookingStatus;
import com.farmSystem.enums.PaymentStatus;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class BookingsDTO {
	
	
	private EquipmentDTO equipmentDTO;
	
	
	private UserDTO renterDTO;
	
	private UserDTO lenderDTO;
	
	
	private LocalDateTime start_date;
	
	
	private LocalDateTime end_date;
	
	
	private BookingStatus bookingStatus;
	
	
	private PaymentStatus paymentStatus;
	
	
	private double totalCost;
	
	 
	            
	private LocalDateTime createdAt;
	 
	 

	
	 

}
