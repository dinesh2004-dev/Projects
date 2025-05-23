package com.farmSystem.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.farmSystem.DTO.Coordinates;
import com.farmSystem.Repository.DeliveryRepository;
import com.farmSystem.entity.Delivery;
import com.farmSystem.enums.DeliveryStatus;
import com.farmSystem.exception.BookingNotFoundException;
import com.farmSystem.exception.DeliveryNotFoundException;
import com.farmSystem.service.DeliveryService;

@RequestMapping("api/delivery")
@RestController
public class DeliveryContoller {
	@Autowired
	private DeliveryService deliveryService;
	@Autowired
	private DeliveryRepository deliveryRepository;
	
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
	
	@PostMapping("/{deliveryId}/location")
	public ResponseEntity<String> updateDeliveryLocation( @PathVariable int deliveryId,
			@RequestBody Coordinates coords) throws DeliveryNotFoundException{
		
		Delivery delivery = deliveryRepository.findById(deliveryId)
		        .orElseThrow(() -> new DeliveryNotFoundException("Delivery not found"));
		if (delivery.getStatus() != DeliveryStatus.IN_TRANSIT) {
	        return ResponseEntity.badRequest().body("Delivery is not in progress.");
	    }
		
		delivery.setLastKnownLatitude(coords.getLatitude());
	    delivery.setLastKnownLongitude(coords.getLongitude());
	    delivery.setLastLocationUpdated(LocalDateTime.now());
	    

	    deliveryRepository.save(delivery);
	   

	    return ResponseEntity.ok("Location updated successfully.");
	}
	
	@GetMapping("/{deliveryId}/location")
	public ResponseEntity<Map<String, Double>> getDeliveryLocation(@PathVariable int deliveryId) throws DeliveryNotFoundException {
	    Delivery delivery = deliveryRepository.findById(deliveryId)
	        .orElseThrow(() -> new DeliveryNotFoundException("Delivery not found"));

	    Map<String, Double> location = new HashMap<>();
	    location.put("latitude", delivery.getLastKnownLatitude());
	    location.put("longitude", delivery.getLastKnownLongitude());

	    return ResponseEntity.ok(location);
	}
}
