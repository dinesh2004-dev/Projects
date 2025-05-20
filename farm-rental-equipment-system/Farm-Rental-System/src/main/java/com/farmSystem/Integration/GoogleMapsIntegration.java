package com.farmSystem.Integration;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GoogleMapsIntegration {
	
	@Value("${google.maps.key}")
	private String API_KEY;
	
	
	public static class Coordinates {
        public double latitude;
        public double longitude;
        public String formattedAddress;

        public Coordinates(double lat, double lng, String address) {
            this.latitude = lat;
            this.longitude = lng;
            this.formattedAddress = address;
        }
    }
	
	public Coordinates getCoordinatesFromAddress(String address) {
		String url = String.format(
	            "https://maps.googleapis.com/maps/api/geocode/json?address=%s&key=%s",
	            address.replace(" ", "+"), API_KEY);
		
		RestTemplate restTemplate = new RestTemplate();
		
		String response = restTemplate.getForObject(url,String.class);
		
		JSONObject jsonObject = new JSONObject(response);
		
		JSONArray result = jsonObject.getJSONArray("results");
		
		if(result.length() > 0) {
			
			JSONObject location = result.getJSONObject(0)
										.getJSONObject("geometry")
										.getJSONObject("location");
			
			double lat = location.getDouble("lat");
			
			double lng = location.getDouble("lng");
			
			String formattedAddress = result.getJSONObject(0).getString("formatted_address");
			
			return new Coordinates(lat,lng,formattedAddress);
		}
		else {
            throw new RuntimeException("Invalid address: Unable to geocode");
        }
	}
	
	
}
