package com.apilike.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apilike.entity.user.User;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	// Optional<User> findAllById(Integer id); 
	
	@Transactional(readOnly = true)
	User findByEmail(String email);
	
}
