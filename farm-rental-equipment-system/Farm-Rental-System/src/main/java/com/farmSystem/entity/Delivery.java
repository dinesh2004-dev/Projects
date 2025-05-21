package com.farmSystem.entity;

import java.time.LocalDateTime;

import com.farmSystem.enums.DeliveryStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Delivery extends Base{
	

    @OneToOne
    @JoinColumn(name = "booking_id", nullable = false)
    private Bookings booking;

    private String deliveryAddress;
    private boolean deliveryRequired;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

//    private double deliveryCharge;
    
    private Double deliveryLatitude;
    private Double deliveryLongitude;

    private Double lastKnownLatitude;
    private Double lastKnownLongitude;
    private LocalDateTime lastLocationUpdated;
    
    @Column(insertable = false, updatable = false, 
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;
}
