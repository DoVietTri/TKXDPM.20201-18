package JUnit_test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import model.Bike;
import ultilities.Contants;

class BikeTest {
	
	public BikeTest()
	{
		
	}
	
	/**
	 * Test a bike exists in database
	 * Test pass if exists bike has id
	 */
	@Test
	void getBikeByIdExistTest() {
		Bike bike;
		bike = Contants.getBikeInfomation(191101);
		Assert.assertTrue(bike.getId() != 0);
	}
	
	/**
	 * Test a bike not exists in database
	 * Test pass if not exists bike has id
	 */
	@Test
	void getBikeByIdNotExistTest()
	{
		Bike bike;
		bike = Contants.getBikeInfomation(1);
		Assert.assertTrue(bike.getId() == 0);
	}
	
	/**
	 * Test get all bike in station exists in database 
	 * Test pass if exists station has id
	 */
	@Test
	void getListAllBikeInStationExistTest()
	{
		List <Bike> bikes = null;
		try {
			bikes = Contants.getAllBikes(1911);
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
	 * Test get all bike in station not exists in database
	 * Test pass if not exists station has id
	 */
	@Test
	void getListAllBikeInStationNotExistTest()
	{
		List <Bike> bikes = null;
		try {
			bikes = Contants.getAllBikes(191);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Assert.assertTrue(bikes.size() <= 0);
	}
}
