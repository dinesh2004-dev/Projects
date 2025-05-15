package com.farmSystem.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.farmSystem.entity.Equipment;

import jakarta.persistence.LockModeType;

public interface EquipmentRepository extends JpaRepository<Equipment,Integer>,CustomEquipmentRepository{
	
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("SELECT e FROM Equipment e WHERE e.id = :id")
	Optional<Equipment> findByIdForUpdate(@Param("id") Integer id);
	
}
