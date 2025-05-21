package com.farmSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.farmSystem.exception.BookingNotFoundException;
import com.farmSystem.exception.DeliveryNotFoundException;
import com.farmSystem.service.DeliveryService;

@RequestMapping("api/delivery")
@RestController
public class DeliveryContoller {
	@Autowired
	private DeliveryService deliveryService;
	
	@PatchMapping("/{bookingId}/update-delivery")
	public ResponseEntity<String> updateDeliveryAddress(@RequestParam int bookingId,@RequestBody String address) throws BookingNotFoundException, DeliveryNotFoundException{
		
		String response = deliveryService.updateDelivery(bookingId, address);
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@PutMapping("/delivery/{deliveryId}/start")
	public ResponseEntity<String> startDelivery(@PathVariable int deliveryId) throws DeliveryNotFoundException{
		
		String response = deliveryService.startDelivery(deliveryId);
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}
