package presentation.views;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import bll.CustomerBLL;
import bll.ProductBLL;
import model.Customer;
import model.Product;

public class AddOrderView extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JComboBox<String> productComboBox;
	private JComboBox<String> customerComboBox;
	private JLabel nameL;
	private JTextField nameTf;
	private JLabel addressL;
	private JTextField addressTf;
	private JLabel phoneL;
	private JTextField phoneTf;
	private JLabel amountL;
	private JTextField amountTf;
	private JButton sendOrderBtn;

	public AddOrderView() {
		
		setTitle("Create order");
		setBounds(700, 100, 600, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		List<Product> products = (new ProductBLL()).showAll();
		Vector<String> productsList = new Vector<String>();
		productsList.add("Select a product");
		for(Product p : products) {
			productsList.add(p.getName());
		}
		
		productComboBox = new JComboBox<String>(productsList);
		productComboBox.setBounds(70, 70, 160, 30);
		contentPane.add(productComboBox);
		
		List<Customer> customers = (new CustomerBLL()).showAll();
		Vector<String> customersList = new Vector<String>();
		customersList.add("Select your name");
		for(Customer p : customers) {
			customersList.add(p.getName());
		}
		
		customerComboBox = new JComboBox<String>(customersList);
		customerComboBox.setBounds(70, 150, 160, 30);
		contentPane.add(customerComboBox);
		
		nameL = new JLabel("Name:");
		nameL.setBounds(350, 70, 60, 25);
		contentPane.add(nameL);
		
		nameTf = new JTextField();
		nameTf.setBounds(420, 70, 120, 25);
		contentPane.add(nameTf);
		
		addressL = new JLabel("Adress:");
		addressL.setBounds(350, 140, 60, 25);
		contentPane.add(addressL);
		
		addressTf = new JTextField();
		addressTf.setBounds(420, 140, 120, 25);
		contentPane.add(addressTf);
		
		phoneL = new JLabel("Phone:");
		phoneL.setBounds(350, 210, 60, 25);
		contentPane.add(phoneL);
		
		phoneTf = new JTextField();
		phoneTf.setBounds(420, 210, 120, 25);
		contentPane.add(phoneTf);
		
		amountL = new JLabel("Amount");
		amountL.setBounds(70, 210, 60, 25);
		contentPane.add(amountL);
		
		amountTf = new JTextField();
		amountTf.setBounds(120, 210, 110, 25);
		contentPane.add(amountTf);
		
		sendOrderBtn = new JButton("SEND ORDER");
		sendOrderBtn.setBounds(100, 280, 400, 60);
		contentPane.add(sendOrderBtn);
		
		setVisible(true);
	}
	
	public String getProduct() {
		return (String)productComboBox.getSelectedItem();
	}
	
	public List<String> getCustomer() {
		List<String> customer = new ArrayList<String>();
		if(new String("Select your name").equals((((String)customerComboBox.getSelectedItem())))) {
			customer.add(nameTf.getText());
			customer.add(addressTf.getText());
			customer.add(phoneTf.getText());
		}
		else {
			customer.add((String)customerComboBox.getSelectedItem());
		}

		return customer;
	}
	
	public int getAmount() {
		return Integer.parseInt(amountTf.getText());
	}
	
	public void addListener(ActionListener e) {
		sendOrderBtn.addActionListener(e);
	}
	
	public void clearFields() {
		nameTf.setText("");
		addressTf.setText("");
		phoneTf.setText("");
		amountTf.setText("");
	}
}