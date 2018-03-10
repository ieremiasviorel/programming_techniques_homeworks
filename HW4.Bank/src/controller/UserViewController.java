package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Bank;
import presentation.UserView;

public class UserViewController {
	
	private Bank bank;
	private UserView userView;
	
	public UserViewController(Bank bank, UserView userView) {
		this.bank = bank;
		this.userView = userView;
		this.userView.fillUserSelectionComboBox(this.bank.viewClients());
		this.userView.addUserSelectionListener(new UserSelectionActionListener());
		this.userView.addExecuteActionListener(new ExecuteTransactionActionListener());
	}
	
	class UserSelectionActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			userView.fillAccountSelectionComboBox(bank.viewClientAccounts(bank.findPersonByName(userView.getSelectedPerson())));
		}
	}
	
	class ExecuteTransactionActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				if(userView.getOperation().equals("Deposit")) {
					bank.findAccountByNumber(bank.findPersonByName(userView.getSelectedPerson()), userView.getSelectedAccount()).deposit(userView.getAmount());
				}
				else {
					if(userView.getOperation().equals("Withdraw")) {
						bank.findAccountByNumber(bank.findPersonByName(userView.getSelectedPerson()), userView.getSelectedAccount()).withdraw(userView.getAmount());
					}
				}	
			} catch (IllegalArgumentException ex) {
				System.err.println("Illegal Argument Exception : Transaction");
			}
		}
	}
}