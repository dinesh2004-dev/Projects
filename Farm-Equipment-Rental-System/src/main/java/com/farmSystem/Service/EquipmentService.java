package com.farmSystem.Service;

import java.util.List;

import com.farmSystem.entity.Equipment;

public interface EquipmentService {
	
	List<Equipment> searchEquipment(String category,String location,Double minRate,Double maxRate,String sortField,String sortOrder,int pageNumber,int pageSize,
			double userLng,double userLat,Double radius);

}
