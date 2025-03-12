package com.farmSystem.Service;

import com.farmSystem.dao.UserDAO;
import com.farmSystem.exceptions.AdminNotFound;

public interface AdminService {
	void addAdmin(UserDAO userDAO) throws AdminNotFound;
}
