package com.farmSystem.Service;

import com.farmSystem.dao.UserDAO;
import com.farmSystem.exceptions.RenterNotFound;

public interface RenterService {
	void addRenter(UserDAO userDAO) throws RenterNotFound;
}
