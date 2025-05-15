package com.farmSystem.Util;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.farmSystem.DTO.UserDTO;
import com.farmSystem.entity.User;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
	
	User UserDTOToUser(UserDTO userDTO);
	
	UserDTO UserEntityToUserDTO(User user);

}
