package com.farmSystem.Repository.Impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.farmSystem.Config.DataBaseConfig;
import com.farmSystem.Repository.RenterRepository;
import com.farmSystem.entity.User;

public class RenterRepositoryImpl implements RenterRepository{
	
	private static SessionFactory sessionFactory = DataBaseConfig.getSessionFactory();
	
	public User findRenter(int id) {
		
		try(Session session = sessionFactory.openSession()){
			
			return session.get(User.class,id);
		}
	}
		
	public void addRenter(User user) {
		
		try(Session session = sessionFactory.openSession()){
			
			Transaction transaction = session.beginTransaction();
			
			session.persist(user);
			
			transaction.commit();
		}
		
	}
		
	
	

}
