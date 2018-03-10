package testing;

import org.junit.Test;

import model.*;

public class OperationTesting {

	@Test
	public void testDeposit() {
		Account a1 = new SavingAccount(1, 1000, 2.1);
		int orgBalance = a1.getBalance();
		a1.deposit(100);
		assert(orgBalance + 100 == a1.getBalance());
		
		Account a2 = new SpendingAccount(2, 3000);
		orgBalance = a2.getBalance();
		a2.deposit(1000);
		assert(orgBalance + 1000 == a2.getBalance());
	}
	
	@Test
	public void testWithDraw() {
		Account a1 = new SavingAccount(1, 1000, 2.1);
		int orgBalance = a1.getBalance();
		a1.withdraw(300);
		assert(orgBalance - 300 == a1.getBalance());
		
		Account a2 = new SpendingAccount(2, 3000);
		orgBalance = a2.getBalance();
		a2.withdraw(700);
		assert(orgBalance - 700 == a2.getBalance());
	}
}