package com.farmSystem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.farmSystem.DTO.UserDTO;
import com.farmSystem.entity.User;
import com.farmSystem.exception.UserNotFoundException;
import com.farmSystem.service.UserService;
import com.farmSystem.service.impl.TokenBlacklistService;

import lombok.RequiredArgsConstructor;

@Validated
@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {
	
	private final UserService userService;
	
	private final TokenBlacklistService tokenBlacklistService;
	
//	@Value("${google.maps.key}")
//	private String mapKey;
	@PostMapping
	public ResponseEntity<Integer> saveUser(@RequestBody UserDTO userDTO){
		
		int userId = userService.saveUser(userDTO);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(userId);
	}
	
	@GetMapping
	public ResponseEntity<UserDTO> findById(@RequestParam int id) throws UserNotFoundException{
		
		UserDTO userDTO =userService.findById(id);
		
		return ResponseEntity.status(HttpStatus.OK).body(userDTO);
	}
	
	
	@GetMapping("/logout")
	public ResponseEntity<String> logout(@RequestHeader("Authorization") String authHeader){
		
		String token = authHeader.replace("Bearer ","");
		
		tokenBlacklistService.addToBlackList(token);
		
		return ResponseEntity.status(HttpStatus.OK).body("user Logged Out");
	}
	
	@DeleteMapping
	public ResponseEntity<Integer> deleteUser(@RequestParam int id) throws UserNotFoundException{
		
		if(!userService.existsById(id)) {
			
			throw new UserNotFoundException("user not found with id:"+id);
			
		}
		
		userService.deleteById(id);
		
		return ResponseEntity.status(HttpStatus.OK).body(id);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<UserDTO>> findAllUsers(){
		
		List<UserDTO> userList = userService.findAllUsers();
		
		return ResponseEntity.status(HttpStatus.OK).body(userList);
	}
}
