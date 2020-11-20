package ultilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import model.Bike;
import model.Customer;
import model.Station;

public final class Contants {
	public Contants() {}
	
	public static Station stationSelected = new Station();
	public static Bike bikeSelected = new Bike();
	//public static Customer customer = new Customer();
	
	public static Connection getSQLServerConnection() throws ClassNotFoundException, SQLException {

		 String dbURL = "jdbc:sqlserver://localhost;databaseName=EcoBikeRental;user=group18;password=123456";
		    Connection conn = DriverManager.getConnection(dbURL);
	 
	     return conn;
	 }
	
	public static ArrayList<Station> getAllStations() throws ClassNotFoundException, SQLException {
		
		Connection conn = getSQLServerConnection();
		
		ArrayList<Station> list = new ArrayList<Station>();
		
		String sel = "SELECT * FROM Station";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sel);
	
		while(rs.next()) {
			int id = rs.getInt(1);
			String name = rs.getString(2);
			String address = rs.getString(3);
			int totalBike = rs.getInt(4);
			int available = rs.getInt(5);
			
			Station st = new Station(id, totalBike, available, name, address) ;
			list.add(st);
		}
		return list;
	}
	
	public static ArrayList<Bike> getAllBikes(int stationId) throws ClassNotFoundException, SQLException {
		Connection conn = getSQLServerConnection();
		ArrayList<Bike> list = new ArrayList<Bike>();
		String sel = "SELECT * FROM Bike WHERE stationID = \'" + stationId +"\'";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sel);
	
		while(rs.next()) {
			int id = rs.getInt(1);
			String type = rs.getString(2);
			int code = rs.getInt(3);
			String desc = rs.getString(4);
			double price = rs.getDouble(5);
			String name = rs.getString(6);
			String status = rs.getString(7);
			int battery = rs.getInt(8);
			int stationID = rs.getInt(9);
			
			Bike st = new Bike(id, code, battery, stationID, price, name, status, type, desc) ;
			list.add(st);
		}
		return list;
	}
	
	public static Bike getBikeInfomation(int bikeId) {
		Bike bike = new Bike(112233);
		return bike;
	}
	
}
