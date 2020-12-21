package controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
	
	/**
	 * Các nút được định nghĩa id bên view
	 */
	@FXML 
	Button btnOK, btnClose;
	
	/**
	 * Các nhãn dán được định nghĩa bên view
	 */
	@FXML 
	Label lbMessage, lbMoney;
	
	@FXML 
	GridPane form;
	
	/**
	 * Các text field bên view
	 */
	@FXML
	TextField txtCardHolderName, txtCardNumber, txtCardBank, txtCardExpirationDate, txtCardCVV, txtContent;
	
	Card card;
	Bike bike = new Bike();
	Rent rent = new Rent();
	
	Time current;
	/**
	 * Khởi chạy
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		bike.setBikeFromID(Contants.bikeIDSelected);
		addEvents();
		setupTextField();
		
	}
	
	/**
	 * Bắt sự kiện click vào các nút
	 */
	public void addEvents() {
		btnOK.setOnMouseClicked(e -> {
			submitPaymentForm();
		});
		btnClose.setOnMouseClicked(e -> {
			Stage stage = (Stage) btnClose.getScene().getWindow();
			stage.close();
		});

		
	}
	/**
	 * Nhiệm vụ: xử lý khi submit form
	 */
	public void submitPaymentForm() {
		if (checkBlankField()) {
			card.cardHolderName = txtCardHolderName.getText();
			card.cardNumber = txtCardNumber.getText();
			card.expirationDate = txtCardExpirationDate.getText();
			card.securityCode = txtCardCVV.getText();
			card.issuingBank = txtCardBank.getText();
			current = Contants.getCurrentTime();
			rentBike(bike.getDepositMoney());
	
		} else {
			showMessage("Hãy nhập đầy đủ thông tin !");
		}
	}
	
	/**
	 * Nhiệm vụ: xử lý khi thuê xe
	 * @param depositMoney: tiền đặt cọc
	 */
	public void rentBike(int depositMoney) {
		String res;
		try {
			res = InterbankService.processTransaction(card, "pay", depositMoney);
		//	System.out.print("Code: " + res);
			if("00".equals(res)) {
				bike.updateBike("renting", bike.stationID);
				bike.createRent(current, Contants.currentUserID);
				Contants.currentRentID = HomeController.currentUser.getRentingBike().rentID;
				Rent rent = new Rent(Contants.currentRentID);
				rent.createTransaction("deposit", "Deposit rent bike" , bike.getDepositMoney());
			
				showMessage(Configs.MESSAGE_SUCCESS);
				Stage stage = (Stage) btnClose.getScene().getWindow();
				stage.close();
			} else {
				showMessage(Contants.response(res));
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Nhiệm vụ: Kiểm tra xem tất cả các trường trong form đã được nhập chưa
	 * @return true hoặc false
	 */
	public boolean checkBlankField() {
		if (txtCardHolderName.getText().isEmpty()) return false;
		if (txtCardNumber.getText().isEmpty()) return false;
		if (txtCardBank.getText().isEmpty()) return false;
		if (txtCardExpirationDate.getText().isEmpty()) return false;
		if (txtCardCVV.getText().isEmpty()) return false;
		return true;
	}
	
	/**
	 * Hiển thị thông báo
	 * @param mess: thông báo
	 */
	public void showMessage(String mess) {
		Alert dialog = new Alert(AlertType.ERROR);
		dialog.setTitle(Configs.TITLE_FOR_ALERT);
		dialog.setHeaderText(mess);
		 dialog.showAndWait();
		
	}
	
	/**
	 * Làm rỗng các trường trong form
	 */
	public void clearTextField() {
		txtCardHolderName.setText("");
		txtCardNumber.setText("");
		txtCardBank.setText("");
		txtCardExpirationDate.setText("");
		txtCardCVV.setText("");
		txtContent.setText(HomeController.currentUser.customerName + " thue xe " + bike.getId());
	}
	
	/**
	 * Định nghĩa thông tin tài khoản thẻ nhập vào
	 */
	public void setupTextField() {
		card = HomeController.currentUser.getCustomerCard();
				
		lbMoney.setText("" + bike.getDepositMoney());
		txtCardHolderName.setText("" + card.cardHolderName);
		txtCardNumber.setText("" + card.cardNumber);
		txtCardBank.setText("" + card.issuingBank);
		txtCardExpirationDate.setText("" + card.expirationDate);
		txtCardCVV.setText("");
		txtContent.setText(HomeController.currentUser.customerName + " thue xe " + bike.getId());
	}

}
  