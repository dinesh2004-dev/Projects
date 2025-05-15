package com.farmSystem.Util;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.farmSystem.DTO.EquipmentDTO;
import com.farmSystem.entity.Equipment;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = UserMapper.class)
public interface EquipmentMapper {

	@Mapping(target = "owner", ignore = true)
    EquipmentDTO equipmentEntityToEquipmentDTO(Equipment equipment);

    @Mapping(source = "owner", target = "owner")
    Equipment equipmentDTOToEquipmentEntity(EquipmentDTO equipmentDTO);
}
