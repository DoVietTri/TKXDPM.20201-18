package controller;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.layout.Pane;
import model.Station;
import ultilities.Contants;

public class ListStationController implements Initializable {
	@FXML
	Button btnBack, btnViewStation;

	@FXML
	Pane contentView;
	
	@FXML
	TableView<Station> tbvStation;
	
	@FXML
	TableColumn<Station, String> nameCol, positionCol;
	
	@FXML
	TableColumn<Station, Integer> numberCol, distanceCol;
	
	public ObservableList<Station> listStation;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		getAllStation();
		addEvents();
	}
	
	public void addEvents() {
		btnViewStation.setOnMouseClicked(e -> {
			viewStation();
		});
		btnBack.setOnMouseClicked(e -> {
			backToPrevious();
		});
	}
		
	
	
	public void addControll() {
		
	}
	
	public void getAllStation() {
		listStation = FXCollections.observableArrayList();
		listStation.addAll(Contants.sharedInstance.getAllStations()) ;
		
		nameCol.setCellValueFactory(new PropertyValueFactory<Station, String>("name"));
		positionCol.setCellValueFactory(new PropertyValueFactory<Station, String>("position"));
	//	distanceCol.setCellValueFactory(new PropertyValueFactory<Station, Integer>("Khoảng cách"));
	
		tbvStation.setItems(listStation);
		tbvStation.refresh();
	}
	
	
	public void viewStation() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/ViewStation.fxml"));
	//	contentView.getChildren().removeAll();
			contentView.getChildren().add(root);
	} 	 catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void backToPrevious() {
		int i = contentView.getChildren().size() - 1;
		if (i >= 0) contentView.getChildren().remove(i);
	}

}
