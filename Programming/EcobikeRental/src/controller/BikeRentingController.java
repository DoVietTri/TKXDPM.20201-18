package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import model.Customer;

public class BikeRentingController implements Initializable {

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		showBikeRenting();
		
	}

	public void showBikeRenting() {
		if (Customer.user.bikeIdRented == 0) {
			
		} else {
			
		}
	}
}
