package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Bike;
import model.Station;
import ultilities.Contants;

class StationModelTest {

	private Station station1, station2;
	
	@BeforeEach
	void setUp() throws Exception {
		Contants.conn = Contants.getSQLServerConnection();
		station1 = new Station(1911, 10, 10, "HUST", "so 1 Dai Co Viet");
		station2 = new Station(1900, "FTU", "Chua Lang");
	}

	/**
	 * Test: thông tin xe có trong csdl và có trong bãi
	 */
	@Test
	void getBikeByIDExistTest() {
		Bike bike;
		bike = station1.getBikeByID(191102);
		
		Assert.assertTrue(bike.getId() != 0);
	}
	
	/**
	 * Test: thông tin xe k có trong csdl
	 */
	@Test
	void getBikeByIDNotExistTest()
	{
		Bike bike;
		bike = station1.getBikeByID(191100);
		
		Assert.assertTrue(bike.getId() == 0);
	}
	
	/**
	 * Test: Lấy tất cả xe có trong bãi (bãi đó có trong csdl)
	 */
	@Test
	void getAllBikeExistStationTest()
	{
		List<Bike> bikes = null;
		try {
			bikes = station1.getAllBikes();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Assert.assertTrue(bikes.size() > 0);
	}
	
	/**
	 * Test: Lấy tất cả xe trong bãi (bãi đó không tồn tại)
	 */
	@Test
	void getAllBikeNotExistStationTest()
	{
		List<Bike> bikes = null;
		try {
			bikes = station2.getAllBikes();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertTrue(bikes.size() == 0);
	}
}






