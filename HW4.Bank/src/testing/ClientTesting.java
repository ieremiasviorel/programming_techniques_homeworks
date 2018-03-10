package testing;

import org.junit.Test;

import model.Account;
import model.Bank;
import model.Person;
import model.SavingAccount;
import model.SpendingAccount;

public class ClientTesting {

	@Test
	public void addAccountTest() {
		Bank bank = new Bank();
		Person p = new Person("Vasilescu", "Andrei", "1234567890123", "1234567890");
		bank.addPerson(p);
		int noAccounts = bank.getNoClientAccounts(p);
		Account ac = new SavingAccount(1, 1000, 1.2);
		bank.addAccount(ac, p);
		assert bank.hasAccount(p, ac);
		assert noAccounts + 1 == bank.getNoClientAccounts(p);
	}
	
	@Test
	public void removeAccountTest() {
		Bank bank = new Bank();
		Person p = new Person("Vasilescu", "Andrei", "1234567890123", "1234567890");
		bank.addPerson(p);
		Account ac = new SavingAccount(1, 1000, 1.2);
		Account acc = new SpendingAccount(2, 800);
		bank.addAccount(ac, p);
		bank.addAccount(acc, p);
		int noAccounts = bank.getNoClientAccounts(p);
		
		bank.removeAccount(acc, p);
		assert bank.hasAccount(p, ac);
		assert !bank.hasAccount(p, acc);
		assert noAccounts - 1 == bank.getNoClientAccounts(p);
	}
}