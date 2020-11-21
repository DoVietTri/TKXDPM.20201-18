package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ultilities.Contants;

public class ViewBikeController implements Initializable {

	@FXML
	Label lbBikeCode, lbBikeDesc, lbBikeStatus, lbBikeName, lbBikePrice, lbBikeBattery, lbBikeType;
	
	@FXML
	ImageView imgBike;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		getBikeInfo();
		
	}
	
	public void getBikeInfo() {
		String type = Contants.bikeSelected.getType();
		Image img = new Image("/resources/bike"+ type +".jpg");
		imgBike.setImage(img);
		lbBikeCode.setText("" + Contants.bikeSelected.getId());
		lbBikeBattery.setText("" + Contants.bikeSelected.getBattery());
		lbBikeName.setText(Contants.bikeSelected.getName());
		lbBikeType.setText("" + Contants.bikeSelected.getType());
		lbBikeStatus.setText(Contants.bikeSelected.getStatus());
		lbBikePrice.setText("" +Contants.toString(Contants.bikeSelected.getPrice()));
		lbBikeDesc.setText(Contants.bikeSelected.getDescription());
	}
}
