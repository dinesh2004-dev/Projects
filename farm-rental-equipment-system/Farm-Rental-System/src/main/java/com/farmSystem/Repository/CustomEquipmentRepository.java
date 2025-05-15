package com.farmSystem.Repository;

import java.util.List;

import com.farmSystem.DTO.EquipmentDTO;
import com.farmSystem.DTO.SearchEquipmentDTO;

public interface CustomEquipmentRepository {
	
	List<EquipmentDTO> serchEquipment(SearchEquipmentDTO searchEquipmentDTO);
}
