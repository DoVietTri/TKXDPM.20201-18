package controller;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Bike;
import model.Card;
import model.Rent;
import ultilities.Contants;
import ultilities.InterbankService;

public class ReturnBikeController implements Initializable {

	@FXML 
	Button btnSubmit, btnClose, btnRefresh;
	
	@FXML
	Label lbCardNumber, lbCardHolderName, lbDepositMoney, lbTotalMoneyRent, lbTotalMoney, lbBikeCode, lbBikePrice, lbTimeStart, lbTotalTime, lbMessage;
	
	Card card = new Card();
	Rent rent = new Rent();
	Bike bike = new Bike();
	
	int totalTimeRent = 0;
	int totalMoney = 0;
	
	Time current;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		getRentingInfo();
		addEvents();
	}
	
	public void addEvents() {
		btnSubmit.setOnMouseClicked(e -> {
			submitReturnBike();
		});
		btnRefresh.setOnMouseClicked(e -> {
			getRentingInfo();
		});
		btnClose.setOnMouseClicked(e -> {
			Stage stage = (Stage) btnClose.getScene().getWindow();
			stage.close();
		});
	}
	
	public void getRentingInfo() {
		rent.setRentFromID(Contants.currentRentID);
		bike.setBikeFromID(rent.bikeID);
		card.setCard(HomeController.currentUser.getCustomerCard());
		// update view
		updateView();
	}
	
	public void updateView() {
		current = Contants.getCurrentTime();
		totalTimeRent = (int) ((current.getTime() - rent.timeStart.getTime())/60000);
		totalMoney = Contants.calculateMoney(bike.price, totalTimeRent);
		
		lbCardHolderName.setText(card.cardHolderName);
		lbCardNumber.setText(card.cardNumber);
		lbBikeCode.setText("" + bike.id);
		lbBikePrice.setText("" + Contants.toString(bike.price));
		lbTimeStart.setText("" + rent.timeStart);
		totalTimeRent = Contants.calculateTotalTime(rent.timeStart);
		lbTotalTime.setText("" + totalTimeRent);
		int depositMoney = bike.getDepositMoney();
		lbDepositMoney.setText("" + depositMoney);
		totalMoney = Contants.calculateMoney(bike.price, totalTimeRent);
		lbTotalMoneyRent.setText("" + totalMoney);
		
		if(depositMoney > totalMoney) {
			lbMessage.setText("Số tiền bạn được hoàn lại: ");
			lbTotalMoney.setText("" + (depositMoney-totalMoney));
		} else {
			lbMessage.setText("Số tiền bạn cần thanh toán: ");
			lbTotalMoney.setText("" + (totalMoney-depositMoney));
		}
	}
	
	public void submitReturnBike() {
		current = Contants.getCurrentTime();
		totalTimeRent = Contants.calculateTotalTime(rent.timeStart);
		totalMoney = Contants.calculateMoney(bike.price, totalTimeRent);
		bike.updateBike("available", Contants.stationIDSelected);
		
		rent.updateRent(current, totalTimeRent);

		createTransaction(totalTimeRent, totalMoney, totalMoney);
		updateRentingBike();
		showMessage("Trả xe thành công");
	}
	
	public void createTransaction(int totalTimeRent, int totalMoney, int rentID) {
		rent.createTransaction("pay", "Pay for rent bike " + bike.id, totalMoney);
	}
	
	public void updateRentingBike() {
		Contants.currentRentID = 0;
	}
	
	public void showMessage(String mess) {
		Alert dialog = new Alert(AlertType.ERROR);
		dialog.setTitle("Thông báo");
		dialog.setHeaderText(mess);
		dialog.showAndWait();
	}

}
