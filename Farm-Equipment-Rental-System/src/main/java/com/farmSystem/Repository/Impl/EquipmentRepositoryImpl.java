package com.farmSystem.Repository.Impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;

import com.farmSystem.Config.DataBaseConfig;
import com.farmSystem.Repository.EquipmentRepository;
import com.farmSystem.entity.Equipment;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class EquipmentRepositoryImpl implements EquipmentRepository{
	
	private SessionFactory sessionFactory = DataBaseConfig.getSessionFactory();
	
	@Override
	public List<Equipment> serchEquipment(String category,String location,Double minRate,Double maxRate){
		
		try(Session session = sessionFactory.openSession()){
			
			HibernateCriteriaBuilder hibernateCriteriaBuilder = session.getCriteriaBuilder();
			
			CriteriaQuery<Equipment> criteriaQuery = hibernateCriteriaBuilder.createQuery(Equipment.class);
			
			Root<Equipment> root = criteriaQuery.from(Equipment.class);
			
			Predicate predicate = hibernateCriteriaBuilder.conjunction();
			
			if(category != null && !category.isEmpty()) {
				
				predicate = hibernateCriteriaBuilder.and(predicate,hibernateCriteriaBuilder.equal(root.get("category"), category));
			}
			if(location != null && !location.isEmpty()) {
				
				predicate = hibernateCriteriaBuilder.and(predicate,hibernateCriteriaBuilder.like(root.get("location"),"%"+location+"%"));
			}
			if(minRate != null) {
				
				predicate = hibernateCriteriaBuilder.and(predicate,hibernateCriteriaBuilder.greaterThanOrEqualTo(root.get("rentalRate"), minRate));
			}
			if(maxRate != null) {
				predicate = hibernateCriteriaBuilder.and(predicate,hibernateCriteriaBuilder.lessThanOrEqualTo(root.get("rentalRate"), maxRate));
			}
			
			criteriaQuery.select(root).where(predicate);
			
			return session.createSelectionQuery(criteriaQuery).getResultList();
		}
	}
	
	
}
