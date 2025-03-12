package com.farmSystem.Service.Impl;

import java.util.Objects;

import com.farmSystem.Repository.LenderRepository;
import com.farmSystem.Repository.Impl.LenderRepositoryImpl;
import com.farmSystem.Service.LenderService;
import com.farmSystem.dao.EquipmentDAO;
import com.farmSystem.dao.UserDAO;
import com.farmSystem.entity.Equipment;
import com.farmSystem.entity.User;
import com.farmSystem.exceptions.LenderNotFound;
import com.farmSystem.exceptions.UserNotLender;
import com.farmSystem.util.MapperUtil;

public class LenderServiceImpl implements LenderService {

	private LenderRepository lenderRepository;

	public LenderServiceImpl() {

		lenderRepository = new LenderRepositoryImpl();
	}

	@Override
	public void addLender(UserDAO userDAO) throws LenderNotFound {

		User existingUser = lenderRepository.findLender(userDAO.getId());

		if (Objects.nonNull(existingUser)) {

			throw new LenderNotFound("Lender with id:" + userDAO.getId() + "Already Exist");
		}

		User user = MapperUtil.convertUserDtoToUserEntity(userDAO);

		lenderRepository.addLender(user);
	}

	public void addEquipment(int id, EquipmentDAO equipmentDAO) throws LenderNotFound,UserNotLender{

		User existingUser = lenderRepository.findLender(id);

		if (Objects.isNull(existingUser)) {

			throw new LenderNotFound("User with id:" + id+ "Not Exist");
		}
		
		String userRole =existingUser.getRole().toString();
		
		if(!userRole.equals("Lender")) {
			
			throw new UserNotLender("User with id:" + id+" not Lender");
		}
		
		Equipment equipment = MapperUtil.convertEquipmentDAOToEquipmentEntity(equipmentDAO);
		
		equipment.setOwner(existingUser);
		
		lenderRepository.addEquipment(equipment);

	}

}
