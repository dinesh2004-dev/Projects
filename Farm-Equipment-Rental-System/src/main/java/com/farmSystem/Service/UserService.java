package com.farmSystem.Service;

import com.farmSystem.entity.User;

public interface UserService {
	
	User findUser(int id);
	
	User findUser(String emailId,String password);
	
	boolean isEmailExist(String emailId);

}
