package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;

import ultilities.Contants;

public class Customer {
	public String cardNumber;	
	public int customerID, rentID;
	public String customerName;
	
	public Customer() {}
	
	public  Rent getRentingBike(){
		try {
			String select1 = "SELECT * FROM Rent WHERE customerID = " + this.customerID +" AND status=\'renting\'";
			Statement stmt = Contants.conn.createStatement();
			ResultSet rs = stmt.executeQuery(select1);
			while(rs.next()) {
				int rentID = rs.getInt(1);
				String status = rs.getString(2);
				Time timeStart = rs.getTime(3);
				int bikeID = rs.getInt(6);
		
				return new Rent(rentID,status, customerID, bikeID, timeStart);
			} 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Rent();
	}
	
	public Card getCustomerCard() {
		try {
			String select1 = "SELECT * FROM Card WHERE cardNumber = \'" + this.cardNumber +"\'";
			Statement stmt = Contants.conn.createStatement();
			ResultSet rs = stmt.executeQuery(select1);
			while(rs.next()) {
				int cardID = rs.getInt(1);
				String cardHolderName = rs.getString(2);
				String cardNumber = rs.getString(3);
				String expirationDate = rs.getString(5);
				String securityCode = rs.getString(6);
				String issuingBank = rs.getString(7);
				Card card = new Card(cardID, cardHolderName, cardNumber, cardHolderName + " chuyen tien", expirationDate, securityCode, issuingBank);
				return card;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Card();
	}
	
	public Customer(int customerID, String cardNumber,  int rentID, String customerName) {
		super();
		this.cardNumber = cardNumber;
		this.customerID = customerID;
		this.rentID = rentID;
		this.customerName = customerName;
	}

	public Customer(int customerID, int rentID, String customerName) {
		super();
		
		this.customerID = customerID;
		this.rentID = rentID;
		this.customerName = customerName;
	}
	
	public Customer(int id, String name) {
		this.customerID = id;
		this.customerName = name;
	}



	public String getCardNumber() {
		return cardNumber;
	}



	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}



	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public int getRentID() {
		return rentID;
	}

	public void setRentID(int rentID) {
		this.rentID = rentID;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	
}
