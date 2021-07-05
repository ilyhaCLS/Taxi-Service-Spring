package com.taxi.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.taxi.entity.dto.RideDTO;

@Entity
public class Ride {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(nullable=false, length = 64)
	private String posFrom;
	
	@Column(nullable=false, length = 64)
	private String posTo;
	
	@Column(nullable=false)
	private int price;
	
	@Column(nullable=false)
	private LocalDateTime creationTime;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;
	
	@OneToOne
	@JoinColumn(name="car_id")
	private Car car;
	
	public Ride() {
		
	}
	
	public Ride(RideDTO r) {
		this.posFrom = r.getPosFrom();
		this.posTo = r.getPosTo();
		this.price = r.getPrice();
		this.creationTime = r.getCreationTime();
		this.user = r.getUser();
		this.car = r.getCar();
	}
	
	private Ride(int id, String posFrom, String posTo, int price, LocalDateTime creationTime, User user,
			Car car) {
		super();
		this.id = id;
		this.posFrom = posFrom;
		this.posTo = posTo;
		this.price = price;
		this.creationTime = creationTime;
		this.user = user;
		this.car = car;
	}

	
	public int getId() {
		return id;
	}
	
	public String getPosFrom() {
		return posFrom;
	}
	
	public String getPosTo() {
		return posTo;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public LocalDateTime getCreationTime() {
		return creationTime;
	}
	
	public void setCreationTime(LocalDateTime creationTime) {
		this.creationTime = creationTime;
	}
	
	public Car getCar() {
		return car;
	}
	
	public void setCar(Car car) {
		this.car = car;
	}
	
	public User getUser() {
		return user;
	}
	
	public static class RideBuilder{
		private int id;
		private String posFrom;
		private String posTo;
		private int price;
		private LocalDateTime creationTime;
		private User user;
		private Car car;
		
		public RideBuilder setId(int id) {
			this.id = id;
			return this;
		}
		
		public RideBuilder setPosFrom(String posFrom) {
			this.posFrom = posFrom;
			return this;
		}
		
		public RideBuilder setPosTo(String posTo) {
			this.posTo = posTo;
			return this;
		}
		
		public RideBuilder setPrice(int price) {
			this.price = price;
			return this;
		}
		
		public RideBuilder setCreationTime(LocalDateTime creationTime) {
			this.creationTime = creationTime;
			return this;
		}
		
		public RideBuilder setCar(Car car) {
			this.car = car;
			return this;
		}
		
		public RideBuilder setUser(User user) {
			this.user = user;
			return this;
		}
		
		public Ride build() {
			return new Ride(id, posFrom, posTo, price, creationTime, user, car); 
		}
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + car.getId();
		result = prime * result + ((creationTime == null) ? 0 : creationTime.hashCode());
		result = prime * result + ((posFrom == null) ? 0 : posFrom.hashCode());
		result = prime * result + id;
		result = prime * result + price;
		result = prime * result + user.getId();
		result = prime * result + ((posTo == null) ? 0 : posTo.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Car))
			return false;
		
		Ride other = (Ride) obj;
		if(!posFrom.equals(other.getPosFrom())) {
			return false;
		}else if (!posTo.equals(other.getPosTo())){
			return false;
		}else if (price != other.getPrice()) {
			return false;
		}else if (!creationTime.equals(other.getCreationTime())) {
			return false;
		}else if (car.getId() != other.getCar().getId()) {
			return false;
		}else if (user.getId() != other.getCar().getId()) {
			return false;
		}
		
		return true;
	}
	
	@Override
	public String toString() {
		return "Order [id=" + id + ", posFrom=" + posFrom + ", posFrom=" + posFrom + ", price=" + price + ", creationTime="
				+ creationTime + ", carId=" + car.getId() + ", userId=" + user.getId() + "]";
	}
}