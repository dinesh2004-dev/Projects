package com.farmSystem.Repository;

import com.farmSystem.entity.Equipment;
import com.farmSystem.entity.User;

public interface LenderRepository {
	User findLender(int id);

	void addLender(User user);
	
	void addEquipment(Equipment equipment);
}
