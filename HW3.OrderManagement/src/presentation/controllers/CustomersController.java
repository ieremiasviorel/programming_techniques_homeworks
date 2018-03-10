package presentation.controllers;

import bll.CustomerBLL;
import model.Customer;
import presentation.views.CustomersView;

public class CustomersController extends AbstractController<Customer> {
	
	public CustomersController() {
		super(new CustomersView(), new CustomerBLL());
	}
}