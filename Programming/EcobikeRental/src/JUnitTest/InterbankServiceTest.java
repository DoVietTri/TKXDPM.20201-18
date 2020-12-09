package JUnit_test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.UnsupportedEncodingException;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import model.Card;
import ultilities.InterbankService;

class InterbankServiceTest {
	
	Card card;
	public InterbankServiceTest()
	{
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
	
}
