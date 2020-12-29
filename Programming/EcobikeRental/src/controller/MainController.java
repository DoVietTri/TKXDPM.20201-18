package controller;

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
	Button btnHome, btnListStation, btnExit;
	
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
	
	/**
	 * Nhiệm vụ: Bắt các sự hiện bấm nút
	 */
	public void addEvents() {
		btnHome.setOnMouseClicked(e -> {
			
			btnHome.setStyle("-fx-background-color:  #ffffff; -fx-background-radius:  10;");
			
			btnListStation.setStyle("-fx-background-color:   #4b9ab8; -fx-border-color:  #ffffff; -fx-border-radius:  10; -fx-border-width:  2;");

			
			showHomeScreen();
		});
		
		btnListStation.setOnMouseClicked(e -> {
			btnListStation.setStyle("-fx-background-color:  #ffffff; -fx-background-radius:  10;");
			
			btnHome.setStyle("-fx-background-color:   #4b9ab8; -fx-border-color:  #ffffff; -fx-border-radius:  10; -fx-border-width:  2;");
			showListStationScreen();
			
		});
		
	}
	
	public void addControll() {
		
	}
	
	/**
	 * Nhiệm vụ: kết nối đến cơ sở dữ liệu
	 */
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
	
	/**
	 * Nhiệm vụ: Hiển thị màn hình trang chủ
	 */
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
	
	/**
	 * Hiển thị màn hình danh sách các bãi xe
	 */
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
