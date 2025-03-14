package com.farmSystem.util;

import com.farmSystem.dao.EquipmentDAO;
import com.farmSystem.dao.UserDAO;
import com.farmSystem.entity.Equipment;
import com.farmSystem.entity.User;

public class MapperUtil {
	
	public static User convertUserDtoToUserEntity(UserDAO userDAO) {
		
		User user = new User();
		
		user.setRole(userDAO.getRole());
		user.setPhoneNumber(userDAO.getPhoneNumber());
		user.setPassword(userDAO.getPassword());
		user.setFullName(userDAO.getFullName());
		user.setEmailId(userDAO.getEmailId());
		user.setAddress(userDAO.getAddress());
		user.setLatitude(userDAO.getLattitude());
		user.setLongitude(userDAO.getLongitute());
		return user;
		
	}
	
	public static Equipment convertEquipmentDAOToEquipmentEntity(EquipmentDAO equipmentDAO) {
		
		Equipment equipment = new Equipment();
		
		equipment.setAvailability(equipmentDAO.getAvailability());
		
		equipment.setCategory(equipmentDAO.getCategory());
		
		equipment.setCondition(equipmentDAO.getCondition());
		
		equipment.setDescription(equipmentDAO.getDescription());
		
		equipment.setLocation(equipmentDAO.getLocation());
		
		equipment.setName(equipmentDAO.getName());
		
		equipment.setRentalRate(equipmentDAO.getRentalRate());
		
		return equipment;
	}

}
