package model;

public class SpendingAccount extends Account {

	private static final long serialVersionUID = 1L;

	public SpendingAccount(int accountNumber, int balance, double interestRate) {
		super(accountNumber, balance, 0);
	}
	
	public SpendingAccount(int accountNumber, int balance) {
		super(accountNumber, balance, 0);
	}
	
	public int deposit(int amount) {
		this.balance += amount;
		setChanged();
		this.noDeposits++;
		notifyObservers("Account " + this.accountNumber + " | Deposit | Amount: " + amount + " | New balance: " + this.balance);
		return amount;
	}
	
	public int withdraw(int amount) {
		if(amount <= this.balance) {
			this.balance -= amount;
			setChanged();
			this.noWithdraws++;
			notifyObservers("Account " + this.accountNumber + " | Withdraw | Amount: " + amount + " | New balance: " + this.balance);
			return amount;
		}
		else {
			System.err.println("Cannot withdraw " + amount + " . Current balance is: " + this.balance);
			return 0;
		}
	}
}