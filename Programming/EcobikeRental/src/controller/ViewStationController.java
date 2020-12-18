package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Bike;
import model.Station;
import ultilities.Contants;

public class ViewStationController  implements Initializable{
	@FXML
	Button btnReturnBike, btnViewBike, btnSearch;
	
	@FXML
	TableView<Bike> tbvListBike;
	
	@FXML
	TableColumn<Bike, Integer> codeCol,priceCol, batteryCol;
	
	
	@FXML
	TableColumn<Bike, String> nameCol, statusCol;
	
	@FXML
	Label lbStationID, lbStationName, lbStationAddress, lbStationTotalBike, lbStationAvailable;
	
	@FXML
	TextField txtBikeCode;
	
	Station station = new Station();
	
	public ObservableList<Bike> listBike;
		
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		getStationInfo();
		getAllBikes();
		addEvents();
		addControl();
		
	}
	
	public void addEvents() {
		
		btnViewBike.setOnMouseClicked(e -> {
			showBikeInfo();
		});
		
		btnReturnBike.setOnMouseClicked(e -> {
			checkBikeRenting();
		});
		
		
		btnSearch.setOnMouseClicked(e -> {
			searchBike();
		});
		
		tbvListBike.setRowFactory(tv -> {
			
			TableRow<Bike> row = new TableRow<>();
			row.setOnMouseClicked(e -> {
				if (e.getClickCount() == 2 && !(row.isEmpty())) {
					
					Contants.bikeSelected.setBike(row.getItem());
					showBikeInfo();;
					
				}
			});

			return row;
		});
	}
		
	public void addControl() {
		Image img = new Image("/resources/search.png");
		ImageView imgview = new ImageView(img);
		imgview.setFitWidth(20);
		imgview.setFitHeight(20);
		
		btnSearch.setGraphic(imgview);
		
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
			
			listBike.addAll(Contants.getAllBikes(Contants.stationSelected.stationID));
			
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
			
		}
		
		codeCol.setCellValueFactory(new PropertyValueFactory<Bike, Integer>("id"));
		priceCol.setCellValueFactory(new PropertyValueFactory<Bike, Integer>("price"));
		statusCol.setCellValueFactory(new PropertyValueFactory<Bike, String>("status"));
		nameCol.setCellValueFactory(new PropertyValueFactory<Bike, String>("name"));
		batteryCol.setCellValueFactory(new PropertyValueFactory<Bike, Integer>("battery"));
		
		tbvListBike.setItems(listBike);
		tbvListBike.refresh();
	}
	
	
	public void showBikeInfo() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/ViewBike.fxml"));
			
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("Bike Info");
			stage.initModality(Modality.WINDOW_MODAL);
			stage.setResizable(false);
			stage.showAndWait();

	} 	 catch (IOException e) {
			e.printStackTrace();
		}

	}
	public void checkBikeRenting() {
		if(Contants.currentRentID != 0) {
			showReturnBike();
		} else {
			showMessage("Bạn chưa thuê xe !");
		}
		
		
	}
	
	public void showReturnBike() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/ReturnBike.fxml"));
			
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("Return Bike");
			stage.initModality(Modality.WINDOW_MODAL);
			stage.setResizable(false);
			stage.showAndWait();

	} 	 catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void searchBike() {
		if (txtBikeCode.getText().isEmpty()) return ;
		else {
			int id = Integer.parseInt(txtBikeCode.getText());
			int bikeID = Contants.getBikeInfomation(id).id;
			if (bikeID != 0) {
				Contants.bikeSelected.setId(bikeID);;
				showBikeInfo(); 
			}
			else {
				showMessage("Không tìm thấy xe !");
			}
		}
	}
	
	public void showMessage(String mess) {
		Alert dialog = new Alert(AlertType.ERROR);
		dialog.setTitle("Thông báo");
		dialog.setHeaderText(mess);
		 dialog.showAndWait();
	}
	
}
