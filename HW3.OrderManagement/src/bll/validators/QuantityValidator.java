package bll.validators;

import model.Ord;

public class QuantityValidator implements Validator<Ord> {
	
	public void validate(Ord t) {

		if (t.getQuantity() < 0) {
			throw new IllegalArgumentException("The Student Age limit is not respected!");
		}
	}
}