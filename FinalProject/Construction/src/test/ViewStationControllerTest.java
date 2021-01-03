package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import controller.ViewStationController;

class ViewStationControllerTest {
	
	ViewStationController viewStationController;
	
	@BeforeEach
	void setUp() throws Exception {
		viewStationController = new ViewStationController();
	}
	
	
	@ParameterizedTest
	@CsvSource({
		"118609_group18_2020, true",
		"@123,false",
		",false",
		"3 1 2, false"
	})
	void validateSearchBikeTest(String bikeID, boolean expected) {
		boolean isValid = viewStationController.validateSearchBike(bikeID);
	}

}
