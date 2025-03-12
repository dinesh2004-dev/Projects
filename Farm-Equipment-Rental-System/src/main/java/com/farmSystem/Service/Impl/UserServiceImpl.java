package com.farmSystem.Service.Impl;

import com.farmSystem.Repository.UserRepository;
import com.farmSystem.Repository.Impl.UserRepositoryImpl;
import com.farmSystem.Service.UserService;
import com.farmSystem.entity.User;

public class UserServiceImpl implements UserService{
	
	private UserRepository userRepository = new UserRepositoryImpl();
	
	@Override
	public User findUser(int id) {
		
		return userRepository.findUser(id);
	}
	
	@Override
	public boolean isEmailExist(String emailId) {
		
		return userRepository.isEmailExist(emailId);
	}
	
	@Override
	public User findUser(String emailId,String password) {
		
		return userRepository.findUser(emailId,password);
	}

}
