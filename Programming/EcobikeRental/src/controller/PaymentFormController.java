package controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Bike;
import model.Card;
import model.Rent;
import ultilities.Configs;
import ultilities.Contants;
import ultilities.InterbankService;

public class PaymentFormController implements Initializable {

	@FXML 
	Button btnOK, btnClose;
	
	@FXML 
	Label lbMessage, lbMoney;
	
	@FXML 
	GridPane form;
	
	@FXML
	TextField txtCardHolderName, txtCardNumber, txtCardBank, txtCardExpirationDate, txtCardCVV, txtContent;
	
	Card card;
	Bike bike = new Bike();
	Time current;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		bike.setBikeFromID(Contants.bikeIDSelected);
		addEvents();
		setupTextField();
		
	}
	
	public void addEvents() {
		btnOK.setOnMouseClicked(e -> {
			submitPaymentForm();
		});
		btnClose.setOnMouseClicked(e -> {
			Stage stage = (Stage) btnClose.getScene().getWindow();
			stage.close();
		});

		
	}
	
	public void submitPaymentForm() {
		if (checkBlankField()) {
			card.cardHolderName = txtCardHolderName.getText();
			card.cardNumber = txtCardNumber.getText();
			card.expirationDate = txtCardExpirationDate.getText();
			card.securityCode = txtCardCVV.getText();
			card.issuingBank = txtCardBank.getText();

			current = Contants.getCurrentTime();
			
			bike.createRent(current, Contants.currentUserID);
			Contants.currentRentID = HomeController.currentUser.getRentingBike().rentID;
			Rent rent = new Rent(Contants.currentRentID);
			rent.createTransaction("deposit", "Deposit rent bike" , bike.getDepositMoney());
			
			updateBike();
			
			showMessage(Configs.MESSAGE_SUCCESS);
		} else {
			showMessage("Hãy nhập đầy đủ thông tin !");
		}
	}
	
	public void rentBike() {
		int depositMoney = 10000;
		String code;
		try {
			code = InterbankService.processTransaction(card, "pay", depositMoney);
			System.out.print("Code: " + code);
			if("00".equals(code)) {
				createRent();
				updateBike();
				showMessage(Configs.MESSAGE_SUCCESS);
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void updateView() {
		showMessage(Configs.MESSAGE_SUCCESS);
	}
	
	public void updateBike() {
		bike.updateBike("renting", bike.stationID);
	}
	
	public void createRent() {
		
	}
	
	public boolean checkBlankField() {
		if (txtCardHolderName.getText().isEmpty()) return false;
		if (txtCardNumber.getText().isEmpty()) return false;
		if (txtCardBank.getText().isEmpty()) return false;
		if (txtCardExpirationDate.getText().isEmpty()) return false;
		if (txtCardCVV.getText().isEmpty()) return false;
		return true;
	}
	
	public void showMessage(String mess) {
		Alert dialog = new Alert(AlertType.ERROR);
		dialog.setTitle(Configs.TITLE_FOR_ALERT);
		dialog.setHeaderText(mess);
		dialog.showAndWait();
	}
	
	public void clearTextField() {
		txtCardHolderName.setText("");
		txtCardNumber.setText("");
		txtCardBank.setText("");
		txtCardExpirationDate.setText("");
		txtCardCVV.setText("");
		txtContent.setText(HomeController.currentUser.customerName + " thue xe " + bike.getId());
	}
	
	public void setupTextField() {
		card = new Card();
		card.setCardFromCardNumber(Contants.cardSelected.cardHolderName);
		
		lbMoney.setText("" + Contants.getDepositMoney(bike.getType()));
		txtCardHolderName.setText("" + card.cardHolderName);
		txtCardNumber.setText("" + card.cardNumber);
		txtCardBank.setText("" + card.issuingBank);
		txtCardExpirationDate.setText("" + card.expirationDate);
		txtCardCVV.setText("");
		txtContent.setText(HomeController.currentUser.customerName + " thue xe " + bike.getId());
	}

}
  