package bll;

import dao.CustomerDAO;
import model.Customer;

public class CustomerBLL extends AbstractBLL<Customer> {

	public CustomerBLL() {
		super(new CustomerDAO());
	}
}