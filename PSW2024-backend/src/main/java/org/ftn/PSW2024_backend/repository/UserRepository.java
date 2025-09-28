package org.ftn.PSW2024_backend.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.ftn.PSW2024_backend.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	List <User> findAll();
	User findByEmail(String email);
	User findByUsername(String username);
	
	@Query("SELECT u FROM User u WHERE TYPE(u) = Tourist")
	List<User> findAllTourists();
	
	@Query("SELECT u FROM User u WHERE TYPE(u) = Guide")
	List<User> findAllGuides();
}