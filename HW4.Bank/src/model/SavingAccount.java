package model;

public class SavingAccount extends Account {

	private static final long serialVersionUID = 1L;
	

	public SavingAccount(int accountNumber, int balance, double interestRate) {
		super(accountNumber, balance, interestRate);
	}
	
	public int deposit(int amount) {
		if(this.noDeposits == 0) {
			this.balance += amount;
			setChanged();
			notifyObservers("Account " + this.accountNumber + " | Deposit | Amount: " + amount + " | New balance: " + this.balance);
			this.noDeposits++;
			return amount;
		}
		else {
			System.out.println("Only one deposit allowed for saving account.");
			return 0;
		}
	}
	
	public int withdraw(int amount) {
		if(this.noWithdraws == 0) {
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
		else {
			System.err.println("Only one withdraw allowed for saving account.");
			return 0;
		}
	}
}