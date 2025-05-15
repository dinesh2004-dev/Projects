package com.farmSystem.DTO;
import java.time.LocalDateTime;

import com.farmSystem.enums.Role;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class UserDTO{
	
	 private Long id;
	
	private String fullName;
	
	@Size(min = 8)
	private String password;
	
	
	private String emailId;
	
	
	private String phoneNumber;
	
	
	private double latitude;
	
	private double longitude;
	
	
	private Role role;
	
	private String address;
	 
	private LocalDateTime createdAt;

	
	

}
