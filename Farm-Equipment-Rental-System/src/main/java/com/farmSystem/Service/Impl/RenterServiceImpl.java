package com.farmSystem.Service.Impl;

import java.util.Objects;

import com.farmSystem.Repository.RenterRepository;
import com.farmSystem.Repository.Impl.RenterRepositoryImpl;
import com.farmSystem.Service.RenterService;
import com.farmSystem.dao.UserDAO;
import com.farmSystem.entity.User;
import com.farmSystem.exceptions.RenterNotFound;
import com.farmSystem.util.MapperUtil;

public class RenterServiceImpl implements RenterService{
	
	private RenterRepository renterRepository;
	
	public RenterServiceImpl() {
		
		renterRepository = new RenterRepositoryImpl();
	}
	
	public void addRenter(UserDAO userDAO) throws RenterNotFound{
		
		User existingUser = renterRepository.findRenter(userDAO.getId());
		
		if(Objects.nonNull(existingUser)) {
			
			throw new RenterNotFound("Renter with id:"+userDAO.getId()+"Already Exist");
		}
		
		User user = MapperUtil.convertUserDtoToUserEntity(userDAO);
		
		renterRepository.addRenter(user);
	}

}
