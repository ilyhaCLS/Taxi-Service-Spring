package com.taxi.service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taxi.entity.Car;
import com.taxi.entity.Ride;
import com.taxi.exception.CarNotFoundException;
import com.taxi.repo.CarRepository;
import com.taxi.repo.RideRepository;

@Service
public class RideService {
	
	@Autowired
	RideRepository rideRepo;
	@Autowired
	CarRepository carRepo;
	
	
	@Transactional
	public Car addRide(Ride r, String carClass) throws CarNotFoundException {
		
		List<Car> cars = carRepo.findWhereCarClass(carClass).orElseThrow(CarNotFoundException::new);
		Car c = cars.get(cars.size() > 1 ? new SecureRandom().nextInt(cars.size()) : 0);
		
		r.setCar(c);
		r.setCreationTime(LocalDateTime.now());
		
		rideRepo.saveAndFlush(r);
		
		return c;
	}
	
	
	public int getNumOfRides() {
		return rideRepo.findNumOfRides();
	}
	
	public int getNumOfRides(int userId) {
		return rideRepo.findNumOfRides(userId);
	}
	
	public List<Ride> getRides(int userId, int page){
		return rideRepo.findAllByUserId(userId, PageRequest.of(page-1, 10, Sort.by("creationTime").descending()));
	}
	
	public List<Integer> getDayInfo(LocalDateTime timeFrom, LocalDateTime timeUntil){
	
		return rideRepo.infoForADay(timeFrom.toString(), timeUntil.toString());
	}

	public List<Ride> getSortedRides(String column, String order, int page) {
		
		return rideRepo.findAll(PageRequest.of(page-1, 10, Direction.fromString(order), column)).getContent();
	}

	public List<Ride> getRidesByPeriod(LocalDateTime from, LocalDateTime until, int page) {
		return rideRepo.findAllRidesByPeriod(from.toString(), until.toString(), (page * 10) - 10);
	}

	public int getNumOfRides(LocalDateTime from, LocalDateTime until) {
		return rideRepo.findNumOfRides(from.toString(), until.toString());
	}
}