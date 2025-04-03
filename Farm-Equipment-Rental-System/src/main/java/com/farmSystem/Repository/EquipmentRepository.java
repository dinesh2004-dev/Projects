package com.farmSystem.Repository;

import java.util.List;

import com.farmSystem.dao.EquipmentDAO;
import com.farmSystem.entity.Equipment;

public interface EquipmentRepository {
	
	public List<EquipmentDAO> serchEquipment(String category,String location,Double minRate,Double maxRate,String sortField,String sortOrder,int pageNumber,int pageSize,
			double userLng,double userLat,Double radius);
	
	public Equipment findEquipment(int id);

}
