package com.farmSystem.Repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.farmSystem.entity.Bookings;
import com.farmSystem.entity.Equipment;
import com.farmSystem.entity.User;
import com.farmSystem.enums.BookingStatus;

public interface BookingsRepository extends JpaRepository<Bookings,Integer>{
	
	@Query("SELECT e from Bookings e where lender = :lender")
	List<Bookings> findAllByLender(User lender);
	
	@Query("SELECT e from Bookings e where renter = :renter")
	List<Bookings> findAllByRenter(User renter);
	
	@Query("SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END " + 
			"FROM Bookings b " + 
			"WHERE b.renter = :renter " + 
			"AND b.equipment = :equipment " + 
			"AND b.bookingStatus in :statuses ")
	boolean existsByRenterAndEquipmentWithStatusIn(@Param("renter") User renter,
												   @Param("equipment") Equipment equipment,
												   @Param("statuses") List<BookingStatus> statuses);
	
	boolean existsByRenterAndEquipment(User renter,Equipment equipment);
	
	@Query("SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END " +
		       "FROM Bookings b " +
		       "WHERE b.equipment = :equipment " +
		       "AND b.bookingStatus = 'Pending' " + // optional: limit to active bookings
		       "AND b.start_date <= :endDate " +
		       "AND b.end_date >= :startDate")
		boolean existsOverlappingBookings(@Param("equipment") Equipment equipment,
		                                  @Param("startDate") LocalDateTime startDate,
		                                  @Param("endDate") LocalDateTime endDate);

}
