package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ultilities.Contants;

public class Card {
	
	/**
	 * id của thẻ
	 */
	private int cardID;
	
	/**
	 * Tên chủ thẻ
	 */
	private String cardHolderName;
	
	/**
	 * Mã thẻ
	 */
	private String cardNumber;
	
	/**
	 * Nội dung giao dịch
	 */
	private String transactionDescription;
	
	/**
	 * Ngày hết hạn 
	 */
	private String expirationDate;
	
	/**
	 * Mã bảo mật
	 */
	private String securityCode;
	
	/**
	 * Ngân hàng phát sinh thẻ
	 */
	private String issuingBank;
	
	public Card() {}
	
	/**
	 * Nhiệm vụ: khởi tao Card
	 * @param cardID
	 */
	public Card(int cardID) {
		this.cardID = cardID;
	}
	
	/**
	 * Nhiệm vụ: Khởi tạo Card
	 * @param cardID:
	 * @param cardHolderName: Tên chủ thẻ
	 * @param cardNumber: mã thẻ
	 * @param transactionDescription: mô tả giao dịch
	 * @param expirationDate: ngày hết hạn
	 * @param securityCode: mã bảo mật (cvv)
	 * @param issuingBank: ngân hàng phát hàng
	 */
	public Card(int cardID, String cardHolderName, String cardNumber, String transactionDescription,
			String expirationDate, String securityCode, String issuingBank) {
		super();
		this.cardID = cardID;
		this.cardHolderName = cardHolderName;
		this.cardNumber = cardNumber;
		this.transactionDescription = transactionDescription;
		this.expirationDate = expirationDate;
		this.securityCode = securityCode;
		this.issuingBank = issuingBank;
	}
	
	/**
	 * Nhiệm vụ: Định nghĩa card
	 * @param c: thông tin thẻ
	 */
	public void setCard(Card c) {
		this.cardID = c.cardID;
		this.cardHolderName = c.cardHolderName;
		this.cardNumber = c.cardNumber;
		this.transactionDescription = c.transactionDescription;
		this.expirationDate = c.expirationDate;
		this.securityCode = c.securityCode;
		this.issuingBank = c.issuingBank;
	}
	
	/**
	 * Nhiệm vụ: định nghĩa card từ cơ sở dữ liệu
	 * @param cardNumber
	 */
	public void setCardFromCardNumber(String cardNumber) {
		try {
			String select1 = "SELECT * FROM Card WHERE cardNumber = \'" + cardNumber +"\'";
			Statement stmt = Contants.conn.createStatement();
			ResultSet rs = stmt.executeQuery(select1);
			while(rs.next()) {
				this.cardID = rs.getInt(1);
				this.cardHolderName = rs.getString(2);
				this.cardNumber = rs.getString(3);
				this.expirationDate = rs.getString(5);
				this.securityCode = rs.getString(6);
				this.issuingBank = rs.getString(7);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getCardID() {
		return cardID;
	}
	public void setCardID(int cardID) {
		this.cardID = cardID;
	}
	public String getCardHolderName() {
		return cardHolderName;
	}
	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getTransactionDescription() {
		return transactionDescription;
	}
	public void setTransactionDescription(String transactionDescription) {
		this.transactionDescription = transactionDescription;
	}
	public String getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
	public String getSecurityCode() {
		return securityCode;
	}
	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}
	public String getIssuingBank() {
		return issuingBank;
	}
	public void setIssuingBank(String issuingBank) {
		this.issuingBank = issuingBank;
	}	
}
