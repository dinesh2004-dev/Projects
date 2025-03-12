package com.farmSystem.Service.Impl;

import java.util.Objects;

import com.farmSystem.Repository.AdminRepository;
import com.farmSystem.Repository.Impl.AdminRepositoryImpl;
import com.farmSystem.Service.AdminService;
import com.farmSystem.dao.UserDAO;
import com.farmSystem.entity.User;
import com.farmSystem.exceptions.AdminNotFound;
import com.farmSystem.util.MapperUtil;

public class AdminServiceImpl implements AdminService  {
	
	private AdminRepository adminRepository; 
	
	
	
	public AdminServiceImpl() {
		
		adminRepository = new AdminRepositoryImpl();
	}
	
	public void addAdmin(UserDAO userDAO) throws AdminNotFound {
		
		User existingAdmin = adminRepository.findAdmin(userDAO.getId());
		
		if(Objects.nonNull(existingAdmin)) {
			
			throw new AdminNotFound("Admin With id :"+userDAO.getId()+" is Already Exist");
		}
		
		User admin = MapperUtil.convertUserDtoToUserEntity(userDAO);
		
		adminRepository.addAdmin(admin);  
		
	}

}
