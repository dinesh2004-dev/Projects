package com.farmSystem.Service;

import com.farmSystem.dao.EquipmentDAO;
import com.farmSystem.dao.UserDAO;
import com.farmSystem.entity.User;
import com.farmSystem.exceptions.LenderNotFound;
import com.farmSystem.exceptions.UserNotLender;

public interface LenderService {
	
	void addLender(UserDAO userDAO) throws LenderNotFound;
	
	void addEquipment(int id,EquipmentDAO EquipmentDAO) throws LenderNotFound,UserNotLender; 
}
