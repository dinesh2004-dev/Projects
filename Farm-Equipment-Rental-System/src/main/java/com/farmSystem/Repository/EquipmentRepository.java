package com.farmSystem.Repository;

import java.util.List;

import com.farmSystem.entity.Equipment;

public interface EquipmentRepository {
	
	List<Equipment> serchEquipment(String category,String location,Double minRate,Double maxRate);

}
