package ultilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
	public static int stationIDSelected = 0;
	public static int bikeIDSelected = 0;
	public static Card cardSelected = new Card();
	public static Connection conn;
	
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
	
	
	
	public static Time getCurrentTime() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String dateTimeNow = dtf.format(now);
		return Time.valueOf(dateTimeNow);
	}
	
	public static int calculateTotalTime(Time timeStart) {
		Time current = getCurrentTime();
		return (int) ((current.getTime() -timeStart.getTime())/60000);
	}
	
	public static int calculateMoney(int price, int totalTime) {
		if (totalTime <= 30) {
			return 10000;
		} else {
			int total = 10000;
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
