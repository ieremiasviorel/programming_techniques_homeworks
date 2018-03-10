package presentation;

import java.awt.event.ActionListener;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import model.Account;
import model.Person;

public class UserView extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private JComboBox<String> userSelection;
	private JComboBox<String> accountSelection;
	private JComboBox<String> operationSelection;
	private JLabel amountL;
	private JTextField amountTf;
	private JButton executeTransactionBtn;
	private JLabel transactionStatusL;
	private JLabel newBalanceL;
	
	public UserView() {
		
		setTitle("User View");
		setBounds(520, 100, 510, 320);
		setLayout(null);
		
		userSelection = new JComboBox<String>();
		userSelection.setBounds(50, 50, 200, 25);
		add(userSelection);
		
		accountSelection = new JComboBox<String>();
		accountSelection.setBounds(50, 130, 200, 25);
		add(accountSelection);
		
		operationSelection = new JComboBox<String>();
		operationSelection.setBounds(50, 210, 200, 25);
		operationSelection.addItem("");
		operationSelection.addItem("Deposit");
		operationSelection.addItem("Withdraw");
		add(operationSelection);
		
		executeTransactionBtn = new JButton("Execute");
		executeTransactionBtn.setBounds(300, 195, 160, 50);
		add(executeTransactionBtn);
		
		amountL = new JLabel("Amount");
		amountL.setBounds(300, 110, 160, 25);
		amountL.setHorizontalAlignment(SwingConstants.CENTER);
		add(amountL);
		
		amountTf = new JTextField();
		amountTf.setBounds(300, 140, 160, 25);
		add(amountTf);
		
		transactionStatusL = new JLabel();
		transactionStatusL.setBounds(300, 40, 160, 25);
		transactionStatusL.setHorizontalAlignment(SwingConstants.CENTER);
		transactionStatusL.setVisible(false);
		add(transactionStatusL);
		
		newBalanceL = new JLabel();
		newBalanceL.setBounds(300, 70, 160, 25);
		newBalanceL.setHorizontalAlignment(SwingConstants.CENTER);
		transactionStatusL.setVisible(false);
		add(newBalanceL);
		
		setVisible(true);
	}
	
	public String getSelectedPerson() {
		return (String)userSelection.getSelectedItem();
	}
	
	public int getSelectedAccount() {
		return Integer.parseInt((String) accountSelection.getSelectedItem());
	}
	
	public void fillUserSelectionComboBox(Set<Person> people) {
		userSelection.removeAllItems();
		userSelection.addItem("Select user");
		for(Person p : people)
		{
			userSelection.addItem(p.getLastName() + " " + p.getFirstName());
		}
	}
	
	public int getAmount() {
		return Integer.parseInt(amountTf.getText());
	}
	
	public String getOperation() {
		return (String) operationSelection.getSelectedItem();
	}
	
	public void fillAccountSelectionComboBox(List<Account> accounts) {
		accountSelection.removeAllItems();
		accountSelection.addItem("Select account");
		for(Account a : accounts)
		{
			accountSelection.addItem(a.getAccountNumber() + "");
		}
	}
	
	public void addUserSelectionListener(ActionListener e) {
		userSelection.addActionListener(e);
	}
	
	public void addExecuteActionListener(ActionListener e) {
		executeTransactionBtn.addActionListener(e);
	}
}