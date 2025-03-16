package com.farmSystem.geolocation;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.farmSystem.Config.ConfigLoader;

public class ReverseGeocodingService {

	private static final String GOOGLE_API_KEY = ConfigLoader.getProperty("api.key");

	public static String getAddressFromCoordinates(double latitude, double longitude) {

		try {
			String requestUrl = "https://maps.googleapis.com/maps/api/geocode/json?latlng=" + latitude + "," + longitude
					+ "&key=" + GOOGLE_API_KEY;

			HttpClient httpClient = HttpClient.newHttpClient();

			HttpRequest request = HttpRequest.newBuilder().uri(URI.create(requestUrl)).GET().build();

			HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
			
			JSONObject jsonResponse = new JSONObject(response.body());
			
			JSONArray results = jsonResponse.getJSONArray("results");
			
			if(results.length() > 0) {
				
				return results.getJSONObject(0).getString("formatted_address");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Unknown Address";
	}
	
	

}
