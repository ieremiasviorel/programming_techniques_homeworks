package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Bank;
import presentation.AccountsView;
import presentation.AdminView;
import presentation.InsertView;

public class AdminViewController {
	
	private Bank bank;
	private AdminView adminView;
	
	public AdminViewController(Bank bank, AdminView adminView) {
		this.bank = bank;
		this.adminView = adminView;
		this.adminView.createTable(this.bank.viewClients());
		this.adminView.addInsertButtonListener(new InsertButtonListener());
		this.adminView.addDeleteButtonListener(new DeleteButtonListener());
		this.adminView.addUpdateButtonListener(new UpdateButtonListener());
		this.adminView.addAccountsButtonListener(new AccountsButtonListener());
	}
	
	class InsertButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new InsertViewController(bank, adminView, new InsertView());
		}
	}
	
	class DeleteButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				bank.removePerson(adminView.getSelectedPerson());
				adminView.deleteSelectedRow();
			} catch (ArrayIndexOutOfBoundsException ex) {
				System.err.println("No row selected for delete!");	
			}
		}
	}
	
	class UpdateButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				bank.updatePersonDetails(adminView.getSelectedPerson());
			} catch (ArrayIndexOutOfBoundsException ex) {
				System.err.println("No row selected for update!");
			}
		}
	}
	
	class AccountsButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				new AccountsViewController(bank, adminView.getSelectedPerson(), new AccountsView());
			} catch (ArrayIndexOutOfBoundsException ex) {
				System.err.println("No client selected!");
			}
		}
	}
}