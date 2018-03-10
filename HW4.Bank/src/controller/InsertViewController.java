package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Account;
import model.Bank;
import model.Person;
import presentation.AdminView;
import presentation.InsertView;

public class InsertViewController {

	private Bank bank;
	private AdminView adminView;
	private InsertView insertView;
	
	public InsertViewController(Bank bank, AdminView adminView, InsertView insertView) {
		
		this.bank = bank;
		this.adminView = adminView;
		this.insertView = insertView;
		this.insertView.addInsertButtonListener(new InsertButtonListener());
	}
	
	class InsertButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				Person p = insertView.getPersonToInsert();
				Account ac = insertView.getAccountToInsert();
				ac.addObserver(p);
				bank.addPerson(p);
				bank.addAccount(ac, p);
				adminView.addRow(p);
			} catch (IllegalArgumentException ex) {
				System.err.println("Illegal Argument Exception : Insert Client");
			}
			insertView.dispose();
		}
	}
}