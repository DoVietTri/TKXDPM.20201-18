package model;

public class Transaction {
	String code, transactionName;
	int totalTimeRent, totalMoney, rentID;
	
	public Transaction() {}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTransactionName() {
		return transactionName;
	}

	public void setTransactionName(String transactionName) {
		this.transactionName = transactionName;
	}

	public int getTotalTimeRent() {
		return totalTimeRent;
	}

	public void setTotalTimeRent(int totalTimeRent) {
		this.totalTimeRent = totalTimeRent;
	}

	public int getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(int totalMoney) {
		this.totalMoney = totalMoney;
	}

	public int getRentID() {
		return rentID;
	}

	public void setRentID(int rentID) {
		this.rentID = rentID;
	}
	
}
