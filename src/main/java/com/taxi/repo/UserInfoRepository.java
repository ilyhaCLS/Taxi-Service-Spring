package com.taxi.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.taxi.entity.UserInfo;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
	
	@Query("SELECT first FROM UserInfo WHERE user_id = :user_id")
	Optional<String> findFirstname(@Param("user_id") int user_id);

	
	@Query("SELECT totalSpent FROM UserInfo WHERE user_id = :user_id")
	Integer findTotalSpent(@Param("user_id") int user_id);
}