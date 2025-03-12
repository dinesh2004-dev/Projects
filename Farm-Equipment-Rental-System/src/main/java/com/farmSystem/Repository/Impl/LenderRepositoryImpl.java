package com.farmSystem.Repository.Impl;

import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.farmSystem.Config.DataBaseConfig;
import com.farmSystem.Repository.LenderRepository;
import com.farmSystem.entity.Equipment;
import com.farmSystem.entity.User;

public class LenderRepositoryImpl implements LenderRepository{
	
	private static SessionFactory sessionFactory = DataBaseConfig.getSessionFactory();
	
	@Override
	public User findLender(int id) {
		
		try(Session session = sessionFactory.openSession()){
			
			return session.get(User.class,id);
		}
	}
	
	@Override
	public void addLender(User user) {
		
		try(Session session = sessionFactory.openSession()){
			
			Transaction transaction = session.beginTransaction();
			
			session.persist(user);
			
			transaction.commit();
		}
		
	}
	
	@Override
	public void addEquipment(Equipment equipment) {
		
		try(Session session = sessionFactory.openSession()){
			
			Transaction transaction = session.beginTransaction();
			
			session.persist(equipment);
			
			transaction.commit();
		}
	}
		
	
	

}
