package com.farmSystem.Repository.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.SelectionQuery;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;

import com.farmSystem.Config.DataBaseConfig;
import com.farmSystem.Repository.EquipmentRepository;
import com.farmSystem.dao.EquipmentDAO;
import com.farmSystem.entity.Equipment;
import com.farmSystem.entity.User;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class EquipmentRepositoryImpl implements EquipmentRepository {

	private SessionFactory sessionFactory = DataBaseConfig.getSessionFactory();

	@Override
	public List<EquipmentDAO> serchEquipment(String category, String location, Double minRate, Double maxRate,
			String sortField, String sortOrder, int pageNumber, int pageSize, double userLng, double userLat,
			Double radius) {

		try (Session session = sessionFactory.openSession()) {

			HibernateCriteriaBuilder hibernateCriteriaBuilder = session.getCriteriaBuilder();

			CriteriaQuery<Equipment> criteriaQuery = hibernateCriteriaBuilder.createQuery(Equipment.class);

			Root<Equipment> root = criteriaQuery.from(Equipment.class);
			
			Join<Equipment, User> ownerJoin = root.join("owner", JoinType.INNER);

			Predicate predicate = hibernateCriteriaBuilder.conjunction();
			

			if (category != null && !category.isEmpty()) {

				predicate = hibernateCriteriaBuilder.and(predicate,
						hibernateCriteriaBuilder.equal(root.get("category"), category));
			}
			if (location != null && !location.isEmpty()) {

				predicate = hibernateCriteriaBuilder.and(predicate,
						hibernateCriteriaBuilder.like(root.get("location"), "%" + location + "%"));
			}

			if (radius != null) {

				// Expression for distance calculation (in meters)
				Expression<Double> distanceExpr = hibernateCriteriaBuilder.function("ST_Distance_Sphere", Double.class,
						hibernateCriteriaBuilder.function("POINT", Object.class, ownerJoin.get("longitude"),
								ownerJoin.get("latitude")),
						hibernateCriteriaBuilder.function("POINT", Object.class,
								hibernateCriteriaBuilder.literal(userLng), hibernateCriteriaBuilder.literal(userLat)));

				predicate = hibernateCriteriaBuilder.and(predicate,
						hibernateCriteriaBuilder.lessThanOrEqualTo(distanceExpr, radius * 1000));

			}
			if (minRate != null) {

				predicate = hibernateCriteriaBuilder.and(predicate,
						hibernateCriteriaBuilder.greaterThanOrEqualTo(root.get("rentalRate"), minRate));
			}
			if (maxRate != null) {
				predicate = hibernateCriteriaBuilder.and(predicate,
						hibernateCriteriaBuilder.lessThanOrEqualTo(root.get("rentalRate"), maxRate));
			}

			criteriaQuery.select(root).where(predicate);

			// 🔹 Apply Sorting
			if (sortField != null && !sortField.isEmpty()) {
				if ("DESC".equalsIgnoreCase(sortOrder)) {
					criteriaQuery.orderBy(hibernateCriteriaBuilder.desc(root.get(sortField)));
				} else {
					criteriaQuery.orderBy(hibernateCriteriaBuilder.asc(root.get(sortField)));
				}
			}

			SelectionQuery<Equipment> selectionQuery = session.createSelectionQuery(criteriaQuery);

			selectionQuery.setFirstResult((pageNumber - 1) * pageSize); // Calculate starting index
			selectionQuery.setMaxResults(pageSize); // Set max records per page
			List<Equipment> result = selectionQuery.getResultList();

			return result.stream().map(EquipmentDAO::new).collect(Collectors.toList());

		}
	}

}
