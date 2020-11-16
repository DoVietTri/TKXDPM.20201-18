package controller;


import java.awt.Label;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import model.Station;
import ultilities.Contants;

public class HomeController implements Initializable {

	@FXML
	Button btnRentBike, btnReturnBike;
	
	@FXML
	Label lbMessage;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		addEvents();
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
