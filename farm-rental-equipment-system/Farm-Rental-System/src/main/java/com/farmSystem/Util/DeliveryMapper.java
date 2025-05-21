package com.farmSystem.Util;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.farmSystem.DTO.BookingsRequestDTO;
import com.farmSystem.DTO.Coordinates;
import com.farmSystem.entity.Bookings;
import com.farmSystem.entity.Delivery;
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DeliveryMapper {
	
	    @Mapping(target = "id", ignore = true)
	    @Mapping(target = "booking", source = "booking")
	    @Mapping(target = "deliveryAddress", source = "dto.deliveryAddress")
	    @Mapping(target = "deliveryRequired", source = "dto.deliveryRequired")
	    @Mapping(target = "deliveryLatitude", source = "coords.latitude")
	    @Mapping(target = "deliveryLongitude", source = "coords.longitude")
	    @Mapping(target = "status", constant = "PENDING")
	    Delivery toDelivery(BookingsRequestDTO dto, Coordinates coords, Bookings booking);

}
