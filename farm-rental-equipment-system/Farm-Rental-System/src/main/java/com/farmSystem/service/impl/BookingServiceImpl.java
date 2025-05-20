package com.farmSystem.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import com.farmSystem.DTO.BookingsDTO;
import com.farmSystem.DTO.BookingsRequestDTO;
import com.farmSystem.Repository.BookingsRepository;
import com.farmSystem.Repository.EquipmentRepository;
import com.farmSystem.Repository.PaymentsRepository;
import com.farmSystem.Repository.UserRepository;
import com.farmSystem.Util.BookingsMapper;
import com.farmSystem.Util.PaymentMapper;
import com.farmSystem.Util.UsersUtil;
import com.farmSystem.entity.Bookings;
import com.farmSystem.entity.Equipment;
import com.farmSystem.entity.Payments;
import com.farmSystem.entity.User;
import com.farmSystem.enums.Availability;
import com.farmSystem.enums.BookingStatus;
import com.farmSystem.enums.PaymentStatus;
import com.farmSystem.enums.Role;
import com.farmSystem.exception.BookingNotFoundException;
import com.farmSystem.exception.EquipmentNotFoundException;
import com.farmSystem.exception.LenderNotFoundException;
import com.farmSystem.exception.RenterNotFoundException;
import com.farmSystem.exception.UserNotFoundException;
import com.farmSystem.service.BookingsService;
import com.farmSystem.service.EmailService;
import com.farmSystem.service.RefundService;
import com.razorpay.RazorpayException;

import jakarta.transaction.Transactional;

@Service
public class BookingServiceImpl implements BookingsService {
	
	@Autowired
	private BookingsRepository bookingsRepository;
	
	@Autowired
	private EquipmentRepository equipmentRepository;
	
	@Autowired
	private UsersUtil usersUtil;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PaymentsRepository paymentsRepository;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private RefundService refundService;
	
	@Autowired
	private PaymentMapper paymentMapper;
	
	
	@Autowired
	private BookingsMapper bookingsMapper;
	
	
	@Value("${equipment.not.found}")
	private String equipmentNotFound;
	
	@Value("${user.not.found}")
	private String userNotFound;
	
	@Value("${Booking.not.found}")
	private String NoBookingFound;
	
	@Value("${Lender.not.found}")
	private String lenderNotFound;
	
	@Transactional
	@Override
	public String book(BookingsRequestDTO bookingsRequestDTO) throws UserNotFoundException, EquipmentNotFoundException, RenterNotFoundException, LenderNotFoundException {

	    String emailId = usersUtil.getCurrentUserEmail();

	    User renter = userRepository.findByEmailId(emailId)
	            .orElseThrow(() -> new RenterNotFoundException("Renter Not Found with Email Id " + emailId));

	    if (renter.getRole() != Role.Renter) {
	        throw new AccessDeniedException("Only Renters Can Book!");
	    }

	    Equipment equipment = equipmentRepository.findByIdForUpdate(bookingsRequestDTO.getEquipmentId())
	            .orElseThrow(() -> new EquipmentNotFoundException(String.format(equipmentNotFound, bookingsRequestDTO.getEquipmentId())));

	    if (equipment.getOwner() == null) {
	        throw new LenderNotFoundException("Equipment has no owner assigned.");
	    }

	    if (equipment.getAvailability() == Availability.Unavailable) {
	        return "Equipment Is Not Available";
	    }

	    boolean overlapExists = bookingsRepository.existsOverlappingBookings(
	        equipment, bookingsRequestDTO.getStartDate(), bookingsRequestDTO.getEndDate()
	    );
	    if (overlapExists) {
	        return "Equipment already booked during selected dates.";
	    }
	    
	    

	    boolean alreadyBooked = checkIfIncompleteBookingsExist(renter,equipment);
	    if (alreadyBooked) {
	        return "Equipment with id :" + equipment.getId() + " Already Booked and is pending.";
	    }

	    User lender = userRepository.findById(equipment.getOwner().getId())
	            .orElseThrow(() -> new LenderNotFoundException(String.format(lenderNotFound, equipment.getOwner().getId())));

	    Bookings booking = new Bookings();
	    booking.setEquipment(equipment);
	    booking.setRenter(renter);
	    booking.setLender(lender);
	    booking.setStart_date(bookingsRequestDTO.getStartDate());
	    booking.setEnd_date(bookingsRequestDTO.getEndDate());
	    booking.setBookingStatus(BookingStatus.Pending);
	    booking.setPaymentStatus(PaymentStatus.PENDING);
	    booking.setTotalCost(equipment.getRentalRate());

	    equipment.setAvailability(Availability.Booked);
	    equipmentRepository.save(equipment);
	    bookingsRepository.save(booking);

	    // Async Email
	    emailService.sendBookingRequestToLender(
	        lender.getEmailId(),
	        lender.getFullName(),
	        renter.getFullName(),
	        equipment.getName(),
	        booking.getStart_date(),
	        booking.getEnd_date(),
	        renter.getAddress()
	    );

	    return String.format("Request Sent To Lender with id : %s", lender.getId());
	}
	
	
	@Override
    public boolean checkIfIncompleteBookingsExist(User renter, Equipment equipment) {
        List<BookingStatus> statuses = List.of(
            BookingStatus.Pending,
            BookingStatus.Approved
            
        );

        return bookingsRepository.existsByRenterAndEquipmentWithStatusIn(renter, equipment, statuses);
    }

