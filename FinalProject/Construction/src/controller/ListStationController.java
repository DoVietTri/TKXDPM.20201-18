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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import model.Station;
import ultilities.Contants;

public class ListStationController implements Initializable {
	/**
	 * Các nút được định nghĩa id bên view
	 */
	@FXML
	Button btnBack, btnViewStation, btnSearch;

	@FXML
	Pane contentView;
	
	@FXML
	TableView<Station> tbvStation;
	
	@FXML
	TableColumn<Station, String> nameCol, addressCol;
	
	@FXML
	TableColumn<Station, Integer> idCol, availableCol, totalBikeCol;
	
	public ObservableList<Station> listStation;
	
	/**
	 * Khởi chạy
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		getAllStation();
		addEvents();
		addControl();
	}
	
	/**
	 * Bắt sự kiện click vào các nút
	 */
	public void addEvents() {
		btnViewStation.setOnMouseClicked(e -> {
			if(tbvStation.getSelectionModel().getSelectedItem() != null) {
				Contants.stationIDSelected = tbvStation.getSelectionModel().getSelectedItem().getStationID();
				viewStation();
			}
			
		});
		btnBack.setOnMouseClicked(e -> {
			backToPrevious();
		});
		
		tbvStation.setRowFactory(tv -> {
			TableRow<Station> row = new TableRow<>();
			row.setOnMouseClicked(e -> {
				if (e.getClickCount() == 2 && !(row.isEmpty())) {		
					Contants.stationIDSelected = row.getItem().getStationID();
					viewStation();
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
	
	/**
	 * Lấy tất cả bãi xe và hiển thị tất cả bãi xe lên màn hình
	 */
	public void getAllStation() {
		listStation = FXCollections.observableArrayList();
		try {
			listStation.addAll(Contants.getAllStations()) ;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		idCol.setCellValueFactory(new PropertyValueFactory<Station, Integer>("stationID"));
		nameCol.setCellValueFactory(new PropertyValueFactory<Station, String>("name"));
		addressCol.setCellValueFactory(new PropertyValueFactory<Station, String>("address"));
		totalBikeCol.setCellValueFactory(new PropertyValueFactory<Station, Integer>("totalBike"));
		availableCol.setCellValueFactory(new PropertyValueFactory<Station, Integer>("available"));
		
		tbvStation.setItems(listStation);
		tbvStation.refresh();
	}
	
	public void viewStation() {
		try {
			
			//	FXMLLoader fxmlLoader
			Parent root = FXMLLoader.load(getClass().getResource("/view/ViewStation.fxml"));
			contentView.getChildren().add(root);
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Xử lý khi nhấn vào nút back
	 */
	public void backToPrevious() {
		int i = contentView.getChildren().size() - 1;
		if (i > 0) contentView.getChildren().remove(i);
	}

}
