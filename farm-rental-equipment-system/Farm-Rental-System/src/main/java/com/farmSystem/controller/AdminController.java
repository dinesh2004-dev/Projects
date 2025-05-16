package com.farmSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.farmSystem.DTO.UserDTO;
import com.farmSystem.exception.UserNotFoundException;
import com.farmSystem.service.AdminService;

@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/admin")
@RestController
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@GetMapping
	public ResponseEntity<UserDTO> findUserById(@RequestParam int id) throws UserNotFoundException{
		
		UserDTO userDTO =adminService.findUserById(id);
		
		return ResponseEntity.status(HttpStatus.OK).body(userDTO);
	}
	
	@GetMapping("/findAllUsers")
	public ResponseEntity<List<UserDTO>> findAllUsers(){
		
		List<UserDTO> userDTOList =  adminService.findAllUsers();
		
		return ResponseEntity.status(HttpStatus.OK).body(userDTOList);
	}

}