	@Override
	public String getBookingStatus(int id) throws BookingNotFoundException{
		
		 
		String emailId = usersUtil.getCurrentUserEmail();
		
		Bookings booking = bookingsRepository.findById(id)
											 .orElseThrow(() -> new BookingNotFoundException(String.format(NoBookingFound,id)));
		
		if (!booking.getRenter().getEmailId().equals(emailId)) {
	        return "Not authorized to view booking status";
	    }
		
		return booking.getBookingStatus().name();
											  
	}
	
	@Override
	public List<BookingsDTO> getAllBookings() throws RenterNotFoundException, BookingNotFoundException{
		
		String renterEmailId = usersUtil.getCurrentUserEmail();

	    User renter = userRepository.findByEmailId(renterEmailId)
	    							.orElseThrow(() -> new RenterNotFoundException("Renter Not Found with Email Id"+renterEmailId));
	    
	    List<Bookings> bookingsList = bookingsRepository.findAllByRenter(renter);
	    
	    if(bookingsList.isEmpty()) {
	    	
	    	throw new BookingNotFoundException("there are no bookings for renter with id : "+renter.getId());
	    }
	    
	    return bookingsList.stream().map(bookings -> bookingsMapper.bookingsEntityToBookingsDTO(bookings)).collect(Collectors.toList());
	}
	
	@Override
	@Transactional
	public void confirmBookingAfterPayment(int bookingId,String razorpayOrderId,String razorpayPaymentId) throws BookingNotFoundException {
		
		Bookings booking = bookingsRepository.findById(bookingId)
	        .orElseThrow(() -> new BookingNotFoundException("Booking not found"));

	    if (booking.getBookingStatus() != BookingStatus.Approved) {
	        throw new IllegalStateException("Booking must be approved before confirming payment");
	    }

	    booking.setPaymentStatus(PaymentStatus.PAID);
	    booking.setBookingStatus(BookingStatus.Completed);
	    bookingsRepository.save(booking);
	    
	    Payments payment = paymentMapper.toPayments(booking,razorpayOrderId,razorpayPaymentId);
	    
	    paymentsRepository.save(payment);
	    
	    
	}
	
	@Override
	@Transactional
	public String cancelBooking(int id) throws BookingNotFoundException, EquipmentNotFoundException, RazorpayException {
		
		
		
		String emailId = usersUtil.getCurrentUserEmail();
		
		Bookings booking = bookingsRepository.findById(id).orElseThrow(() -> new BookingNotFoundException("Booking not found"));
		
		if(!booking.getRenter().getEmailId().equals(emailId)) {
			
			throw new AccessDeniedException("You are not authorized to delete this booking");
		}
		
		Equipment equipment = booking.getEquipment();
		
		if(Objects.isNull(equipment)) {
			
			throw new EquipmentNotFoundException("There is No Equipment Booked With Id :"+id);
		}
		
		equipment.setAvailability(Availability.Available);
		
		equipmentRepository.save(equipment);
		
		Payments payment = booking.getPayment();
		String paymentId = payment.getRazorPayPayMentId();
		int amount = (int)payment.getAmount() * 100;
		
		String refundId = refundService.refund(paymentId, amount);
		payment.setRazorPayRefundId(refundId);
		paymentsRepository.save(payment);
		
		booking.setBookingStatus(BookingStatus.Cancelled);
		booking.setPaymentStatus(PaymentStatus.REFUNDED);
		
		bookingsRepository.save(booking);
		
		
		return "Booking is deleted successfully and refund is initiated";
	}


}
