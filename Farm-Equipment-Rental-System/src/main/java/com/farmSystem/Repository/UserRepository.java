package com.farmSystem.Repository;

import com.farmSystem.entity.User;

public interface UserRepository {
	
	User findUser(int id);
	
	User findUser(String emailId,String password);
	
	boolean isEmailExist(String emailId);
	

}
