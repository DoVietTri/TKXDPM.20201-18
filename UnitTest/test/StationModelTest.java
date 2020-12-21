package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Bike;
import model.Station;

class StationModelTest {

	private Station station1;
	
	@BeforeEach
	void setUp() throws Exception {
		station1 = new Station(1911, 10, 10, "HUST", "so 1 Dai Co Viet");
	}

	@Test
	void test() {
		station1.setStationID(1911);
		Bike bike;
		bike = station1.getBikeByID(191102);
		
		System.out.println(bike.getId());
		Assert.assertTrue(bike.getId() != 0);
	}

}
