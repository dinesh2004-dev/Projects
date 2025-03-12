package com.farmSystem.Repository.Impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.farmSystem.Config.DataBaseConfig;
import com.farmSystem.Repository.UserRepository;
import com.farmSystem.entity.User;

public class UserRepositoryImpl implements UserRepository{
      
	private static SessionFactory sessionFactory = DataBaseConfig.getSessionFactory();
	
	@Override
	public User findUser(int id) {
		
		try(Session session = sessionFactory.openSession()){
			
			return session.get(User.class,id);
		}
	}
	
	@Override
	
	public User findUser(String emailId,String password) {
		
		String hql = "select u from User u where u.emailId = :emailId and u.password = :password";
		try(Session session = sessionFactory.openSession()){
			
			return session.createSelectionQuery(hql,User.class)
					.setParameter("emailId", emailId)
					.setParameter("password",password)
					.getSingleResultOrNull();
		}
	}
	@Override
	public boolean isEmailExist(String emailId) {
		
		String hql = "select count(u) from User u where u.emailId = :emailId";
		
		try(Session session = sessionFactory.openSession()){
			
			Long count = (Long)session.createSelectionQuery(hql,Long.class).setParameter("emailId",emailId).getSingleResult();
			
			return count > 0;
		}
	}
	
}
