package model;

public class Station {

	public int stationID, totalBike, available;
	public String name, address;
	
	public void setStation(Station s) {
		this.stationID = s.stationID;
		this.totalBike = s.totalBike;
		this.available = s.available;
		this.name = s.name;
		this.address = s.address;
	}

	public Station(int stationID, String name, String position) {
		super();
		this.setName(name);
		this.setAddress(address);
	}
	
	

	public Station(int stationID, int totalBike, int available, String name, String address) {
	super();
	this.stationID = stationID;
	this.totalBike = totalBike;
	this.available = available;
	this.name = name;
	this.address = address;
}

public Station() {}

	



	public int getTotalBike() {
		return totalBike;
	}



	public void setTotalBike(int totalBike) {
		this.totalBike = totalBike;
	}



	public int getAvailable() {
		return available;
	}



	public void setAvailable(int available) {
		this.available = available;
	}



	public String getAddress() {
		return address;
	}



	public String getPosition() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	} 
	
	
	
}
