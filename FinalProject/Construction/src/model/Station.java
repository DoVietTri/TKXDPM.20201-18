package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ultilities.Contants;

public class Station {

	/**
	 * mã bãi xe
	 */
	private int stationID;
	
	/**
	 * tổng số xe có trong bãi
	 */
	private int totalBike;
	
	/**
	 * Số xe hiện có
	 */
	private int available;
	
	/**
	 * Tên bãi xe
	 */
	private String name;
	
	/**
	 * Địa chỉ bãi xe
	 */
	private String address;
	
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
	
	/**
	 * Nhiệm vụ: Khởi tạo station từ database
	 * @param stationID: mã station
	 */
	public void setStationFromID(int stationID) {
		try {
			String sel = "SELECT * FROM Station WHERE stationID = " + stationID;
			Statement stmt = Contants.conn.createStatement();
			ResultSet rs;
			rs = stmt.executeQuery(sel);
			while(rs.next()) {
				this.stationID = stationID;
				this.name = rs.getString(2);
				this.address = rs.getString(3);
				this.totalBike = rs.getInt(4);
				this.available = rs.getInt(5);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Nhiệm vụ: Lấy tất cả xe có trong bãi
	 * @return danh sách xe
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ArrayList<Bike> getAllBikes() throws ClassNotFoundException, SQLException {
	
		ArrayList<Bike> list = new ArrayList<Bike>();
		String sel = "SELECT * FROM Bike WHERE stationID = \'" + this.stationID +"\'";
		Statement stmt = Contants.conn.createStatement();
		ResultSet rs = stmt.executeQuery(sel);
	
		while(rs.next()) {
			int id = rs.getInt(1);
			String type = rs.getString(2);
			String desc = rs.getString(3);
			int price = rs.getInt(4);
			String name = rs.getString(5);
			String status = rs.getString(6);
			int battery = rs.getInt(7);
			int stationID = rs.getInt(8);
			
			Bike st = new Bike(id, battery, stationID, price, name, status, type, desc) ;
			list.add(st);
		}
		return list;
	}
	
	/**
	 * Nhiệm vụ: Lấy thông tin chi tiết của xe
	 * @param bikeID: mã xe
	 * @return thông tin xe
	 */
	public Bike getBikeByID(int bikeID) {
		try {
			String select1 = "SELECT * FROM Bike WHERE bikeID = " + bikeID;
			Statement stmt = Contants.conn.createStatement();
			ResultSet rs = stmt.executeQuery(select1);
			while(rs.next()) {
				String type = rs.getString(2);
				String description = rs.getString(3);
				int price = rs.getInt(4);
				String name = rs.getString(5);
				String status = rs.getString(6);
				int battery = rs.getInt(7);
				int stationID = rs.getInt(8);
				return new Bike(bikeID, battery, stationID, price, name, status, type, description);
			} 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return  new Bike();
	}
	
	/**
	 * Nhiệm vụ: Khởi tạo station
	 * @param stationID: mã station
	 * @param totalBike: tổng số xe có trong bãi
	 * @param available: số xe còn lại trong bãi
	 * @param name: tên bãi
	 * @param address: địa chỉ bãi xe
	 */
	public Station(int stationID, int totalBike, int available, String name, String address) {
		super();
		this.stationID = stationID;
		this.totalBike = totalBike;
		this.available = available;
		this.name = name;
		this.address = address;
	}

	public Station() {}

	public int getStationID() {
		return stationID;
	}

	public void setStationID(int stationID) {
		this.stationID = stationID;
	}

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
