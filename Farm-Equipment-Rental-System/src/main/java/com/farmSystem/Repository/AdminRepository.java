package com.farmSystem.Repository;

import com.farmSystem.entity.User;

public interface AdminRepository {
     
	User findAdmin(int id);
	
	void addAdmin(User user);
}
