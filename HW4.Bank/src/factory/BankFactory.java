package factory;

import java.util.Random;

import model.Account;
import model.Bank;
import model.Person;
import model.SavingAccount;
import model.SpendingAccount;

public class BankFactory {
	
	public static Random rnd = new Random();
	public static String[] lastName = new String[]{"Smith", "Johnson", "Williams", "Brown", "Jones", "Miller", "Davis", "Harris", "Clark", "Wilson",
			"Lewis", "Anderson", "Taylor", "Thomas", "Walker", "Moore", "Martin", "Jackson", "Thompson", "White"};
	public static String[] firstName = new String[]{"James", "John", "Robert", "Michael", "Wiliam", "David", "Richard", "Charles", "Joseph", "Thomas",
			"Christopher", "Daniel", "Paul", "Mark", "Donald", "Mary", "Patricia", "Linda", "Barbara", "Elizabeth", "Jennifer", "Maria", "Susan",
			"Margaret", "Dorothy", "Lisa", "Nancy", "Karen", "Betty", "Helen"};
	
	public static long generateNin() {
		long nin;
		nin = rnd.nextInt(2) + 1;
		for(int i = 0; i < 12; i++) {
			nin = 10 * nin + rnd.nextInt(10);
		}
		return nin;
	}
	
	public static long generatePhoneNumber() {
		long phone = 7;
		for(int i = 0; i < 8; i++) {
			phone = 10 * phone + rnd.nextInt(10);
		}
		return phone;
	}
	
	public static Bank createBank() {
		Bank b = new Bank();
		for(int i = 0; i < 50; i++) {
			Person p = new Person(lastName[rnd.nextInt(20)], firstName[rnd.nextInt(30)], ((Long)generateNin()).toString(), "0" + ((Long)generatePhoneNumber()).toString());
			b.addPerson(p);
			int noAccounts = rnd.nextInt(20) + 1;
			for(int j = 0; j < noAccounts; j++) {
				int x = rnd.nextInt(2);
				Account ac;
				if(x == 0) {
					ac = new SavingAccount(rnd.nextInt(1000) + 1000, rnd.nextInt(100000), rnd.nextInt(25) / (double)10);
				}
				else {
					ac = new SpendingAccount(rnd.nextInt(1000) + 1000, rnd.nextInt(100000));
				}
				ac.addObserver(p);
				b.addAccount(ac, p);
			}
		}
		return b;	
	}
}