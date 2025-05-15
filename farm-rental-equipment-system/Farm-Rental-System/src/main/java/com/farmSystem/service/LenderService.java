package com.farmSystem.service;

import java.util.List;

import com.farmSystem.DTO.BookingsDTO;
import com.farmSystem.enums.BookingStatus;
import com.farmSystem.exception.BookingNotFoundException;
import com.farmSystem.exception.EquipmentNotFoundException;

public interface LenderService {
	String updateBookingStatus(int id,BookingStatus status) throws BookingNotFoundException,EquipmentNotFoundException ;
	
	List<BookingsDTO> getAllRequestBookings();
}
