package bll.validators;

import model.Product;

public class UnitsInStockValidator implements Validator<Product> {
	
	public void validate(Product t) {

		if (t.getUnitsInStock() < 0) {
			throw new IllegalArgumentException("The Student Age limit is not respected!");
		}
	}
}