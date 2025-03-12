package com.farmSystem.Config;

import org.hibernate.SessionFactory;

import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.farmSystem.entity.Equipment;
import com.farmSystem.entity.User;

public class DataBaseConfig {
	
	private static final SessionFactory SESSION_FACTORY = buildSessionFactory();
	
	public DataBaseConfig() {
		
	}
	
	public static SessionFactory buildSessionFactory() {
		
		Configuration configuration = new Configuration()
				.addAnnotatedClass(User.class)
				.addAnnotatedClass(Equipment.class);
				
		
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties())
				.build();
		
		return configuration.buildSessionFactory(serviceRegistry);
	}
	
	public static SessionFactory getSessionFactory() {
		
		return SESSION_FACTORY;
	}
}
