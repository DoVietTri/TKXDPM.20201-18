package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Bike;
import model.Station;
import ultilities.Contants;

public class ViewStationController  implements Initializable{
	@FXML
	Button btnReturnBike, btnViewBike;
	
	@FXML
	TableView<Bike> tbvListBike;
	
	@FXML
	TableColumn<Bike, Integer> codeCol, batteryCol;
	
	@FXML
	TableColumn<Bike, Double> priceCol;
	
	@FXML
	TableColumn<Bike, String> nameCol, typeCol;
	
	@FXML
	Label lbStationID, lbStationName, lbStationAddress, lbStationTotalBike, lbStationAvailable;
	
	public ObservableList<Bike> listBike;
		
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		getStationInfo();
		getAllBikes();
		addEvents();
	}
	
	public void addEvents() {
		btnViewBike.setOnMouseClicked(e -> {
			viewBike();
		});
		btnReturnBike.setOnMouseClicked(e -> {
			
		});
		
		tbvListBike.setRowFactory(tv -> {
			TableRow<Bike> row = new TableRow<>();
			row.setOnMouseClicked(e -> {
				if (e.getClickCount() == 2 && !(row.isEmpty())) {
					
					Contants.bikeSelected.setBike(row.getItem());
					viewBike();
				}
			});

			return row;
		});
	}
		
	public void addControll() {
		
	}
	
	public void getStationInfo() {
		lbStationID.setText("" + Contants.stationSelected.stationID);
		lbStationName.setText(Contants.stationSelected.getName());
		lbStationAddress.setText(Contants.stationSelected.address);
		lbStationTotalBike.setText("" + Contants.stationSelected.totalBike);
		lbStationAvailable.setText("" + Contants.stationSelected.available);
	}
	
	public void getAllBikes() {
		listBike = FXCollections.observableArrayList();
		try {
			listBike.addAll(Contants.getAllBikes(Contants.stationSelected.stationID)) ;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		codeCol.setCellValueFactory(new PropertyValueFactory<Bike, Integer>("code"));
		priceCol.setCellValueFactory(new PropertyValueFactory<Bike, Double>("price"));
		typeCol.setCellValueFactory(new PropertyValueFactory<Bike, String>("type"));
		nameCol.setCellValueFactory(new PropertyValueFactory<Bike, String>("name"));
		batteryCol.setCellValueFactory(new PropertyValueFactory<Bike, Integer>("battery"));
		
		tbvListBike.setItems(listBike);
		tbvListBike.refresh();
	}
	
	
	public void viewBike() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/ViewBike.fxml"));
			
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("Bike Info");
			stage.show();

	} 	 catch (IOException e) {
			e.printStackTrace();
		}

	}
	
}
