package com.taxi.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.taxi.entity.Ride;

public interface RideRepository extends JpaRepository<Ride, Integer> {

	@Query("SELECT COUNT(id) from Ride")
	public int findNumOfRides();
	
	@Query("SELECT COUNT(id) from Ride WHERE user_id = :user_id")
	public int findNumOfRides(int user_id);
	
	@Query("SELECT COUNT(id) from Ride WHERE creation_time >= :timeFrom AND creation_time <= :timeUntil")
	public int findNumOfRides(String timeFrom, String timeUntil);
	
	public List<Ride> findAllByUserId(int userId, Pageable p);
	
	
	@Query(value = "SELECT * FROM ride r JOIN car c ON r.car_id = c.id ORDER BY ?1 ?2 LIMIT ?3 , 10", nativeQuery = true)
	public List<Ride> findAllList(String column, String order, int page);
	
	public Page<Ride> findAll(Pageable p);
	
	
	@Query("SELECT price FROM Ride WHERE creation_time >= :timeFrom AND creation_time <= :timeUntil")
	public List<Integer> infoForADay(String timeFrom, String timeUntil);

	@Query(value = "SELECT * FROM ride r JOIN car c ON r.car_id = c.id WHERE creation_time >= :timeFrom "
			+ "AND creation_time <= :timeUntil ORDER BY creation_time DESC LIMIT :page , 10", nativeQuery = true)
	public List<Ride> findAllRidesByPeriod(String timeFrom, String timeUntil, int page);
}