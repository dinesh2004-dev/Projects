package com.farmSystem.service;

import java.util.List;

import com.farmSystem.DTO.BookingsDTO;
import com.farmSystem.DTO.BookingsRequestDTO;
import com.farmSystem.entity.Equipment;
import com.farmSystem.entity.User;
import com.farmSystem.exception.BookingNotFoundException;
import com.farmSystem.exception.EquipmentNotFoundException;
import com.farmSystem.exception.LenderNotFoundException;
import com.farmSystem.exception.RenterNotFoundException;
import com.farmSystem.exception.UserNotFoundException;
import com.razorpay.RazorpayException;

public interface BookingsService {
	
	String  book(BookingsRequestDTO bookingsDTO) throws UserNotFoundException,EquipmentNotFoundException,RenterNotFoundException,LenderNotFoundException;
	
	String getBookingStatus(int id) throws BookingNotFoundException;
	
	List<BookingsDTO> getAllBookings() throws RenterNotFoundException,BookingNotFoundException;
	
	public void confirmBookingAfterPayment(int bookingId,String razorpayOrderId,String razorpayPaymentId) throws BookingNotFoundException;
	
	String cancelBooking(int id)throws BookingNotFoundException,EquipmentNotFoundException,RazorpayException;
	
	boolean checkIfIncompleteBookingsExist(User renter, Equipment equipment); 
}
