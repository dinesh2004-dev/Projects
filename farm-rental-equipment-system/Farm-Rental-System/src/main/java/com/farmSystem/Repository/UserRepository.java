package com.farmSystem.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.farmSystem.entity.User;

public interface UserRepository extends JpaRepository<User,Integer>{
	
	Optional<User> findByEmailId(String emailId);
	
	void deleteById(int id);

	@Query("SELECT o FROM User o WHERE o.emailId = :emailId AND o.password = :password")
	User findUser(String emailId, String password);
	
	boolean existsByEmailId(String emailId);


	boolean existsById(int id);
}
