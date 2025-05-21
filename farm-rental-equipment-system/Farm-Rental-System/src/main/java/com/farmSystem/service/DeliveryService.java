package com.farmSystem.service;

import com.farmSystem.exception.BookingNotFoundException;
import com.farmSystem.exception.DeliveryNotFoundException;

public interface DeliveryService {
	
	String updateDelivery(int bookingId,String delivery) throws BookingNotFoundException,DeliveryNotFoundException;
	
	String startDelivery(int deliveryId)throws DeliveryNotFoundException;
	
}
