package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Rent;
import ultilities.Contants;

class RentModelTest {
	
	private Rent rent, rent2;
	
	@BeforeEach
	void setUp() throws Exception {
		Contants.conn = Contants.getSQLServerConnection();
		rent = new Rent(13);
		rent2 = new Rent(16);
	}

	/**
	 * Test: update rent
	 */
	@Test
	void updateRentTest() {
		boolean isValid = false;
		isValid = rent.updateRent(Contants.getCurrentTime(), 5);
		Assert.assertTrue(isValid);
	}
	
	/**
	 * Test: Tạo một giao dịch
	 */
	@Test
	void createTransactionTest()
	{
		boolean isValid = false;
		isValid = rent.createTransaction("deposit", "Deposit", 100000);
		Assert.assertTrue(isValid);
	}
}
