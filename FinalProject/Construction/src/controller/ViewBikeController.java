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

	/**
	 * Các nút được định nghĩa id bên view
	 */
	@FXML
	Button btnRentBike, btnClose;
	
	/**
	 * Các nhãn dán được định nghĩa bên view
	 */
	@FXML
	Label lbBikeCode, lbBikeDesc, lbBikeStatus, lbBikeName, lbBikePrice, lbBikeDepositMoney;
	
	@FXML
	ImageView imgBike;
	
	Bike bike = new Bike();
	
	/**
	 * Khởi chạy
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		bike.setBikeFromID(Contants.bikeIDSelected);;
		addEvents();
		showBikeInfo();
		
	}
	
	/**
	 * Bắt sự kiện click vào các nút
	 */
	public void addEvents() {
		
		btnRentBike.setOnMouseClicked(e -> {
			if (checkRentAvailable()) {
				showPaymentForm();
			}
		});
		
		btnClose.setOnMouseClicked(e -> {
			Stage stage = (Stage) btnClose.getScene().getWindow();
			stage.close();
		});

		
	}
	/**
	 * Lấy thông tin chi tiết của xe và Hiển thị thông tin chi tiết của xe
	 */
	public void showBikeInfo() {
		bike.setBikeFromID(Contants.bikeIDSelected);;
		String type = bike.getType();
		Image img = new Image("/resources/bike"+ type +".jpg");
		
		imgBike.setImage(img);
		lbBikeCode.setText("" + bike.getId());
		lbBikeName.setText(bike.getName());
		lbBikeDepositMoney.setText("" +Contants.toString(bike.getDepositMoney()));
		lbBikeStatus.setText(bike.getStatus());
		lbBikePrice.setText("" + Contants.toString(bike.getPrice()));
		lbBikeDesc.setText(bike.getDescription());
	}
	
	/**
	 * Kiểm tra xe đó có dang được thuê hay chưa
	 * @return true hoặc false
	 */
	public boolean checkRentAvailable() {
		if(HomeController.currentUser.getRentingBike().getRentID() != 0) {
			showMessage(Configs.CUSTOMER_IS_RENTING);
			return false;
		}
		if(!"available".equals(bike.getStatus())) {
			showMessage(Configs.BIKE_IS_RENTING);
			return false;
		}
		return true;
	}
	
	/**
	 * Hiển thị thông báo đến người dùng
	 * @param mess: thông báo
	 */
	public void showMessage(String mess) {
		Alert dialog = new Alert(AlertType.ERROR);
		dialog.setTitle(Configs.TITLE_FOR_ALERT);
		dialog.setHeaderText(mess);
		dialog.showAndWait();
	}
	
	/**
	 * Gọi đến màn hình form thanh toán
	 */
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
