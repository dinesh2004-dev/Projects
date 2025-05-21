package com.farmSystem.DTO;

import java.time.LocalDateTime;

import com.farmSystem.entity.Bookings;
import com.farmSystem.enums.DeliveryStatus;

import lombok.Data;

@Data
public class DeliveryDTO {
	 	private int id;
	    private Bookings booking;

	    private String deliveryAddress;
	    private boolean deliveryRequired;

	    
	    private DeliveryStatus status;

	    private double deliveryCharge;
	    
	    private Double deliveryLatitude;
	    private Double deliveryLongitude;

	    private Double lastKnownLatitude;
	    private Double lastKnownLongitude;
	    private LocalDateTime lastLocationUpdated;

	    private LocalDateTime createdAt;
}
