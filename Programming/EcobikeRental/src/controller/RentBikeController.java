package controller;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ResourceBundle;

import Subsystem.InterbankService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Bike;
import model.Card;
import ultilities.Contants;

public class RentBikeController implements Initializable {

	/**
	 * Các nút được định nghĩa id bên view
	 */
	@FXML 
	Button btnSubmit;
	
	/**
	 * Các nhãn dán được định nghĩa bên view
	 */
	@FXML
	Label lbCardNumber, lbCardHolderName, lbMoney;
	
	Card card = new Card();
	Bike bike = new Bike();
	
	/**
	 * Khởi chạy
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		card.setCard(Contants.cardSelected);
		bike.setBikeFromID(Contants.bikeIDSelected);
		showTransactionInfo();
		addEvents();
	}
	
	/**
	 * Bắt sự kiện click vào các nút
	 */
	public void addEvents() {
		btnSubmit.setOnMouseClicked(e -> {
			rentBike();
		});
	}
	
	/**
	 * Hiển thị thông tin giao dịch
	 */
	public void showTransactionInfo() {
		lbCardHolderName.setText(card.cardHolderName);
		lbCardNumber.setText(card.cardNumber);
	}
	
	/**
	 * Nhiệm vụ: xử lý tác vụ, goi api thuê xe
	 */
	public void rentBike() {
		int depositMoney = 10000;
		//= Contants.getDepositMoney(Bike.type);
		try {
			String code = InterbankService.processTransaction(card, "pay", depositMoney);
			System.out.print("Code: " + code);
			if("00".equals(code)) {
				
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
