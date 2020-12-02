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

	@FXML
	Button btnRentBike, btnReturnBike;
	
	@FXML
	Label lbMessage,lbTitle, lbBikeCode, lbBikeBattery, lbBikePrice, lbTotalTime, lbTimeStart, lbTotalMoney;
	
	public Rent rent = new Rent();
	public Bike bike = new Bike();
	
	public static Customer currentUser = new Customer(20173410, "9704151300297331", 1, "Tran Van Tri");
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		addEvents();
		getRentingInfo();
	}
	
	public void addEvents() {
		btnRentBike.setOnMouseClicked(e -> {
			viewListStation();
		});
		btnReturnBike.setOnMouseClicked(e -> {
			viewListStation();
		});
	}
		
	
	
	public void addControll() {
		
	}
	
	public void getRentingInfo() {
		try {
			 rent.setRent(Contants.getRentingBike(currentUser.customerID));
			if (rent.rentID != 0) {
				bike.setBike(Contants.getBikeInfomation(rent.bikeID));
				updateView();
			} else {
				clearView();
			}

			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateView() {
		// message
		lbMessage.setText("Bạn đang thuê xe có ID " + bike.getId() );
		
		// user
		lbTitle.setText("" + currentUser.getCustomerName());
		
		//bike
		lbBikeCode.setText("" + bike.getId());
		lbBikeBattery.setText("" + bike.getBattery());
		lbBikePrice.setText("" + Contants.toString(bike.getPrice()) );
		
		//rent
		lbTimeStart.setText("" + rent.getTimeStart());
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		String timeNow = formatter.format(date);
		
		Time time = rent.timeStart;
		Time time2 = Time.valueOf(timeNow);
		long totalTimeRent = (time2.getTime() - time.getTime())/60000;
		lbTotalTime.setText("" + totalTimeRent + " phút");
		lbTotalMoney.setText("" + Contants.toString(Contants.calculateMoney(bike.getPrice() , totalTimeRent)) + " VNĐ");
		
		//button
		btnRentBike.setDisable(true);
		btnReturnBike.setDisable(false);
		
	}
	
	public void calculateMoney() {
		
	}
	
	public void clearView() {
		// message
		lbMessage.setText("Bạn chưa thuê xe !");
		
		// user
		lbTitle.setText("" + currentUser.getCustomerName());
				
		//bike
		lbBikeCode.setText("");
		lbBikeBattery.setText("");
		lbBikePrice.setText("");
				
		//rent
		lbTimeStart.setText("");
		
		//button
		btnRentBike.setDisable(false);
		btnReturnBike.setDisable(true);
				
	}
	
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
