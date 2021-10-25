package Basic;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.LinkedHashMap;
import java.util.Map;

import Discount.DiscountPolicy;

public class Cart {
	private Map<Iteam, Integer> quantities;
	private DiscountPolicy discountPolicy;
	private User user;

	public Cart(User user) {
	quantities = new LinkedHashMap<Iteam, Integer>();
	this.user = user;
	}

	public Cart(User user, DiscountPolicy discountPolicy) {
	quantities = new LinkedHashMap<Iteam, Integer>();
	this.user = user;
	this.discountPolicy = discountPolicy;
	}

	public double total() {
	double result = 0;
	for (Iteam each : quantities.keySet()) {
	result += each.priceForQuantity(quantities.get(each));
	}

	if (discountPolicy != null) {
	result = discountPolicy.applyDiscount(result);
	}

	return result;
	}

	public void add(Iteam iteamToBuy) {
	add(iteamToBuy, 1);
	}


	// To add multiple quantities of item
	public void add(Iteam iteamToBuy, int howMany) {
	Iteam iteam;

	// Apply 30% discount in case of employee of store
	if (user.getType() == UserType.EMPLOYEE) {
	iteam = new PromotionPricing();
	}
	// Apply 10% discount in case of affiliate
	else if (user.getType() == UserType.AFFILIATE) {
	iteam = new PromotionPricing();
	}

	// If a user has been a customer for 2 or more years apply 5% discount
	else if (user.getType() == UserType.CUSTOMER &&
	ChronoUnit.YEARS.between(user.getJoiningDate(), LocalDateTime.now()) >= 2) {
	iteam = new PromotionPricing();
	}

	else {
	iteam = iteamToBuy;
	}

	int previousQuantity = quantities.containsKey(iteam)
	? quantities.get(iteam)
	: 0;
	quantities.put(iteam, previousQuantity + howMany);
	}

}
