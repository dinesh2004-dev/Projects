package com.farmSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.farmSystem.entity.Payments;

public interface PaymentsRepository extends JpaRepository<Payments,Integer>{

}
