package controller;

import java.io.IOException;
import java.net.URL;


import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import model.Bike;
import model.Card;
import model.Customer;
import model.Rent;
import model.Station;
import ultilities.Contants;

public class HomeController implements Initializable {
	
	/**
	 * Các nút được định nghĩa id bên view
	 */
	@FXML
	Button btnRentBike, btnReturnBike, btnRefresh;
	
	/**
	 * Các nhãn dán được định nghĩa bên view
	 */
	@FXML
	Label lbMessage,lbTitle, lbBikeCode, lbDepositMoney, lbBikePrice, lbTotalTime, lbTimeStart, lbTotalMoney;
	
	public Rent rent = new Rent();
	public Bike bike = new Bike();
	
	public static Customer currentUser = new Customer(20173410, "118609_group18_2020", 1, "Group 18");
	
	/**
	 * Khởi chạy
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		addEvents();
		getRentingInfo();
	}
	
	/**
	 * Bắt các sự kiện bấm nút
	 */
	public void addEvents() {
		btnRentBike.setOnMouseClicked(e -> {
			viewListStation();
		});
		btnReturnBike.setOnMouseClicked(e -> {
			viewListStation();
		});
		btnRefresh.setOnMouseClicked(e -> {
			getRentingInfo();
		});
	}
	
	/**
	 * Lấy thông tin đang thuê xe, nếu khách hàng đang thuê xe
	 */
	public void getRentingInfo() {
		rent.setRent(currentUser.getRentingBike());
		Contants.currentRentID = rent.rentID;
		if (rent.rentID != 0) {
			bike.setBikeFromID(rent.bikeID);
			updateView();
		} else {
			clearView();
		}
	}
	
	/**
	 * Cập nhật lại màn hình
	 */
	public void updateView() {
		// message
		lbMessage.setText("Bạn đang thuê xe có code " + bike.getId() );
		
		// user
		lbTitle.setText("" + currentUser.getCustomerName());
		
		//bike
		lbBikeCode.setText("" + bike.getId());
		lbDepositMoney.setText("" +Contants.toString(bike.getDepositMoney()));
		lbBikePrice.setText("" + Contants.toString(bike.getPrice()) );
		
		//rent
		lbTimeStart.setText("" + rent.getTimeStart());
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		String timeNow = formatter.format(date);
		
		Time time = rent.timeStart;
		Time time2 = Time.valueOf(timeNow);
		int totalTimeRent = (int) ((time2.getTime() - time.getTime())/60000);
		lbTotalTime.setText("" + totalTimeRent + " phút");
		lbTotalMoney.setText("" + Contants.toString(Contants.calculateMoney(bike.getPrice() , totalTimeRent)) + " VNĐ");
		
		//button
		btnRentBike.setDisable(true);
		btnReturnBike.setDisable(false);
		
	}
	
	/**
	 * Làm trống màn hình
	 */
	public void clearView() {
		// message
		lbMessage.setText("Bạn chưa thuê xe !");
		
		// user
		lbTitle.setText("" + currentUser.getCustomerName());
				
		//bike
		lbBikeCode.setText("");
		lbDepositMoney.setText("");
		lbBikePrice.setText("");
		
		//money
		lbTotalTime.setText("0");
		lbTotalMoney.setText("0");
		
		//rent
		lbTimeStart.setText("");
		
		//button
		btnRentBike.setDisable(false);
		btnReturnBike.setDisable(true);
				
	}
	
	/**
	 * Gọi đến màn hình xem bãi xe
	 */
	public void viewListStation() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/ViewStation.fxml"));
	//	contentView.getChildren().removeAll();
	} 	 catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


}
