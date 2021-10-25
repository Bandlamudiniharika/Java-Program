package Java;

import Basic.Iteam;
import Basic.IteamType;

public class Product implements Iteam {
	private final String name;
	private final double unitPrice;
	private final IteamType type;

	public Product(String name, double priceInDollars, IteamType type) {
	this.name = name;
	this.unitPrice = priceInDollars;
	this.type = type;
	}

	public double getUnitPrice() {
	return unitPrice;
	}

	public String getName() {
	return name;
	}

	public double priceForQuantity(int quantity) {
	return unitPrice * quantity;
	}

	public IteamType getType() {
	return type;
	}

}
