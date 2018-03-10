package presentation.views;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class StartView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private JButton productsBtn;
	private JButton customersBtn;
	private JButton ordersBtn;
	private JButton addOrderBtn;
	private JLabel adminL;
	private JLabel userL;
	
	public StartView() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Order management system");
		setBounds(100, 100, 920, 440);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		productsBtn = new JButton("Products");
		productsBtn.setBounds(100, 50, 300, 80);
		contentPane.add(productsBtn);
		
		customersBtn = new JButton("Customers");
		customersBtn.setBounds(100, 170, 300, 80);
		contentPane.add(customersBtn);
		
		ordersBtn = new JButton("Orders");
		ordersBtn.setBounds(100, 290, 300, 80);
		contentPane.add(ordersBtn);
		
		addOrderBtn = new JButton("Add order");
		addOrderBtn.setBounds(500, 50, 300, 320);
		contentPane.add(addOrderBtn);
		
		adminL = new JLabel("Admin Space");
		adminL.setBounds(100, 10, 300, 25);
		adminL.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(adminL);
		
		userL = new JLabel("User Space");
		userL.setBounds(500, 10, 300, 25);
		userL.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(userL);
		
		setVisible(true);
	}
	
	public void addProductsActionListener(ActionListener e) {
		productsBtn.addActionListener(e);
	}
	
	public void addCustomersActionListener(ActionListener e) {
		customersBtn.addActionListener(e);
	}
	
	public void addOrdersActionListener(ActionListener e) {
		ordersBtn.addActionListener(e);
	}
	
	public void addAddOrderActionListener(ActionListener e) {
		addOrderBtn.addActionListener(e);
	}
}