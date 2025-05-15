package com.farmSystem.service;

import java.util.List;

import com.farmSystem.DTO.EquipmentDTO;
import com.farmSystem.DTO.SearchEquipmentDTO;

public interface EquipmentService {
	
	int saveEquipment(EquipmentDTO equipmentDTO) throws Exception;
	
	List<EquipmentDTO> searchEquipment(SearchEquipmentDTO searchEquipmentDTO);
	
	List<EquipmentDTO> findAllEquipments();

}
