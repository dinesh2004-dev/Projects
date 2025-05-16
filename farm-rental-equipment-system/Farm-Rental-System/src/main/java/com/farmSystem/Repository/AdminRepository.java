package com.farmSystem.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.farmSystem.entity.User;

public interface AdminRepository extends JpaRepository<User,Integer>{
	
	@Query("SELECT u FROM User u WHERE u.role != Admin")
	List<User> findAllUsers();
}
