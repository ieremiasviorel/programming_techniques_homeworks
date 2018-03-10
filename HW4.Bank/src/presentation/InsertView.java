package presentation;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import model.Account;
import model.Person;
import model.SavingAccount;
import model.SpendingAccount;

public class InsertView extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JLabel clientDetailsL;
	private JLabel accountDetailsL;
	private JLabel lastNameL;
	private JTextField lastNameTf;
	private JLabel firstNameL;
	private JTextField firstNameTf;
	private JLabel ninL;
	private JTextField ninTf;
	private JLabel phoneL;
	private JTextField phoneTf;
	private JLabel amountL;
	private JTextField amountTf;
	private JLabel interestRateL;
	private JTextField interestRateTf;
	private JLabel savingsTypeL;
	private JLabel spendingsTypeL;
	private JRadioButton savingsType;
	private JRadioButton spendingsType;
	private ButtonGroup buttonGroup;
	private JButton insertBtn;
	
	public InsertView() {
		
		setTitle("Insert client");
		setBounds(520, 100, 490, 265);
		setLayout(null);
		
		clientDetailsL = new JLabel("Client details");
		clientDetailsL.setBounds(20, 20, 205, 25);
		clientDetailsL.setHorizontalAlignment(SwingConstants.CENTER);
		clientDetailsL.setForeground(Color.green);
		add(clientDetailsL);
		
		accountDetailsL = new JLabel("Account details");
		accountDetailsL.setBounds(265, 20, 205, 25);
		accountDetailsL.setHorizontalAlignment(SwingConstants.CENTER);
		accountDetailsL.setForeground(Color.green);
		add(accountDetailsL);
		
		lastNameL = new JLabel("Last name:");
		lastNameL.setBounds(20, 60, 80, 25);
		add(lastNameL);
		
		lastNameTf = new JTextField();
		lastNameTf.setBounds(105, 60, 100, 25);
		add(lastNameTf);
		
		firstNameL = new JLabel("First name:");
		firstNameL.setBounds(20, 100, 80, 25);
		add(firstNameL);
		
		firstNameTf = new JTextField();
		firstNameTf.setBounds(105, 100, 100, 25);
		add(firstNameTf);
		
		ninL = new JLabel("ID number:");
		ninL.setBounds(20, 140, 80, 25);
		add(ninL);
		
		ninTf = new JTextField();
		ninTf.setBounds(105, 140, 100, 25);
		add(ninTf);
		
		phoneL = new JLabel("Phone:");
		phoneL.setBounds(20, 180, 80, 25);
		add(phoneL);
		
		phoneTf = new JTextField();
		phoneTf.setBounds(105, 180, 100, 25);
		add(phoneTf);
		
		amountL = new JLabel("Amount:");
		amountL.setBounds(265, 60, 100, 25);
		add(amountL);
		
		amountTf = new JTextField();
		amountTf.setBounds(350, 60, 100, 25);
		add(amountTf);
		
		interestRateL = new JLabel("Interest rate:");
		interestRateL.setBounds(265, 100, 100, 25);
		add(interestRateL);
		
		interestRateTf = new JTextField();
		interestRateTf.setBounds(350, 100, 100, 25);
		add(interestRateTf);
		
		savingsTypeL = new JLabel("Savings");
		savingsTypeL.setBounds(265, 140, 60, 25);
		add(savingsTypeL);
		
		savingsType = new JRadioButton();
		savingsType.setBounds(315, 140, 20, 25);
		add(savingsType);
		
		spendingsTypeL = new JLabel("Spendings");
		spendingsTypeL.setBounds(360, 140, 60, 25);
		add(spendingsTypeL);
		
		spendingsType = new JRadioButton();
		spendingsType.setBounds(430, 140, 20, 25);
		add(spendingsType);
		
		buttonGroup = new ButtonGroup();
		buttonGroup.add(savingsType);
		buttonGroup.add(spendingsType);
		
		insertBtn = new JButton("Insert");
		insertBtn.setBounds(265, 180, 185, 25);
		add(insertBtn);
		
		
		
		setVisible(true);
	}
	
	public Person getPersonToInsert() throws IllegalArgumentException {
		return new Person(lastNameTf.getText(), firstNameTf.getText(), ninTf.getText(), phoneTf.getText());
	}
	
	public Account getAccountToInsert() throws IllegalArgumentException {
		if(savingsType.isSelected() == true) {
			return new SavingAccount(1, Integer.parseInt(amountTf.getText()), Double.parseDouble(interestRateTf.getText()));
		}
		else {
			return new SpendingAccount(1, Integer.parseInt(amountTf.getText()), 0);
		}
	}
	
	public void addInsertButtonListener(ActionListener e) {
		insertBtn.addActionListener(e);
	}
}