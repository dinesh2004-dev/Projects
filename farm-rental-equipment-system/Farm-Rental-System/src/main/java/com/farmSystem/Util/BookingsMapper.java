package com.farmSystem.Util;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.farmSystem.DTO.BookingsDTO;
import com.farmSystem.DTO.BookingsRequestDTO;
import com.farmSystem.entity.Bookings;
import com.farmSystem.entity.Equipment;
import com.farmSystem.entity.User;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {EquipmentMapper.class, UserMapper.class})
public interface BookingsMapper {

    @Mapping(source = "equipment", target = "equipmentDTO")
    @Mapping(source = "renter", target = "renterDTO")
    @Mapping(source = "lender", target = "lenderDTO")
    BookingsDTO bookingsEntityToBookingsDTO(Bookings bookings);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "equipment", source = "equipment")
    @Mapping(target = "renter", source = "renter")
    @Mapping(target = "lender", source = "lender")
    @Mapping(target = "start_date", source = "dto.startDate")
    @Mapping(target = "end_date", source = "dto.endDate")
    @Mapping(target = "bookingStatus", constant = "Pending")
    @Mapping(target = "paymentStatus", constant = "PENDING")
    @Mapping(target = "totalCost", expression = "java(equipment.getRentalRate())")
    Bookings toBooking(BookingsRequestDTO dto, Equipment equipment, User renter, User lender);
    
}
