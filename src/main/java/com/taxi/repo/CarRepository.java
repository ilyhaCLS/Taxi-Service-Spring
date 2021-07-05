package com.taxi.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.taxi.entity.Car;

public interface CarRepository extends JpaRepository<Car, Integer> {
	
	@Query("FROM Car WHERE car_class = :car_class")
	Optional<List<Car>> findWhereCarClass(@Param("car_class") String car_class);
}