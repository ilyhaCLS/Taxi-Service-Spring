package com.taxi.entity.dto;

import java.time.LocalDateTime;

import com.taxi.entity.Car;
import com.taxi.entity.User;


public class RideDTO {
	private String posFrom;
	private String posTo;
	private int price;
	private LocalDateTime creationTime;
	private User user;
	private Car car;
	
	public String getPosFrom() {
		return posFrom;
	}


	public String getPosTo() {
		return posTo;
	}


	public int getPrice() {
		return price;
	}


	public LocalDateTime getCreationTime() {
		return creationTime;
	}


	public User getUser() {
		return user;
	}


	public Car getCar() {
		return car;
	}


	private RideDTO(String posFrom, String posTo, int price, LocalDateTime creationTime, User user,
			Car car) {
		this.posFrom = posFrom;
		this.posTo = posTo;
		this.price = price;
		this.creationTime = creationTime;
		this.user = user;
		this.car = car;
	}
	
	
	public static class RideDTOBuilder{
		private String posFrom;
		private String posTo;
		private int price;
		private LocalDateTime creationTime;
		private User user;
		private Car car;
		
		
		public RideDTOBuilder setPosFrom(String posFrom) {
			this.posFrom = posFrom;
			return this;
		}
		
		public RideDTOBuilder setPosTo(String posTo) {
			this.posTo = posTo;
			return this;
		}
		
		public RideDTOBuilder setPrice(int price) {
			this.price = price;
			return this;
		}
		
		public RideDTOBuilder setCreationTime(LocalDateTime creationTime) {
			this.creationTime = creationTime;
			return this;
		}
		
		public RideDTOBuilder setCar(Car car) {
			this.car = car;
			return this;
		}
		
		public RideDTOBuilder setUser(User user) {
			this.user = user;
			return this;
		}
		
		public RideDTO build() {
			return new RideDTO(posFrom, posTo, price, creationTime, user, car); 
		}
	}
}