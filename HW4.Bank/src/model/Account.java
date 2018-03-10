	package model;

import java.io.Serializable;
import java.util.Observable;

public abstract class Account extends Observable implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	protected int accountNumber;
	protected int balance;
	protected double interestRate;
	protected int noDeposits;
	protected int noWithdraws;
	
	public Account(int accountNumber, int balance, double interestRate) {
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.interestRate = interestRate;
		this.noDeposits = 0;
		this.noWithdraws = 0;
	}
	
	public boolean isWellFormed() {
		if(this.balance < 0 || this.interestRate < 0 || this.noDeposits < 0 || this.noWithdraws < 0) {
			return false;
		}
		if(this instanceof SavingAccount) {
			if(this.noDeposits > 1 || this.noWithdraws > 1) {
				return false;
			}
		}
		return true;
	}
	
	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
		setChanged();
		notifyObservers("Account " + this.accountNumber + " | Balance | new balance: " + this.balance);
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
		setChanged();
		notifyObservers("Account " + this.accountNumber + " | Interest | new interest rate: " + this.interestRate);
	}

	public int getNoDeposits() {
		return noDeposits;
	}

	public void setNoDeposits(int noDeposits) {
		this.noDeposits = noDeposits;
	}

	public int getNoWithdraws() {
		return noWithdraws;
	}

	public void setNoWithdraws(int noWithdraws) {
		this.noWithdraws = noWithdraws;
	}

	public abstract int deposit(int amount);
	public abstract int withdraw(int amount);
	
	public boolean equals(Object a) {
		return (this.accountNumber == ((Account)a).accountNumber);
	}
}