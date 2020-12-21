package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.MainController;
import model.Bike;
import ultilities.Contants;

class BikeModelTest {
	
	Bike bike1;
	
	@BeforeEach
	void setUp() throws Exception {
		Contants.conn = Contants.getSQLServerConnection();
		bike1 = new Bike(191101, 90, 1911, 10, "Xe dap don thuong", "renting", "1", "Xe");
	}
	
	@Test
	void getDepositMoneyTest() {
		int price = bike1.getDepositMoney();
		
		Assert.assertTrue(price == 400000);
	}
	
	@Test
	void updateBikeTest()
	{
		boolean isValid = false;
		isValid = bike1.updateBike("available", 1912);
		System.out.println(isValid);
		Assert.assertFalse(isValid); 
	}
	
	@Test
	void createRentTest()
	{
		
	}
}
