package com.farmSystem.geolocation;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class GeolocationService {
	
	private static final String GOOGLE_API_KEY = "Replace with API Key";
	
	public static double[] getLocationFromIP(String ipAddress) {
		try {
			
			String url = "https://www.googleapis.com/geolocation/v1/geolocate?key=" + GOOGLE_API_KEY;
			
			String requestBody = "{\"considerIp\":true,\"ip\":\""+ipAddress+"\"}";
			
//			making API Call
			
			HttpClient httpClient = HttpClient.newHttpClient();
			
			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create(url))
					.header("Content-Type","application/json")
					.POST(HttpRequest.BodyPublishers.ofString(requestBody))
					.build();
//			send request and get response
			HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
			
//			parse JSON Response
			
			JsonObject jsonResponse = JsonParser.parseString(response.body()).getAsJsonObject();
			
			JsonObject location = jsonResponse.getAsJsonObject("location");
			
			double lat = location.get("lat").getAsDouble();
			
			double lng = location.get("lng").getAsDouble();
			
			
			return new double[]{lat, lng};
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

}
