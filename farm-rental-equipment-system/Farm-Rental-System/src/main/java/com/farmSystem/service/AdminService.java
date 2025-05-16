package com.farmSystem.service;

import java.util.List;

import com.farmSystem.DTO.UserDTO;
import com.farmSystem.exception.UserNotFoundException;

public interface AdminService {
	
	UserDTO findUserById(int id) throws UserNotFoundException;
	
	List<UserDTO> findAllUsers();
}
