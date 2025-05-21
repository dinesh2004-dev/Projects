package com.farmSystem.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farmSystem.DTO.Coordinates;
import com.farmSystem.Integration.GoogleMapsIntegration;

@Service
public class GoogleMapsUtil {
	@Autowired
	private GoogleMapsIntegration googleMapsService;
	
	public Coordinates getCoordinates(String address){
		
		 GoogleMapsIntegration.Coordinates coords = googleMapsService.getCoordinatesFromAddress(address);
		 
		 Coordinates coord = new Coordinates();
		 coord.setLatitude(coords.latitude);
		 coord.setLongitude(coords.longitude);
		return coord;
	}
	
}
