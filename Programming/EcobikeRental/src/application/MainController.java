package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import ultilities.Contants;

public class MainController implements Initializable {

	@FXML 
	Button btnHome, btnListStation;
	
	@FXML 
	ImageView imgAva;
	
	@FXML
	Pane contentView;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		addEvents();
		connectDB();
		showHomeScreen();
		
		
	}
	
	public void addEvents() {
		btnHome.setOnMouseClicked(e -> {
			showHomeScreen();
		});
		
		btnListStation.setOnMouseClicked(e -> {
			showListStationScreen();
		});
		
	}
	
	public void addControll() {
		
	}
	
	public void connectDB() {
		try {
			Contants.conn = Contants.getSQLServerConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void showHomeScreen() {
		
		try {
			Parent root;
			root = FXMLLoader.load(getClass().getResource("/view/HomeScreen.fxml"));
			contentView.getChildren().removeAll();
			contentView.getChildren().add(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void showListStationScreen() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/ListStation.fxml"));
			contentView.getChildren().removeAll();
			contentView.getChildren().add(root);
		} 	 catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void showRentingcreen() {
		
	}
	

}
