package com.farmSystem.geolocation;

import jakarta.servlet.http.HttpServletRequest;

public class FindIP {
	public static String getClientIp(HttpServletRequest request) {
	    String ip = request.getHeader("X-Forwarded-For");
	    if (ip != null && !ip.isEmpty()) {
	        return ip.split(",")[0].trim();  // Extract first IP (real user IP)
	    }
	    return request.getRemoteAddr();  // Fallback if no proxy is used
	}

}
