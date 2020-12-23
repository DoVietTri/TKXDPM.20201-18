package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import controller.PaymentFormController;
import junit.framework.Assert;

class PaymentFormControllerTest {
	
	PaymentFormController paymentFormController;
	
	@BeforeEach
	void setUp() throws Exception {
		paymentFormController = new PaymentFormController();
	}
	
	/**
	 * Test: cardNumber Nhập vào 
	 * @param cardNumber: mã thẻ
	 * @param expected
	 */
	@ParameterizedTest
	@CsvSource({
		"118609_group18_2020, true",
		"@123,false",
		",false"
	})
	void validateCardNumberTest(String cardNumber, boolean expected) {
		boolean isValid = paymentFormController.validateCardNumber(cardNumber);
		
		Assert.assertEquals(expected, isValid);
	}
	
	/**
	 * Test: cardHolderName Nhập vào
	 * @param cardHolderName: Tên chủ thẻ
	 * @param expected
	 */
	@ParameterizedTest
	@CsvSource({
		"Group 18, true",
		",false",
		"@123,false"
	})
	void validateCardHolderNameTest(String cardHolderName, boolean expected)
	{
		boolean isValid = paymentFormController.validateCardHolderName(cardHolderName);
		
		Assert.assertEquals(expected, isValid);
	}
	
	/**
	 * Test: mã CVV
	 * @param cvv: mã CVV
	 * @param expected
	 */
	@ParameterizedTest
	@CsvSource({
		"390, true",
		"3 9, false",
		"Group 2, false",
		",false",
		"@123,false"
	})
	void validateCardCVV(String cvv, boolean expected)
	{
		boolean isValid = paymentFormController.validateCardCVV(cvv);
		
		Assert.assertEquals(expected, isValid);
	}
	
	/**
	 * Test: ngày hết hạn
	 * @param date: ngày hết hạn
	 * @param expected
	 */
	@ParameterizedTest
	@CsvSource({
		"390, true",
		"Group 2, false",
		",false",
		"@123,false"
	})
	void validateCardExpirationTest(String date, boolean expected)
	{
		boolean isValid = paymentFormController.validateCardExpiration(date);
		
		Assert.assertEquals(expected, isValid);
	}
}
