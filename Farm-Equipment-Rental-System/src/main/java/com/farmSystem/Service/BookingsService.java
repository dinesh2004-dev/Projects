package com.farmSystem.Service;

import com.farmSystem.dao.BookingDAO;
import com.farmSystem.exceptions.EquipmentNotFound;

public interface BookingsService {
	
	void bookEquipment(BookingDAO bookingDAO) throws EquipmentNotFound;
}
