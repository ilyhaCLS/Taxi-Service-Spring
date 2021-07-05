package com.taxi.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.taxi.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	@Query("FROM User WHERE username = :username")
	Optional<User> findByUsername(@Param("username")String username);
}