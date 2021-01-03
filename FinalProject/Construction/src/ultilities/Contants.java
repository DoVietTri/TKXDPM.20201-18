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
import model.Bike;
import model.Card;
import model.Customer;
import model.Rent;
import model.Station;

public final class Contants {
	public Contants() {}
	
	/**
	 * id của user
	 */
	public static int currentUserID = 20173410;
	
	/**
	 * id rent
	 */
	public static int currentRentID = 0;
	
	/**
	 * id station khi click vào
	 */
	public static int stationIDSelected = 0;
	
	/**
	 * id của xe
	 */
	public static int bikeIDSelected = 0;
	public static Card cardSelected = new Card();
	
	/**
	 * Biến tạo kết nối đến cơ sở dữ liệu
	 */
	public static Connection conn;
	
	/**
	 * Nhiệm vụ: kết nối đến cơ sở dữ liệu
	 * @return kết quả kết nối
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Connection getSQLServerConnection() throws ClassNotFoundException, SQLException {

		 String dbURL = "jdbc:sqlserver://localhost;databaseName=EcoBikeRentalDatabase;user=sa;password=tri.dv27091999";
		 Connection conn = DriverManager.getConnection(dbURL);
	 
	     return conn;
	 }
	
	/**
	 * Nhiệm vụ: Lấy tất cả các bãi xe có trong cơ sở dữ liệu
	 * @return danh sách bãi xe
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
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
	
	
	/**
	 * Nhiệm vụ: Lấy thời gian hiện tại
	 * @return thời gian hiện tại
	 */
	public static Time getCurrentTime() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String dateTimeNow = dtf.format(now);
		return Time.valueOf(dateTimeNow);
	}
	
	/**
	 * Nhiệm vụ: tính tổng thời gian thuê xe
	 * @param timeStart: thời gian bắt đầu thuê
	 * @return thời gian thuê xe tính theo giờ
	 */
	public static int calculateTotalTime(Time timeStart) {
		Time current = getCurrentTime();
		return (int) ((current.getTime() -timeStart.getTime())/60000);
	}
	
	/**
	 * Nhiệm vụ: tính tiền thuê xe
	 * @param price: giá thuê xe
	 * @param totalTime: tổng thời gian thuê xe
	 * @return số tiền phải trả
	 */
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
	
	/**
	 * Nhiệm vụ: chuyển đổi một số thành một String
	 * @param d: số cần chuyển đổi
	 * @return chuỗi 
	 */
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
	
	/**
	 * Nhiệm vụ: Định nghĩa các thông báo cho người dùng
	 * @param res
	 * @return thông báo
	 */
	public static String response(String res) {
		switch(res) {
		case "00" : return "Thành công";
		case "01" : return "Thẻ không hợp lí, hãy dùng thẻ khác";
		case "02" : return "Thẻ không đủ số dư";	
		case "03" : return "Server hiện tại đang bảo trì, hãy thử lại sau";	
		case "04" : return "Bạn không thể thanh toán trên thiết bị này, hãy thử lại sau";	
		case "05" : return "Bạn cần nhập đầy đủ các thông tin giao dịch";
		case "06" : return Configs.SOME_ERROR_OCCUR;
		case "07" : return Configs.SOME_ERROR_OCCUR;
		}
		return Configs.SOME_ERROR_OCCUR;
	}
	
}
