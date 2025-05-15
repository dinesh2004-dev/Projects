package com.farmSystem.service;

import java.util.List;
import java.util.Optional;

import com.farmSystem.DTO.UserDTO;
import com.farmSystem.entity.User;
import com.farmSystem.exception.UserNotFoundException;

public interface UserService {
	int saveUser(UserDTO userDTO);
	
	Optional<User> findById(int id) throws UserNotFoundException;

	User findUser(String emailId, String password) throws UserNotFoundException;
	
	void deleteById(int id) throws UserNotFoundException;
	
	boolean existsById(int id);
	
	List<User> findAllUsers();

	boolean existsByEmailId(String email);
	
}
