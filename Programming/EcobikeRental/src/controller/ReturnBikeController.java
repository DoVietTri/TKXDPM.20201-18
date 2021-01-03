package controller;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import Subsystem.InterbankService;
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
import ultilities.Configs;
import ultilities.Contants;

public class ReturnBikeController implements Initializable {
	/**
	 * Các nút được định nghĩa id bên view
	 */
	@FXML 
	Button btnSubmit, btnClose, btnRefresh;
	
	/**
	 * Các nhãn dán được định nghĩa bên view
	 */
	@FXML
	Label lbCardNumber, lbCardHolderName, lbDepositMoney, lbTotalMoneyRent, lbTotalMoney, lbBikeCode, lbBikePrice, lbTimeStart, lbTotalTime, lbMessage;
	
	Card card = new Card();
	Rent rent = new Rent();
	Bike bike = new Bike();
	
	int totalTimeRent = 0;
	int totalMoneyRent = 0;
	int depositMoney = 0;
	Time current;
	
	/**
	 * Khởi chạy
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		getRentingInfo();
		addEvents();
	}
	
	/**
	 * Bắt sự kiện click vào các nút
	 */
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
	
	/**
	 * Lấy thông tin thuê xe nếu người đó đang thuê xe
	 */
	public void getRentingInfo() {
		rent.setRentFromID(Contants.currentRentID);
		bike.setBikeFromID(rent.getBikeID());
		card.setCard(HomeController.currentUser.getCustomerCard());
		// update view
		updateView();
	}
	
	/**
	 * Cập nhật lại view
	 */
	public void updateView() {
		current = Contants.getCurrentTime();
		totalTimeRent = (int) ((current.getTime() - rent.getTimeStart().getTime())/60000);
		totalMoneyRent = Contants.calculateMoney(bike.getPrice(), totalTimeRent);
		
		lbCardHolderName.setText(card.getCardHolderName());
		lbCardNumber.setText(card.getCardNumber());
		lbBikeCode.setText("" + bike.getId());
		lbBikePrice.setText("" + Contants.toString(bike.getPrice()));
		lbTimeStart.setText("" + rent.getTimeStart());
		totalTimeRent = Contants.calculateTotalTime(rent.getTimeStart());
		lbTotalTime.setText("" + totalTimeRent);
		depositMoney = bike.getDepositMoney();
		lbDepositMoney.setText("" + depositMoney);
		totalMoneyRent = Contants.calculateMoney(bike.getPrice(), totalTimeRent);
		lbTotalMoneyRent.setText("" + totalMoneyRent);
		
		if(depositMoney > totalMoneyRent) {
			lbMessage.setText("Số tiền bạn được hoàn lại: ");
			lbTotalMoney.setText("" + (depositMoney-totalMoneyRent));
			
		} else {
			lbMessage.setText("Số tiền bạn cần thanh toán: ");
			lbTotalMoney.setText("" + (totalMoneyRent-depositMoney));
		}
	}
	
	/**
	 * Xác nhận trả xe
	 */
	public void submitReturnBike() {
		current = Contants.getCurrentTime();
		totalTimeRent = Contants.calculateTotalTime(rent.getTimeStart());
		totalMoneyRent = Contants.calculateMoney(bike.getPrice(), totalTimeRent);
		bike.updateBike("available", Contants.stationIDSelected);

		updateView();
		
		if(depositMoney > totalMoneyRent) {
			returnBike("refund",depositMoney - totalMoneyRent);
		} else {
			returnBike("pay", totalMoneyRent - depositMoney);
		}
	}
	
	/**
	 * Nhiệm vụ: xử lý trả xe, gọi đến api trả xe
	 * @param code: lệnh giao dịch: "pay" hay "refund"
	 * @param totalMoney: tổng số tiền giao dịch
	 */
	public void returnBike( String code, int totalMoney) {
		String res;
		try {
			res = InterbankService.processTransaction(card, code, totalMoney);
		//	System.out.print("Code: " + res);
			if("00".equals(res)) {
				bike.updateBike("available", Contants.stationIDSelected);
				rent.updateRent(current, totalTimeRent);
				Rent rent = new Rent(Contants.currentRentID);
				rent.createTransaction("return", "Return bike" , totalMoney);
				updateRentingBike();
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
	 * Nhiệm vụ: tạo mới 1 giao dịch
	 * @param totalTimeRent: tổng thời gian thuê
	 * @param totalMoney: tổng số tiền thuê
	 * @param rentID: mã thuê
	 */
	public void createTransaction(int totalTimeRent, int totalMoney, int rentID) {
		rent.createTransaction("pay", "Pay for rent bike " + bike.getId(), totalMoney);
	}
	
	public void updateRentingBike() {
		Contants.currentRentID = 0;
	}
	
	/**
	 * Hiển thị thông báo
	 * @param mess: thông báo
	 */
	public void showMessage(String mess) {
		Alert dialog = new Alert(AlertType.ERROR);
		dialog.setTitle("Thông báo");
		dialog.setHeaderText(mess);
		dialog.showAndWait();
	}

}