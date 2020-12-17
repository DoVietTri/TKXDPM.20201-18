package JUnit_test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.UnsupportedEncodingException;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import model.Card;
import ultilities.InterbankService;

class InterbankServiceTest {
	
	Card card;
	/**
	 * Construct class
	 */
	public InterbankServiceTest()
	{
		card = new Card(123, "Group 18", "118609_group18_2020", "Group 18 thue xe", "1125", "390", "VTB");
	}
	
	/**
	 * Test process transaction
	 */
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
	
	/**
	 * Test process transaction card not exists
	 */
	@Test
	void processTransactionCardNotExistsTest()
	{
		Card card1 = new Card(123, "Group 13", "118609_group1821_2020", "Group 18 thue xe", "1125", "110", "VTB");
		String statusCode = null;
		try {
			statusCode = InterbankService.processTransaction(card1, "pay", 10000);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertTrue(statusCode.equals("00"));
	}
	
	/**
	 * Test reset balance
	 */
	@Test
	void resetBalanceTest()
	{
		String statusCode = null;
		statusCode = InterbankService.resetBalance(card);
		Assert.assertNotNull(statusCode);
	}
	
}
