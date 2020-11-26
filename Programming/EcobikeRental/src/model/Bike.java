package model;

public class Bike {
	public int id, code, battery, stationID;
	public double price;
	public String name, status, type, description;

	public Bike(int id) {
		super();
		this.id = id;
	}
	
	public Bike() {}

	
	
	public Bike(int id, int code, int battery, int stationID, double price, String name, String status, String type,
			String description) {
		super();
		this.id = id;
		this.code = code;
		this.battery = battery;
		this.stationID = stationID;
		this.price = price;
		this.name = name;
		this.status = status;
		this.type = type;
		this.description = description;
	}

	
	
	public int getBattery() {
		return battery;
	}

	public void setBattery(int battery) {
		this.battery = battery;
	}

	public int getStationID() {
		return stationID;
	}

	public void setStationID(int stationID) {
		this.stationID = stationID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setBike(Bike b) {
		this.id = b.id;
		this.code = b.code;
		this.price = b.price;
		this.name = b.name;
		this.status = b.status;
		this.type = b.type;
		this.battery = b.battery;
		this.description = b.description;
		this.stationID = b.stationID;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
