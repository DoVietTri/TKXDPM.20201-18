package model;

import java.sql.Date;
import java.sql.Time;

public class Rent {

	public int rentID, customerID, bikeID;
	public  Time timeStart, timeEnd;
	public int totalTimeRent;
	
	public Rent() {
		this.rentID = 0;
	}
	
	public void setRent(Rent r) {
		this.rentID = r.rentID;
		this.customerID = r.customerID;
		this.bikeID = r.bikeID;
		this.timeStart = r.timeStart;
		this.timeEnd = r.timeEnd;
		this.totalTimeRent = r.totalTimeRent;
	}
	
	
	public Rent(int rentID, int customerID, int bikeID, Time timeStart, Time timeEnd, int totalTimeRent) {
		super();
		this.rentID = rentID;
		this.customerID = customerID;
		this.bikeID = bikeID;
		this.timeStart = timeStart;
		this.timeEnd = timeEnd;
		this.totalTimeRent = totalTimeRent;
	}



	public int getRentID() {
		return rentID;
	}
	public void setRentID(int rentID) {
		this.rentID = rentID;
	}
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	public int getBikeID() {
		return bikeID;
	}
	public void setBikeID(int bikeID) {
		this.bikeID = bikeID;
	}
	public Time getTimeStart() {
		return timeStart;
	}
	public void setTimeStart(Time timeStart) {
		this.timeStart = timeStart;
	}
	public Time getTimeEnd() {
		return timeEnd;
	}
	public void setTimeEnd(Time timeEnd) {
		this.timeEnd = timeEnd;
	}
	public int getTotalTimeRent() {
		return totalTimeRent;
	}
	public void setTotalTimeRent(int totalTimeRent) {
		this.totalTimeRent = totalTimeRent;
	}
	
	
	
}
