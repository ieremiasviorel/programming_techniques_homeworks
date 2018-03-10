package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Account;
import model.Bank;
import model.Person;
import presentation.AccountsView;

public class AccountsViewController {

	private Bank bank;
	private Person person;
	private AccountsView accountsView;
	
	public AccountsViewController(Bank bank, Person person, AccountsView accountsView) {
		this.bank = bank;
		this.person = person;
		this.accountsView = accountsView;
		this.accountsView.createTable(bank.viewClientAccounts(this.person));
		this.accountsView.addOpenAccountListener(new OpenAccountActionListener());
		this.accountsView.addCloseAccountListener(new CloseAccountActionListener());
	}
	
	class OpenAccountActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				Account c = accountsView.getAccountToInsert();
				c.addObserver(person);
				bank.addAccount(c, person);
				accountsView.addRow(c);
			} catch (IllegalArgumentException ex) {
				System.err.println("Illegal Argument Exception : Insert Account");
			}
		}
	}
	
	class CloseAccountActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				Account c = accountsView.getSelectedAccount();
				bank.removeAccount(c, person);
				accountsView.deleteSelectedRow();
			} catch (ArrayIndexOutOfBoundsException ex) {
				System.err.println("No account selected to close!");
			}
		}
	}
}