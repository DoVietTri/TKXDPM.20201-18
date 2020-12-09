package JUnit_test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import model.Rent;
import ultilities.Contants;

class RentBikeTest {

	@Test
	void getCustomerRentingBikeExistTest() 
	{
		Rent rent = Contants.getRentingBike(20173410);
		
		Assert.assertTrue(rent.getStatus().equals("renting"));
	}
	
	@Test
	void getCustomerRentingBikeNotExistTest()
	{
		Rent rent = Contants.getRentingBike(20173412);
		Assert.assertTrue(rent.getRentID() == 0);
	}
	
	
}
