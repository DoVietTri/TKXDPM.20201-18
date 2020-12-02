package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Card;
import ultilities.Contants;

public class PaymentFormController implements Initializable {

	@FXML 
	Button btnOK, btnClose;
	
	@FXML 
	Label lbMessage;
	
	@FXML 
	GridPane form;
	
	@FXML
	TextField txtName, txtCardNumber, txtCardBank, txtCardValidDate, txtCardCVV, txtContent;
	
	Card card;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		addEvents();
		setupTextField();
		
	}
	
	public void addEvents() {
		btnOK.setOnMouseClicked(e -> {
			submitPaymentForm();
		});
		btnClose.setOnMouseClicked(e -> {
			Stage stage = (Stage) btnClose.getScene().getWindow();
			stage.close();
		});

		
	}
	
	public void submitPaymentForm() {
		if (checkBlankField()) {
			System.out.print("1");
		} else {
			showMessage("Hãy nhập đầy đủ thông tin !");
		}
	}
	
	public boolean checkBlankField() {
		if (txtName.getText().isEmpty()) return false;
		if (txtCardNumber.getText().isEmpty()) return false;
		if (txtCardBank.getText().isEmpty()) return false;
		if (txtCardValidDate.getText().isEmpty()) return false;
		if (txtCardCVV.getText().isEmpty()) return false;
		return true;
	}
	
	public void showMessage(String mess) {
		lbMessage.setText(mess);
	}
	
	public void clearTextField() {
		txtName.setText("");
		txtCardNumber.setText("");
		txtCardBank.setText("");
		txtCardValidDate.setText("");
		txtCardCVV.setText("");
		txtContent.setText(HomeController.currentUser.customerName + " thue xe " + Contants.bikeSelected.getId());
	}
	
	public void setupTextField() {
		try {
			card = new Card();
			card.setCard(Contants.getCustomerCard());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		txtName.setText("" + card.cardHolderName);
		txtCardNumber.setText("" + card.cardNumber);
		txtCardBank.setText("" + card.issuingBank);
		txtCardValidDate.setText("" + card.expirationDate);
		txtCardCVV.setText("");
		txtContent.setText(HomeController.currentUser.customerName + " thue xe " + Contants.bikeSelected.getId());
	}

}
  