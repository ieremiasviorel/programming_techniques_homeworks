package presentation;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import model.Account;
import model.SavingAccount;
import model.SpendingAccount;

public class AccountsView extends JFrame {

	private static final long serialVersionUID = 1L;

	private JTable table;
	private JScrollPane tableHolder;
	private JButton openAccountBtn;
	private JButton closeAccountBtn;
	private JTextField accountNoTf;
	private JTextField amountTf;
	private JTextField interestRateTf;
	private JLabel savingsTypeL;
	private JLabel spendingsTypeL;
	private JRadioButton savingsType;
	private JRadioButton spendingsType;
	private ButtonGroup buttonGroup;
	
	public AccountsView() {
		setTitle("Accounts View");
		setBounds(920, 100, 615, 265);
		setLayout(null);
		
		accountNoTf = new JTextField();
		accountNoTf.setBounds(15, 145, 106, 25);
		add(accountNoTf);
		
		amountTf = new JTextField();
		amountTf.setBounds(131, 145, 106, 25);
		add(amountTf);
		
		interestRateTf = new JTextField();
		interestRateTf.setBounds(247, 145, 106, 25);
		add(interestRateTf);
		
		openAccountBtn = new JButton("Open Account");
		openAccountBtn.setBounds(30, 185, 200, 30);
		add(openAccountBtn);
		
		closeAccountBtn = new JButton("Close Account");
		closeAccountBtn.setBounds(370, 185, 200, 30);
		add(closeAccountBtn);
		
		savingsTypeL = new JLabel("Savings");
		savingsTypeL.setBounds(370, 145, 60, 25);
		add(savingsTypeL);
		
		savingsType = new JRadioButton();
		savingsType.setBounds(425, 145, 20, 25);
		add(savingsType);
		
		spendingsTypeL = new JLabel("Spendings");
		spendingsTypeL.setBounds(475, 145, 60, 25);
		add(spendingsTypeL);
		
		spendingsType = new JRadioButton();
		spendingsType.setBounds(545, 145, 20, 25);
		add(spendingsType);
		
		buttonGroup = new ButtonGroup();
		buttonGroup.add(savingsType);
		buttonGroup.add(spendingsType);
		
		buttonGroup = new ButtonGroup();
		buttonGroup.add(savingsType);
		buttonGroup.add(spendingsType);
		
		buttonGroup = new ButtonGroup();
		buttonGroup.add(savingsType);
		buttonGroup.add(spendingsType);
		
		setVisible(true);
	}
	
	
	public void createTable(ArrayList<Account> accounts) {	
		
		DefaultTableModel tableModel = new DefaultTableModel();
		tableModel.addColumn("Account no.");
		tableModel.addColumn("Balance");
		tableModel.addColumn("InterestRate");
		tableModel.addColumn("No. deposits");
		tableModel.addColumn("No. withdraws");
		
		for(Account ac : accounts) {
			String[] oneRow = new String[5];
			oneRow[0] = ac.getAccountNumber() + "";
			oneRow[1] = ac.getBalance() + "";
			oneRow[2] = ac.getInterestRate() + "";
			oneRow[3] = ac.getNoDeposits()+  "";
			oneRow[4] = ac.getNoWithdraws() + "";
			tableModel.addRow(oneRow);
		}
		
		table = new JTable(tableModel);
		table.setFillsViewportHeight(true);
		tableHolder = new JScrollPane(table);
		tableHolder.setBounds(10, 10, 580, 120);
		add(tableHolder);
	}
	
	public void addOpenAccountListener(ActionListener e) {
		openAccountBtn.addActionListener(e);
	}
	
	public void addCloseAccountListener(ActionListener e) {
		closeAccountBtn.addActionListener(e);
	}
	
	public Account getAccountToInsert() {
		if(savingsType.isSelected() == true) {
			return new SavingAccount(Integer.parseInt(accountNoTf.getText()), Integer.parseInt(amountTf.getText()), Double.parseDouble(interestRateTf.getText()));
		}
		else {
			return new SpendingAccount(Integer.parseInt(accountNoTf.getText()), Integer.parseInt(amountTf.getText()), 0);
		}
	}
	
	public Account getSelectedAccount() {
		int selectedRow = table.getSelectedRow();
		TableModel tableModel = table.getModel();
		Integer accountNumber = Integer.parseInt((String)tableModel.getValueAt(selectedRow, 0));
		Integer balance = Integer.parseInt((String)tableModel.getValueAt(selectedRow, 1));
		Double interestRate = Double.parseDouble((String)tableModel.getValueAt(selectedRow, 2));
		return new SavingAccount(accountNumber, balance, interestRate);
	}
	
	public void addRow(Account c) {
		String[] row = new String[]{((Integer)(c.getAccountNumber())).toString(), ((Integer)(c.getBalance())).toString(), ((Double)(c.getInterestRate())).toString(), ((Integer)(c.getNoDeposits())).toString(), ((Integer)(c.getNoWithdraws())).toString()};
		TableModel tM = table.getModel();
		((DefaultTableModel)tM).addRow(row);
		table.setModel(tM);
	}
	
	public void deleteSelectedRow() {
		TableModel tM = table.getModel();
		((DefaultTableModel)tM).removeRow(table.getSelectedRow());
		table.setModel(tM);
	}
}