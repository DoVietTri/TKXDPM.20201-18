package model;

public class Customer {
	public Card card;	
	public int customerID, rentID;
	public String customerName;
	
	public Customer() {}

	
	
	public Customer(Card card, int customerID, int rentID, String customerName) {
		super();
		this.card = card;
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

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
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
