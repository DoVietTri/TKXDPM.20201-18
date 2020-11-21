package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
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
	
	public static Customer currentUser = new Customer( 20173410,"Đỗ Viết Trí");
	
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
	
	public void getBikeRentingInfo(int bikeID) throws SQLException, ClassNotFoundException {
		//Bike bike1 = Contants.getBikeInfomation(bikeCode);
		bike.setBike(Contants.getBikeInfomation(bikeID));
	}
	
	public void getRentingInfo() {
		try {
			 rent.setRent(Contants.getRentingBike(currentUser.customerID));
			if (rent.rentID != 0) {
				//rent.setRent(rent1);
				getBikeRentingInfo(rent.bikeID);
			}
			System.out.print(rent.rentID);
			System.out.print(rent.getBikeID());
			updateView();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateView() {
		// user
		lbTitle.setText("" + currentUser.getCustomerName());
		
		//bike
		lbBikeCode.setText("" + bike.getId());
		lbBikeBattery.setText("" + bike.getBattery());
		lbBikePrice.setText("" + Contants.toString(bike.getPrice()) );
		
		//rent
		lbTimeStart.setText("" + rent.getTimeStart() );
		
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
