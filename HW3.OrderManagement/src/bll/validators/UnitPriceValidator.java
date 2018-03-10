package bll.validators;

import model.Product;

public class UnitPriceValidator implements Validator<Product> {
	
	public void validate(Product t) {

		if (t.getUnitPrice() < 0) {
			throw new IllegalArgumentException("The Student Age limit is not respected!");
		}
	}
}