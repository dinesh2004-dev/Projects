package com.farmSystem.Service.Impl;

import java.util.Objects;

import com.farmSystem.Repository.BookingsRepository;
import com.farmSystem.Repository.EquipmentRepository;
import com.farmSystem.Repository.Impl.BookingsRepositoryImpl;
import com.farmSystem.Repository.Impl.EquipmentRepositoryImpl;
import com.farmSystem.Service.BookingsService;
import com.farmSystem.dao.BookingDAO;
import com.farmSystem.entity.Bookings;
import com.farmSystem.entity.Equipment;
import com.farmSystem.exceptions.EquipmentNotFound;
import com.farmSystem.util.MapperUtil;

public class BookingsServiceImpl implements BookingsService{
	
	private BookingsRepository bookingsRepository;
	
	private EquipmentRepository equipmentRepository;
	
	public BookingsServiceImpl() {
		
		this.bookingsRepository = new BookingsRepositoryImpl();
		
		this.equipmentRepository = new EquipmentRepositoryImpl();
		
	}
	
	public void bookEquipment(BookingDAO bookingsDAO) throws EquipmentNotFound{
		
		Equipment equipment = equipmentRepository.findEquipment(bookingsDAO.getEquipment().getId());
		
		if(Objects.isNull(equipment)) {
			throw new EquipmentNotFound("Equipment is not Found");
			
		}
		
		Bookings bookings = MapperUtil.convertBookingsDaoToBookingEntity(bookingsDAO);
		
		bookingsRepository.bookEquipment(bookings);
		
	}

}
