package com.farmSystem.Repository.Impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.farmSystem.Config.DataBaseConfig;
import com.farmSystem.Repository.BookingsRepository;
import com.farmSystem.entity.Bookings;



public class BookingsRepositoryImpl implements BookingsRepository{
	
	SessionFactory sessionFactory = DataBaseConfig.getSessionFactory();
	
	public void bookEquipment(Bookings bookings){
		
		try(Session session = sessionFactory.openSession()){
			
			Transaction transaction = session.beginTransaction();
			
			session.persist(bookings);
			
			transaction.commit();
			
		}
	}

}
