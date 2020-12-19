package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import model.Bike;
import ultilities.Configs;
import ultilities.Contants;

public class ViewBikeController implements Initializable {

	@FXML
	Button btnRentBike, btnClose;
	
	@FXML
	Label lbBikeCode, lbBikeDesc, lbBikeStatus, lbBikeName, lbBikePrice, lbBikeBattery, lbBikeType;
	
	@FXML
	ImageView imgBike;
	
	Bike bike = new Bike();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		bike.setBikeFromID(Contants.bikeIDSelected);;
		addEvents();
		showBikeInfo();
		
	}
	
	public void addEvents() {
		
		btnRentBike.setOnMouseClicked(e -> {
			checkRentAvailable();
		});
		
		btnClose.setOnMouseClicked(e -> {
			Stage stage = (Stage) btnClose.getScene().getWindow();
			stage.close();
		});

		
	}
	
	public void showBikeInfo() {
		bike.setBikeFromID(Contants.bikeIDSelected);;
		String type = bike.getType();
		Image img = new Image("/resources/bike"+ type +".jpg");
		
		imgBike.setImage(img);
		lbBikeCode.setText("" + bike.getId());
		lbBikeBattery.setText("" + bike.getBattery());
		lbBikeName.setText(bike.getName());
		lbBikeType.setText("" + bike.getDepositMoney());

		lbBikeStatus.setText(bike.getStatus());
		
	
		lbBikePrice.setText("" +Contants.toString(bike.getPrice()));
		lbBikeDesc.setText(bike.getDescription());
		
		if("available".equals(bike.status)) {
			btnRentBike.setDisable(false);
			lbBikeStatus.setStyle("-fx-text-fill: black;");
		} else {
			btnRentBike.setDisable(true);
			lbBikeStatus.setStyle("-fx-text-fill: red;");
		}
		
	}
	
	public void checkRentAvailable() {
		if(HomeController.currentUser.getRentingBike().rentID != 0) {
			showMessage(Configs.CUSTOMER_IS_RENTING);
			return;
		}
		if(!"available".equals(bike.status)) {
			showMessage(Configs.BIKE_IS_RENTING);
			return;
		}
		showPaymentForm();
	}
	
	public void showMessage(String mess) {
		Alert dialog = new Alert(AlertType.ERROR);
		dialog.setTitle(Configs.TITLE_FOR_ALERT);
		dialog.setHeaderText(mess);
		dialog.showAndWait();
	}
	
	public void showPaymentForm() {
		try {
			
			Parent root = FXMLLoader.load(getClass().getResource("/view/PaymentForm.fxml"));
			
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("PaymentForm");
			stage.show();

	} 	 catch (IOException e) {
		
			e.printStackTrace();
			
		}
	}
}
