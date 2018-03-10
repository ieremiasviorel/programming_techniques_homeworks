package presentation.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import bll.CustomerBLL;
import bll.OrderBLL;
import bll.ProductBLL;
import model.Customer;
import model.Ord;
import model.Product;
import presentation.views.AddOrderView;
import presentation.views.ReceiptCreator;

public class AddOrderController {
	
	private AddOrderView addOrderView;
	private ProductBLL productBLL;
	private CustomerBLL customerBLL;
	private OrderBLL orderBLL;
	
	public AddOrderController() {
		this.addOrderView = new AddOrderView();
		this.productBLL = new ProductBLL();
		this.customerBLL = new CustomerBLL();
		this.orderBLL = new OrderBLL();
		this.addOrderView.addListener(new SendOrderListener());
	}
	
	public class SendOrderListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			
			int noOrders = orderBLL.showAll().size();
			int orderId = orderBLL.showAll().get(noOrders - 1).getId();
			orderId ++;
			int productId = productBLL.findByName(addOrderView.getProduct()).getId();
			
			int noCustomers = customerBLL.showAll().size();
			List<String> customerDetails = addOrderView.getCustomer();
			int customerId = 0;
			
			if(customerDetails.size() == 1) {
				
				customerId = customerBLL.findByName(customerDetails.get(0)).getId();
			}
			else {
				customerId = customerBLL.showAll().get(noCustomers - 1).getId();
				customerId ++;
				customerBLL.insert(new Customer(customerId, customerDetails.get(0), customerDetails.get(1), customerDetails.get(2)));
			}
			Ord order = new Ord(orderId, productId, customerId, addOrderView.getAmount());
			Product product = productBLL.findById(productId);
			Customer customer = customerBLL.findById(customerId);
			orderBLL.insert(order);
			ReceiptCreator.createReceipt(product, customer, order);
		}
	}
}
