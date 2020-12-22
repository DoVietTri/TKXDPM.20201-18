package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Card;
import model.Customer;
import model.Rent;
import ultilities.Contants;

class CustomerModelTest {
	
	private Customer customer;
	
	@BeforeEach
	void setUp() throws Exception {
		Contants.conn = Contants.getSQLServerConnection();
		customer = new Customer(20173410, "Do Viet Tri");
	}
	
	/**
	 * Test: Lấy thông tin xe đang thuê (người đó chưa thuê xe)
	 */
	@Test
	void getRentingBikeExistTest() {
		Rent rent;
		rent = customer.getRentingBike();
		Assert.assertTrue(rent.getRentID() == 0);
	}
	
	/**
	 * Test: Lấy thông tin xe đang thuê (người đó đang thuê)
	 */
	@Test
	void getRentingBikeNotExistTest()
	{
		Rent rent;
		rent = customer.getRentingBike();
		Assert.assertTrue(rent.getRentID() != 0);
	}
	
	/**
	 * Test: Lấy thông tin thẻ của khách hàng (khách hàng có tài khoản đó)
	 */
	@Test
	void getCustomerCardExistTest()
	{
		customer.setCardNumber("118609_group18_2020");
		Card card;
		card = customer.getCustomerCard();
		Assert.assertTrue(card.getCardID() != 0);
	}
	
	/**
	 * Test: Lấy thông tin thẻ của khách hàng (khách hàng không có tài khoản đó)
	 */
	@Test
	void getCustomerCardNotExistTest()
	{
		Card card;
		customer.setCardNumber("Vidu_123");
		card = customer.getCustomerCard();
		Assert.assertTrue(card.getCardID() == 0);
	}
}
