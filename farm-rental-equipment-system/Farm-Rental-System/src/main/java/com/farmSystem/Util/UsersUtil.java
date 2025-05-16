package com.farmSystem.Util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UsersUtil {
	public String getCurrentUserEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
	
	
	
	
}
