package JUnit_test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import model.Rent;
import ultilities.Contants;

class RentBikeTest {

	/**
	 * Test exist customer is renting 
	 * Test pass if customer is renting is true
	 */
	@Test
	void getCustomerRentingBikeExistTest() 
	{
		Rent rent = Contants.getRentingBike(20173410);
		
		Assert.assertTrue(rent.getStatus().equals("renting"));
	}
	
	/**
	 * Test customer has id and this customer is not renting
	 * Test pass if this customer is not renting
	 */
	@Test
	void getCustomerRentingBikeNotExistTest()
	{
		Rent rent = Contants.getRentingBike(20173412);
		Assert.assertTrue(rent.getRentID() == 0);
	}
	
	/**
	 * 
	 */
	@Test
	void rentBikeTest()
	{
		boolean status = false;
		Time time = Time.valueOf("16:30:00");
		status = Contants.rentBike(time, 191101, 20173410);
		System.out.println(status);
		Assert.assertTrue(status != false);
	}
	
}
