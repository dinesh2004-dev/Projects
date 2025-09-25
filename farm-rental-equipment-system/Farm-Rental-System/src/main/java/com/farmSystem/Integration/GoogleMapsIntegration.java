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
	            "https://us1.locationiq.com/v1/search?key=%s&q=%s&format=json",
                API_KEY,address.replace(" ", "+"));
		
		RestTemplate restTemplate = new RestTemplate();
		
		String response = restTemplate.getForObject(url,String.class);

        System.out.println("Google Maps API response: " + response);

//        JSONObject jsonObject = new JSONObject(response);



		
		JSONArray result = new JSONArray(response);

		
		if (result.length() > 0) {
            JSONObject location = result.getJSONObject(0);
            double lat = Double.parseDouble(location.getString("lat"));
            double lon = Double.parseDouble(location.getString("lon"));
            String displayName = location.getString("display_name");
            return new Coordinates(lat, lon, displayName);

        } else {
            throw new IllegalArgumentException("No results found for the given address: " + address);
        }
	}
	
	
}
