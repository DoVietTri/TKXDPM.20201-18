package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import model.Station;
import ultilities.Contants;

class ContantsUltilitesTest {

	@BeforeEach
	void setUp() throws Exception {
		Contants.conn = Contants.getSQLServerConnection();
	}
	
	/**
	 * Test: Lấy tất cả các bãi xe
	 */
	@Test
	void getAllStationsTest() {
		List<Station> stations = null;
		try {
			stations = Contants.getAllStations();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertTrue(stations.size() != 0);
	}
	
	/**
	 * Test: Tính tổng tiền thuê xe
	 * @param price: tiền thuê ban đầu
	 * @param totalTime: tổng thời gian thuê xe
	 * @param expected: tiền mong đợi
	 */
	@ParameterizedTest
	@CsvSource({
		"50,25,10000",
		"15, 50, 10030"
	})
	void calculateMoneyTest(int price, int totalTime, int expected)
	{
		int isValid = Contants.calculateMoney(price, totalTime);
		Assert.assertEquals(expected, isValid);
	}
	
	
}
