package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;

import ultilities.Contants;

public class Bike {
	public int id, price, battery, stationID;
	public String name, status, type, description;

	public Bike(int id) {
		super();
		this.id = id;
	}
	
	public Bike() {
		this.id = 0;
	}
	
	public Bike(int id, int battery, int stationID, int price, String name, String status, String type,
			String description) {
		super();
		this.id = id;
		this.battery = battery;
		this.stationID = stationID;
		this.price = price;
		this.name = name;
		this.status = status;
		this.type = type;
		this.description = description;
	}

	public void  setBikeFromID(int bikeID) {
		try {
			String select1 = "SELECT * FROM Bike WHERE bikeID = " + bikeID;
			Statement stmt = Contants.conn.createStatement();
			ResultSet rs = stmt.executeQuery(select1);

			while(rs.next()) {
				this.id = bikeID;
				this.type = rs.getString(2);
				this.description = rs.getString(3);
				this.price = rs.getInt(4);
				this.name = rs.getString(5);
				this.status = rs.getString(6);
				this.battery = rs.getInt(7);
				this.stationID = rs.getInt(8);
			} 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean updateBike(String status, int stationID)  {
		try {
			String upd = "UPDATE Bike SET stationID = " + stationID + ", status = \'" + status + "\' WHERE bikeID = " + this.id;
			Statement stmt = Contants.conn.createStatement();
			return stmt.execute(upd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean createRent(Time timeStart, int customerID)  {

		try {
			
			String ins = "INSERT INTO Rent(status, timeStart, bikeID, customerID) VALUES(?,?,?,?) ";
			PreparedStatement stm = Contants.conn.prepareStatement(ins);
			stm.setString(1, "renting");
			stm.setTime(2, timeStart);
			stm.setInt(3, this.id);
			stm.setInt(4, customerID);
			return stm.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public  int getDepositMoney() {
		switch (this.type) {
		case "1": {
			return 400000;
		}
		case "2": {

			return 700000;
		}
		case "3": {

			return 550000;
		}
		default:
			return 400000;
		}

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
		this.price = b.price;
		this.name = b.name;
		this.status = b.status;
		this.type = b.type;
		this.battery = b.battery;
		this.description = b.description;
		this.stationID = b.stationID;
	}


	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
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
