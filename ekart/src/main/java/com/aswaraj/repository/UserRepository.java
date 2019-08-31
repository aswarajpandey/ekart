package com.aswaraj.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aswaraj.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	Optional<User> findByUserEmail(String userEmail);
	
	Optional<User> findByUserUsername(String userUsername);

}
