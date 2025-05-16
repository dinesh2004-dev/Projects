package com.farmSystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.farmSystem.DTO.UserDTO;
import com.farmSystem.Repository.AdminRepository;
import com.farmSystem.Util.UserMapper;
import com.farmSystem.entity.User;
import com.farmSystem.exception.UserNotFoundException;
import com.farmSystem.service.AdminService;

@Service
public class AdminSeviceImpl implements AdminService{
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private UserMapper userMapper;
	
	@Value("${user.not.found}")
	private String userNotFound;
	
	@Override
	public UserDTO findUserById(int id) throws UserNotFoundException {
		
		User user = adminRepository.findById(id).orElseThrow(() -> new UserNotFoundException(String.format(userNotFound,id)));
		
		return userMapper.UserEntityToUserDTO(user);
	}
	
	@Override
	public List<UserDTO> findAllUsers(){
		
		List<User> usersList = adminRepository.findAllUsers();
		
		return usersList.stream().map(user -> userMapper.UserEntityToUserDTO(user))
				.toList();
	}
	
	

}
