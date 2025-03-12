package com.farmSystem.Repository.Impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.farmSystem.Config.DataBaseConfig;
import com.farmSystem.Repository.AdminRepository;
import com.farmSystem.entity.User;



public class AdminRepositoryImpl implements AdminRepository{
	
	private SessionFactory sessionFactory = DataBaseConfig.getSessionFactory();
     
	public User findAdmin(int id) {
		
		try(Session session = sessionFactory.openSession()){
			return session.get(User.class,id);
		}
		
	}
	
	public void addAdmin(User admin) {
		
		try(Session session = sessionFactory.openSession()){
			
			Transaction transaction = session.beginTransaction();
			
			session.persist(admin);
			
			transaction.commit();
		}
	}
}
