package com.farmSystem.geolocation;

import java.net.InetAddress;
import java.net.UnknownHostException;

import jakarta.servlet.http.HttpServletRequest;

public class FindIP {
    public static String getClientIp(HttpServletRequest request) {
    	String ip = request.getHeader("X-Forwarded-For");
        if (ip != null && !ip.isEmpty() && !"unknown".equalsIgnoreCase(ip)) {
            return ip.split(",")[0].trim();
        }

        ip = request.getRemoteAddr();

        // Convert IPv6 localhost (::1) to IPv4 (127.0.0.1)
        if ("0:0:0:0:0:0:0:1".equals(ip)) {
            try {
                ip = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                ip = "127.0.0.1"; // Fallback
            }
        }

        return ip;
    }
}
