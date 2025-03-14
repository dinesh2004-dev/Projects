package com.farmSystem.Service.Impl;

import java.util.List;

import com.farmSystem.Repository.EquipmentRepository;
import com.farmSystem.Repository.Impl.EquipmentRepositoryImpl;
import com.farmSystem.Service.EquipmentService;
import com.farmSystem.entity.Equipment;

public class EquipmentServiceImpl implements EquipmentService {
	
	EquipmentRepository equipmentRepository = new EquipmentRepositoryImpl();
	
	@Override
	public List<Equipment> searchEquipment(String category,String location,Double minRate,Double maxRate,String sortField,String sortOrder,int pageNumber,int pageSize){
		
		return equipmentRepository.serchEquipment(category,location,minRate,maxRate,sortField,sortOrder,pageNumber,pageSize);
	}

}
