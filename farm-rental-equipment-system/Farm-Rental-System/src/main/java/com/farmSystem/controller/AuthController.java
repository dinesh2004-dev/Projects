package com.farmSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farmSystem.DTO.AuthRequest;
import com.farmSystem.Util.JwtUtil;

@RequestMapping("/authenticate")
@RestController
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping
	public ResponseEntity<String> generateJwtToken(@RequestBody AuthRequest authRequest) {
		
		try {
			
			authenticationManager.authenticate(new 
					UsernamePasswordAuthenticationToken(authRequest
							.getEmailId(),authRequest
							.getPassword()));
			
			String token = jwtUtil.generateToken(authRequest.getEmailId());
			
			return ResponseEntity.status(HttpStatus.OK).body(token);
		}
		catch(Exception e) {
			throw e;
		}
		
		
	}

}
