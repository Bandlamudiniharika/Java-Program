package Java;
import Basic.Cart;
import Basic.Iteam;
import Basic.IteamType;
import Basic.User;
import Basic.UserType;


public class TestWithoutDiscounts {
	private Cart cart;
	private Iteam iteam;

	// @Before
	public void setUp() 
	{
	User user = new User(UserType.CUSTOMER, "John");
	cart = new Cart(user);
	iteam = new Product("something", 1000, IteamType.OTHER);
	}

	//@Test
	public void test_emptyCartCostsZero() {
	assertEquals(0, cart.total(), 0.01);
	}

	private void assertEquals(double e, double total, double d) {
	// TODO Auto-generated method stub

	}

	//@Test
	public void test_singleBasicIteamCostsItsUnitPrice() {
	cart.add(iteam);
	assertEquals(iteam.getUnitPrice(), cart.total(), 0.01);
	}

	// @Test
	public void test_multipleBasicItemsCostProportionally() {
	int howMany = 3;
	cart.add(iteam, howMany);
	assertEquals(howMany * iteam.getUnitPrice(), cart.total(), 0.01);
	}

	//@Test
	public void test_separatelyAdding() {
	int howMany = 3;
	for (int i = howMany; i > 0; i--) {
	cart.add(iteam);
	}
	assertEquals(howMany * iteam.getUnitPrice(), cart.total(), 0.01);
	}


}
