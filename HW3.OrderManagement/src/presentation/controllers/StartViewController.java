package presentation.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import presentation.views.StartView;

public class StartViewController {
	
	private StartView startView;
	
	public StartViewController(StartView startView) {
		this.startView = startView;
		this.startView.addProductsActionListener(new ProductActionListener());
		this.startView.addCustomersActionListener(new CustomerActionListener());
		this.startView.addOrdersActionListener(new OrderActionListener());
		this.startView.addAddOrderActionListener(new AddOrderActionListener());
	}
	
	class ProductActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new ProductsController();
		}
	}
	
	class CustomerActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new CustomersController();
		}
	}
	
	class OrderActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new OrdersController();
		}
	}
	
	class AddOrderActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new AddOrderController();
		}
	}
}