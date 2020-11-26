package ultilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;

import controller.HomeController;
import model.Bike;
import model.Card;
import model.Customer;
import model.Rent;
import model.Station;

public final class Contants {
	public Contants() {}
	
	public static Station stationSelected = new Station();
	public static Bike bikeSelected = new Bike();
	
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
			String desc = rs.getString(3);
			double price = rs.getDouble(4);
			String name = rs.getString(5);
			String status = rs.getString(6);
			int battery = rs.getInt(7);
			int stationID = rs.getInt(8);
			
			Bike st = new Bike(id, battery, stationID, price, name, status, type, desc) ;
			list.add(st);
		}
		return list;
	}
	
	public static Rent getRentingBike(int customerID ) throws ClassNotFoundException, SQLException {
		Rent rent = new Rent();
		Connection conn = getSQLServerConnection();
		String select1 = "SELECT rentID FROM Customer WHERE customerID = \'" + customerID +"\' AND rentID IS NOT NULL";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(select1);
		int rentID = 0;
		while(rs.next()) {
			rentID = rs.getInt(1);
		} 
		if(rentID == 0) {
			return rent;
		}
		
		String select2 = "SELECT * FROM Rent WHERE rentID = \'" + rentID +"\'";
		rs = stmt.executeQuery(select2);
		while(rs.next()) {
			Time timeStart = rs.getTime(2);
			Time timeEnd = rs.getTime(2);
		//	Time timeNow = Time.
			int bikeID = rs.getInt(5);
			int totalTimeRent = 90;
			Rent newRent = new Rent(rentID, customerID, bikeID, timeStart, timeEnd, totalTimeRent);
			return newRent;
		} 
		
		return rent;
	}
	
	public static Bike getBikeInfomation(int bikeID) throws ClassNotFoundException, SQLException {
		Bike bike = new Bike();
		
		Connection conn = getSQLServerConnection();
		String select1 = "SELECT * FROM Bike WHERE bikeID = \'" + bikeID +"\'";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(select1);

		while(rs.next()) {
			double price = rs.getDouble(4);
			int battery = rs.getInt(7);
			int stationID = rs.getInt(8);
			Bike bike1 = new Bike(bikeID, battery, stationID, price, "", "", "", "");
			return bike1;
		} 
		
		return bike;
	}
	
	public static Card getCustomerCard() throws ClassNotFoundException, SQLException {
		Connection conn = getSQLServerConnection();
		String select1 = "SELECT * FROM Card WHERE cardID = 111";
				// + HomeController.currentUser.getCard().getCardID();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(select1);
		while(rs.next()) {
			int cardID = rs.getInt(1);
			String cardHolderName = rs.getString(2);
			String cardNumber = rs.getString(3);
			String expirationDate = rs.getString(5);
			String securityCode = rs.getString(6);
			int issuingBank = rs.getInt(7);
			Card card = new Card(cardID, cardHolderName, cardNumber, "", expirationDate, securityCode, issuingBank);
			return card;
		} 
		return new Card();
	}
	
	public static void rentBike() {
		
	}
	
	public static void returnBike() {
		
	}
	
	public static long calculateMoney(Double price, long totalTime) {
		if (totalTime <= 30) {
			return 10000;
		} else {
			long total = 10000;
			totalTime -= 30;
			total += price*(totalTime/15);
			totalTime -= 15*(totalTime/15);
			if(totalTime > 0) total += price;
			return total;
		}
	}
	
	
	public static String toString(double d) {
		
		String s = "" + (int)d ;
		String ans = "";
		int n = s.length();
		for (int i = 1; i <= n; i++ ){
			ans += s.charAt(n-i);
			if(i%3 == 0) {
				ans += ",";
			}
		}
		String rs = "";
		for(int i = ans.length()-1; i >= 0 ; i-- ) {
			rs += ans.charAt(i);
		}
		return rs;
	}
	
}
