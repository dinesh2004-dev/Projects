package com.farmSystem.Repository;

import com.farmSystem.entity.User;

public interface RenterRepository {
	
	User findRenter(int id);
	void addRenter(User user);

}
