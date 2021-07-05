package com.taxi.entity;

public enum CarClass {
	ECONOM(4,18),
	ECONOMXL(6,24),
	COMFORT(4,33),
	VAN(8,30),
	BUSINESS(4,56),
	ELITE(3,100);
	
	private int numOfSeats;
	private int pricePerKm;
	
	CarClass(int numOfSeats,int pricePerKm){
		this.numOfSeats = numOfSeats;
		this.pricePerKm = pricePerKm;
	}
	
	public int getNumOfSeats() {
		return numOfSeats;
	}
	
	public int getPricePerKm() {
		return pricePerKm;
	}
}