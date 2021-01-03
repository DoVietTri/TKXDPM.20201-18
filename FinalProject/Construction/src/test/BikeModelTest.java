package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import controller.MainController;
import model.Bike;
import ultilities.Contants;

class BikeModelTest {
	
	Bike bike1;
	
	@BeforeEach
	void setUp() throws Exception {
		Contants.conn = Contants.getSQLServerConnection();
		bike1 = new Bike(191101, 90, 1911, 10, "Xe dap don thuong", "renting", "1", "Xe");
	}
	
	/**
	 * Test: Lấy thông tin tiền thông tin tiền cọc xe
	 */
	@Test
	void getDepositMoneyTest() {
		int price = bike1.getDepositMoney();
		
		Assert.assertTrue(price == 400000);
	}
	
	/**
	 * Test: Cập nhật xe
	 */
	@ParameterizedTest
	@CsvSource({
		"available, 1914, true"
	})
	void updateBikeTest(String status, int stationID, boolean expected)
	{
		boolean isValid = false;
		isValid = bike1.updateBike(status, stationID);
		Assert.assertEquals(expected, isValid);
	}
	
	/**
	 * Tạo một bản ghi thuê xe trong cơ sở dữ liệu
	 */
	@Test
	void createRentTest()
	{
		boolean isValid = false;
		isValid = bike1.createRent(Contants.getCurrentTime(), 20173410);
		Assert.assertTrue(isValid);
	}
}
