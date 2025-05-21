package com.farmSystem.service.impl;

import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.farmSystem.Repository.BookingsRepository;
import com.farmSystem.Repository.DeliveryRepository;
import com.farmSystem.entity.Bookings;
import com.farmSystem.entity.Delivery;
import com.farmSystem.enums.DeliveryStatus;
import com.farmSystem.exception.BookingNotFoundException;
import com.farmSystem.exception.DeliveryNotFoundException;
import com.farmSystem.service.DeliveryService;

import jakarta.transaction.Transactional;

@Service
public class DeliveryServiceImpl implements DeliveryService{
	
	@Autowired
	private DeliveryRepository deliveryRepository;
	@Autowired
	private BookingsRepository bookingRepository;
	
	@Value("${Booking.not.found}")
	private String bookingNotFound;
	@Value("${Delivery.not.found}")
	private String deliverytNotFound;
	
	@Transactional
	@Override
	public String updateDelivery(int bookingId,String deliveryAddress) throws BookingNotFoundException, DeliveryNotFoundException {
		
		Bookings booking = bookingRepository.findById(bookingId)
											 .orElseThrow(() -> new BookingNotFoundException(String.format(bookingNotFound,bookingId)));
		Delivery deliveryEntity = booking.getDelivery();
		
		if(Objects.isNull(deliveryEntity)) {
			 throw new DeliveryNotFoundException(String.format("Delivery Not Found with Booking id : %s",bookingId));
		}
		
		deliveryEntity.setDeliveryAddress(deliveryAddress);
		deliveryRepository.save(deliveryEntity);
		
		return "Address Update Successfully";
	}
	
	@Transactional
	@Override
	public String startDelivery(int deliveryId) throws DeliveryNotFoundException {
		
		Delivery delivery = deliveryRepository.findById(deliveryId)
											  .orElseThrow(() -> new DeliveryNotFoundException(String.format(deliverytNotFound,deliveryId)));
		
		if (delivery.getStatus() != DeliveryStatus.SCHEDULED) {
	        return "Delivery must be in SCHEDULED state to start.";
	    }
		delivery.setStatus(DeliveryStatus.IN_TRANSIT);
		delivery.setLastLocationUpdated(LocalDateTime.now());
		
		deliveryRepository.save(delivery);
		
		return "Delivery started (IN_TRANSIT).";
	}

	
	

}
