package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;

import ultilities.Contants;

public class Rent {
	
	/**
	 * mã thuê xe
	 */
	private int rentID;
	
	/**
	 * Mã khách hàng
	 */
	private int customerID;
	
	/**
	 * Mã xe đang thuê
	 */
	private int bikeID;
	
	/**
	 * Thời gian bắt đầu thuê
	 */
	private  Time timeStart;
	
	/**
	 * Thời gian kết thúc thuê xe
	 */
	private Time timeEnd;
	
	/**
	 * Tổng thời gian thuê xe
	 */
	private int totalTimeRent;
	
	/**
	 * Trạng thái của mã thuê
	 */
	private String status;
	
	public Rent() {
		this.rentID = 0;
	}
	
	public Rent(int id) {
		this.rentID = id;
	}
	
	public void setRent(Rent r) {
		this.rentID = r.rentID;
		this.customerID = r.customerID;
		this.bikeID = r.bikeID;
		this.timeStart = r.timeStart;
		this.timeEnd = r.timeEnd;
		this.totalTimeRent = r.totalTimeRent;
	}
	
	/**
	 * Nhiệm vụ: update lại thông tin thuê trước đó 
	 * @param timeEnd: thời gian trả xe
	 * @param totalTimeRent: tổng thời gian thuê xe
	 * @return: update thành công hay thất bại
s	 */
	public boolean updateRent(Time timeEnd, int totalTimeRent) {
		try {
			String ins = "UPDATE Rent SET status = ?, timeEnd = ?, totalTimeRent = ? WHERE RentID = ?";
			PreparedStatement stm = Contants.conn.prepareStatement(ins);
			stm.setString(1, "done");
			stm.setTime(2, timeEnd);
			stm.setInt(3, totalTimeRent);
			stm.setInt(4, this.rentID);
			stm.execute();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Nhiệm vụ: Định nghĩa thông tin thuê từ bảng Rent trong csdl
	 * @param rentID: mã thuê
	 */
	public void setRentFromID(int rentID) {
		try {
			String sel = "SELECT * FROM Rent WHERE rentID = " + rentID;
			Statement stmt = Contants.conn.createStatement();
			ResultSet rs;
			rs = stmt.executeQuery(sel);
			while(rs.next()) {
				this.rentID = rentID;
				this.status = rs.getString(2);
				this.timeStart = rs.getTime(3);
				this.timeEnd = rs.getTime(4);
				this.totalTimeRent = rs.getInt(5);
				this.bikeID = rs.getInt(6);
				this.customerID = rs.getInt(7);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Nhiệm vụ: tạo một giao dịch
	 * @param code: mã giao dịch
	 * @param transactionName: thông tin giao dịch
	 * @param totalMoney: tổng số tiền giao dịch
	 * @return: giao dịch thành công hay thất bại
	 */
	public boolean createTransaction(String code, String transactionName,int totalMoney) {
		try {
			String ins = "INSERT INTO Transactions(code, transactionName, totalMoney, rentID) VALUES(?,?,?,?)";
			PreparedStatement stm = Contants.conn.prepareStatement(ins);
			stm.setString(1, code);
			stm.setString(2, transactionName);
			stm.setInt(3, totalMoney);
			stm.setInt(4, this.rentID);
			stm.execute();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * Nhiệm vụ: khởi tạo Rent
	 * @param rentID: mã rent
	 * @param status: trạng thái
	 * @param customerID: mã khách hàng
	 * @param bikeID: mã xe
	 * @param timeStart: thời gian bắt đầu thuê
	 * @param timeEnd: thời gian kết thúc
	 * @param totalTimeRent: tổng thời gian thuê
	 */
	public Rent(int rentID, String status, int customerID, int bikeID, Time timeStart, Time timeEnd, int totalTimeRent) {
		super();
		this.rentID = rentID;
		this.status = status;
		this.customerID = customerID;
		this.bikeID = bikeID;
		this.timeStart = timeStart;
		this.timeEnd = timeEnd;
		this.totalTimeRent = totalTimeRent;
	}

	public Rent(int rentID, String status, int customerID, int bikeID, Time timeStart) {
		super();
		this.rentID = rentID;
		this.status = status;
		this.customerID = customerID;
		this.bikeID = bikeID;
		this.timeStart = timeStart;

	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
