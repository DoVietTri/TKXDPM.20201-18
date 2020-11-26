package model;

public class Card {

	public int cardID, issuingBank;
	public String cardHolderName, cardNumber, transactionDescription, expirationDate , securityCode;
	
	public Card() {}
	
	public Card(int cardID) {
		this.cardID = cardID;
	}
	
	public Card(int cardID, String cardHolderName, String cardNumber, String transactionDescription,
			String expirationDate, String securityCode, int issuingBank) {
		super();
		this.cardID = cardID;
		this.cardHolderName = cardHolderName;
		this.cardNumber = cardNumber;
		this.transactionDescription = transactionDescription;
		this.expirationDate = expirationDate;
		this.securityCode = securityCode;
		this.issuingBank = issuingBank;
	}
	
	public void setCard(Card c) {
		this.cardID = c.cardID;
		this.cardHolderName = c.cardHolderName;
		this.cardNumber = c.cardNumber;
		this.transactionDescription = c.transactionDescription;
		this.expirationDate = c.expirationDate;
		this.securityCode = c.securityCode;
		this.issuingBank = c.issuingBank;
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
	public int getIssuingBank() {
		return issuingBank;
	}
	public void setIssuingBank(int issuingBank) {
		this.issuingBank = issuingBank;
	}
	
	
	
}
