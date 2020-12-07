package controller;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Time;
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
import ultilities.Contants;
import ultilities.InterbankService;

public class ReturnBikeController implements Initializable {

	@FXML 
	Button btnSubmit, btnClose, btnRefresh;
	
	@FXML
	Label lbCardNumber, lbCardHolderName, lbDepositMoney, lbBikeCode, lbBikePrice, lbTimeStart, lbTotalTime;
	
	Card card = new Card();
	Bike bike = new Bike();
	int totalTimeRent = 0;
	int totalMoney = 0;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		bike.setBike(Contants.getBikeInfomation(Contants.bikeSelected.id));
		addEvents();
	}
	
	public void addEvents() {
		btnSubmit.setOnMouseClicked(e -> {
			submitReturnBike();
		});
		btnClose.setOnMouseClicked(e -> {
			Stage stage = (Stage) btnClose.getScene().getWindow();
			stage.close();
		});
	}
	
	public void submitReturnBike() {
		
		
		try {
			InterbankService.processTransaction(card, "pay", 1000);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateBike() {
		Contants.updateBike(bike.id, "done");
	}
	
	public void updateRent(Time timeEnd, int totalTimeRent) {
		int rentID = Contants.getRentingBike(HomeController.currentUser.customerID).rentID;
		Contants.returnBike(rentID, timeEnd, totalTimeRent);;
	}
	
	public void createTransaction(int totalTimeRent, int totalMoney, int rentID) {
		Contants.createTransaction("pay", "Pay for rent bike " + bike.id, totalTimeRent, totalMoney, rentID);
	}
	
	public void showMessage(String mess) {
		Alert dialog = new Alert(AlertType.ERROR);
		dialog.setTitle("Thông báo");
		dialog.setHeaderText(mess);
		dialog.showAndWait();
	}

}
