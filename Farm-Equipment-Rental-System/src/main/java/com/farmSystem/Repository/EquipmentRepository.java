package com.farmSystem.Repository;

import java.util.List;

import com.farmSystem.dao.EquipmentDAO;

public interface EquipmentRepository {
	
	public List<EquipmentDAO> serchEquipment(String category,String location,Double minRate,Double maxRate,String sortField,String sortOrder,int pageNumber,int pageSize,
			double userLng,double userLat,Double radius);

}
