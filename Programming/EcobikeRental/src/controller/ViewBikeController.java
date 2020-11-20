package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import ultilities.Contants;

public class ViewBikeController implements Initializable {

	@FXML
	Label lbBikeCode, lbBikeDesc, lbBikeStatus, lbBikeName, lbBikePrice;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		getBikeInfo();
		
	}
	
	public void getBikeInfo() {
		lbBikeCode.setText("" + Contants.bikeSelected.getCode());
		lbBikeName.setText(Contants.bikeSelected.getName());
	//	lbBikeCode.setText("" + Contants.bikeSelected.getId());
		lbBikeStatus.setText(Contants.bikeSelected.getStatus());
		lbBikePrice.setText("" + Contants.bikeSelected.getPrice());
		lbBikeDesc.setText(Contants.bikeSelected.getDescription());
	}
}
