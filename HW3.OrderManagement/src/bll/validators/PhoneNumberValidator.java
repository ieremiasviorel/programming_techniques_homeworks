package bll.validators;

import model.Customer;

public class PhoneNumberValidator implements Validator<Customer> {
	
	public void validate(Customer c) {
	
		String regex = "^[0-9]*$";
		if(!c.getPhone().matches(regex)) {
			throw new IllegalArgumentException("The phone number contains non-digit characters!");
		}
		else {
			if(c.getPhone().length() != 10) {
				throw new IllegalArgumentException("Incorrect number of digits in phone number!");
			}
		}
	}
}