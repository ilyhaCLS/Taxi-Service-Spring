package com.taxi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Car {
	@Id
	private int id;
	
	@Column(nullable=false, length = 32)
	private String licPlate;
	
	@Column(nullable=false, length = 32)
	private String carClass;
	
	@Column(nullable=false, length = 64)
	private String name;
	
	@Column(nullable=false, length = 32)
	private String status;
	
	public Car() {
		
	}
	
	public Car(String licPlate, String carClass, String name) {
		this.licPlate = licPlate;
		this.carClass = carClass;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLicPlate() {
		return licPlate;
	}
	public void setLicPlate(String licPlate) {
		this.licPlate = licPlate;
	}
	public String getCarClass() {
		return carClass;
	}
	public void setCarClass(String carClass) {
		this.carClass = carClass;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((carClass == null) ? 0 : carClass.hashCode());
		result = prime * result + id;
		result = prime * result + ((licPlate == null) ? 0 : licPlate.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Car))
			return false;
		
		Car other = (Car) obj;
		if(!licPlate.equals(other.getLicPlate())) {
			return false;
		}else if (!carClass.equals(other.getCarClass())){
			return false;
		}
		
		return true;
	}
	
	@Override
	public String toString() {
		return "Car [id=" + id + ", licPlate=" + licPlate + ", carClass=" + carClass + "]";
	}
}
