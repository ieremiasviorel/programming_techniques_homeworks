package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Bank;
import presentation.AdminView;
import presentation.UserView;
import presentation.StartView;

public class StartViewController {
	
	private Bank bank;
	private StartView startView;
	
	public StartViewController(Bank bank, StartView startView) {
		this.bank = bank;
		this.startView = startView;
		this.startView.addAdminButtonActionListener(new AdminButtonListener());
		this.startView.addUserButtonActionListener(new UserButtonListener());
	}
	
	class AdminButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			new AdminViewController(bank, new AdminView());
		}
	}
	
	class UserButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			new UserViewController(bank, new UserView());
		}
	}
}