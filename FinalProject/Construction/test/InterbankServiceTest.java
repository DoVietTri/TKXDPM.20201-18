package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.UnsupportedEncodingException;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Card;
import ultilities.InterbankService;

class InterbankServiceTest {
	
	private Card card;
	
	@BeforeEach
	void setUp() throws Exception {
		card = new Card(123, "Group 18", "118609_group18_2020", "Group 18 thue xe", "1125", "390", "VTB");
	}

	
	@Test
	void processTransactionTest() {
		String statusCode = null;
		try {
			statusCode = InterbankService.processTransaction(card, "pay", 10000);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Assert.assertNotNull(statusCode);
	}
	
	@Test
	void resetBalanceTest()
	{
		String statusCode = null;
		statusCode = InterbankService.resetBalance(card);
		Assert.assertNotNull(statusCode);
	}
	
	@Test
	void getMD5Test() 
	{
		String plaintext = "adc";
		String expected = "225e8a3fe20e95f6cd9b9e10bfe5eb69";
		String actual = null;
		try {
			actual = InterbankService.getMD5("adc");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertEquals(expected, actual);
	}
}
