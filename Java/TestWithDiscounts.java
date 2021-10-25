package Java;

import java.time.LocalDateTime;

import Basic.Cart;
import Basic.Iteam;
import Basic.IteamType;
import Basic.User;
import Basic.UserType;
import Discount.BasicDiscount;
import Discount.DiscountPolicy;

public class TestWithDiscounts {
	private Iteam groceryIteam;
	private Iteam otherIteam;
	private User employee;
	private User affiliate;
	private User simpleUser;
	private User simpleUserWith2Years;
	private DiscountPolicy discountPolicy;

	// @Before
	public void setUp() {
	employee = new User(UserType.EMPLOYEE, "John");
	affiliate = new User(UserType.AFFILIATE, "Michael");
	simpleUser = new User(UserType.CUSTOMER, "Bob");
	simpleUserWith2Years = new User(UserType.CUSTOMER, "Alex", LocalDateTime.of(2014, 7, 19, 6, 40, 45));
	groceryIteam = new Product("Rice", 20, IteamType.GROCERY);
	otherIteam = new Product("TV", 222, IteamType.OTHER);
	discountPolicy = new BasicDiscount();
	}

	//@Test
	public void test_employeeWithGrocery() {
	Cart cart = new Cart(employee, discountPolicy);
	cart.add(groceryIteam, 4);
	// No discount because of grocery item
	assertEquals(80, cart.total(), 0.01);

	}

	private void assertEquals(double e, double total, double d) {
	// TODO Auto-generated method stub

	}

	//@Test
	public void test_employeeWithOtherIteam() {
	Cart cart = new Cart(employee, discountPolicy);
	cart.add(otherIteam, 4);
	/*
	* 30% discount then 5 dollars off of each 100 dollars of total price because of other item
	* Total 222 * 4 = 888
	* After 30% discount = 621.6
	* After 30 dollars off due to price over $600 = 591.6
	*/
	assertEquals(591.6, cart.total(), 0.01);

	}

	// @Test
	public void test_affiliateWithGrocery() {
	Cart cart = new Cart(affiliate, discountPolicy);
	cart.add(groceryIteam, 4);
	// No discount because of grocery item
	assertEquals(80, cart.total(), 0.01);

	}

	// @Test
	public void test_affiliateWithOtherIteam() {
	Cart cart = new Cart(affiliate, discountPolicy);
	cart.add(otherIteam, 4);
	/*
	* 10% discount then 5 dollars off of each 100 dollars of total price because of other item
	* Total 222 * 4 = 888
	* After 10% discount = 799.2
	* After 35 dollars off due to price over $700 = 591.6
	*/
	assertEquals(764.2, cart.total(), 0.01);

	}

	// @Test
	public void test_simpleUserWithGrocery() {
	Cart cart = new Cart(simpleUser, discountPolicy);
	cart.add(groceryIteam, 4);
	// No discount because of grocery item
	assertEquals(80, cart.total(), 0.01);

	}

	//@Test
	public void test_simpleUserWithOtherIteam() {
	Cart cart = new Cart(simpleUser, discountPolicy);
	cart.add(otherIteam, 4);
	/*
	* Total 222 * 4 = 888
	* No percentage discount because user is a customer for less than 2 years
	* After 40 dollars off due to price over $800 = 848
	*/
	assertEquals(848, cart.total(), 0.01);
	}

	// @Test
	public void test_simpleUserWith2YearsWithGrocery() {
	Cart cart = new Cart(simpleUserWith2Years, discountPolicy);
	cart.add(groceryIteam, 4);
	// No discount because of grocery item
	assertEquals(80, cart.total(), 0.01);

	}

	//@Test
	public void test_simpleUserWith2YearsWithOtherIteam() {
	Cart cart = new Cart(simpleUserWith2Years, discountPolicy);
	cart.add(otherIteam, 4);
	/*
	* 5% discount then 5 dollars off of each 100 dollars of total price because of other item
	* Total 222 * 4 = 888
	* After 5% discount = 843.6
	* After 40 dollars off due to price over $800 =803.6
	*/
	assertEquals(803.6, cart.total(), 0.01);

	}

	//@Test
	public void test_employeeWithGroceryAndOtherItem() {
	Cart cart = new Cart(employee, discountPolicy);
	cart.add(groceryIteam, 4);
	cart.add(otherIteam, 4);
	/*
	* Total (20 * 4) + (222 * 4) = 968
	* No discount on grocery items = 968 still
	* After 30% discount on 4 other items totaling 888 = 701.6
	* After 35 dollars off due to price over $700 =666.6
	*/
	assertEquals(666.6, cart.total(), 0.01);

	}


}
