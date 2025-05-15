package com.farmSystem.service;

import com.farmSystem.exception.BookingNotFoundException;
import com.farmSystem.exception.RenterNotFoundException;

public interface PaymentService {
	String initiatePayment(int bookingId) throws BookingNotFoundException, RenterNotFoundException ;
}
