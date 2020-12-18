package ultilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;

import controller.HomeController;
import controller.ReturnBikeController;
import model.Bike;
import model.Card;
import model.Customer;
import model.Rent;
import model.Station;

public final class Contants {
	public Contants() {}
	
	public static int currentUserID = 20173410;
	public static int currentRentID = 0;
	public static Station stationSelected = new Station();
	public static Bike bikeSelected = new Bike();
	public static Card cardSelected = new Card();
	
	public static Connection getSQLServerConnection() throws ClassNotFoundException, SQLException {

		 String dbURL = "jdbc:sqlserver://localhost;databaseName=EcoBikeRentalDatabase;user=group18;password=123456";
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
	
	public static Rent getRentingBike(int customerID ){
		Connection conn;
		try {
			conn = getSQLServerConnection();
			String select1 = "SELECT * FROM Rent WHERE customerID = " + customerID +" AND status=\'renting\'";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(select1);
			while(rs.next()) {
				int rentID = rs.getInt(1);
				String status = rs.getString(2);
				Time timeStart = rs.getTime(3);
				int bikeID = rs.getInt(6);
		
				return new Rent(rentID,status, customerID, bikeID, timeStart);
			} 
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		return new Rent();
	}
	
	public static Bike getBikeInfomation(int bikeID) {
		
		Connection conn;
		try {
			conn = getSQLServerConnection();
			String select1 = "SELECT * FROM Bike WHERE bikeID = \'" + bikeID +"\'";
			Statement stmt = conn.createStatement();
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
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return  new Bike();
	}
	
	public static Card getCustomerCard() throws ClassNotFoundException, SQLException {
		Connection conn = getSQLServerConnection();
		String select1 = "SELECT * FROM Card WHERE cardNumber = \'118609_group18_2020\'";
				// + HomeController.currentUser.getCard().getCardID();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(select1);
		while(rs.next()) {
			int cardID = rs.getInt(1);
			String cardHolderName = rs.getString(2);
			String cardNumber = rs.getString(3);
			String expirationDate = rs.getString(5);
			String securityCode = rs.getString(6);
			String issuingBank = rs.getString(7);
			Card card = new Card(cardID, cardHolderName, cardNumber, "", expirationDate, securityCode, issuingBank);
			return card;
		} 
		return new Card();
	}
	
	public static boolean updateBike(int bikeID, String status)  {
		Connection conn;
		try {
			conn = getSQLServerConnection();
			String upd = "UPDATE Bike SET status = \'" + status + "\' WHERE bikeID = \'" + bikeID + "\'" ;
			Statement stmt = conn.createStatement();
			return stmt.execute(upd);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean rentBike(Time timeStart, int bikeID, int customerID)  {
		Connection conn;
		try {
			conn = getSQLServerConnection();
			String ins = "INSERT INTO Rent(status, timeStart, bikeID, customerID) VALUES(?,?,?,?) ";
			PreparedStatement stm = conn.prepareStatement(ins);
			stm.setString(1, "renting");
			stm.setTime(2, timeStart);
			stm.setInt(3, bikeID);
			stm.setInt(4, customerID);
			return stm.execute();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean returnBike(int rentID, Time timeEnd, int totalTimeRent) {
		Connection conn;
		try {
			conn = getSQLServerConnection();
			String ins = "UPDATE Rent SET status = ?, timeEnd = ?, totalTimeRent = ? WHERE RentID = ?";
			PreparedStatement stm = conn.prepareStatement(ins);
			stm.setString(1, "done");
			stm.setTime(2, timeEnd);
			stm.setInt(3, totalTimeRent);
			stm.setInt(4, rentID);
			return stm.execute();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean createTransaction(String code, String transactionName,int totalTimeRent, int totalMoney, int rentID) {
		Connection conn;
		try {
			conn = getSQLServerConnection();
			String ins = "INSERT INTO Transaction(code, transactionName, totalTimeRent, totalMoney, rentID) VALUES(?,?,?,?,?)";
			PreparedStatement stm = conn.prepareStatement(ins);
			stm.setString(1, code);
			stm.setString(2, transactionName);
			stm.setInt(3, totalTimeRent);
			stm.setInt(4, totalMoney);
			stm.setInt(5, rentID);
			return stm.execute();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	
	public static long calculateMoney(int price, long totalTime) {
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
	
	
	public static String toString(long d) {
		
		String s = "" + d ;
		String ans = "";
		int n = s.length();
		for (int i = 1; i <= n; i++ ){
			ans += s.charAt(n-i);
			if(i%3 == 0 && i != n) {
				ans += ",";
			}
		}
		String rs = "";
		for(int i = ans.length()-1; i >= 0 ; i-- ) {
			rs += ans.charAt(i);
		}
		return rs;
	}
	
	public static int getDepositMoney(String type) {
		switch (type) {
		case "1": {

			return 100000;
		}
		case "2": {

			return 200000;
		}
		case "3": {

			return 300000;
		}
		default:
			return 100000;
		}

	}
	
}
