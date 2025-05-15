package com.farmSystem.service.impl;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

@Service
public class TokenBlacklistService {
	private final Set<String> blackList = ConcurrentHashMap.newKeySet();
	
	public void addToBlackList(String token) {
		
		blackList.add(token);
	}
	
	public boolean isBlackListed(String token) {
		
		
		
		return blackList.size()>=1 && blackList.contains(token);
	}
}
