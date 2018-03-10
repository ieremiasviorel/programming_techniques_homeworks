package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *@invariant isWellFormed()
 */

public class Bank implements BankProc, Serializable {

	private static final long serialVersionUID = 1L;
	private Map<Person, ArrayList<Account>> bankData;
	
	/**
	 * @post isWellFormed
	 */
	public Bank() {
		bankData = new HashMap<Person, ArrayList<Account>>();
	}
	
	public boolean isWellFormed() {
		Set<Person> clients = bankData.keySet();
		if(bankData.size() != 0) {
			for(Person p : clients) {
				ArrayList<Account> accounts = bankData.get(p);
				for(Account ac : accounts) {
					if(ac.getBalance() < 0 || ac.getInterestRate() < 0) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	/**
	 * @pre isWellFormed()
	 * @post isWellFormed()
	 */
	public void addPerson(Person p) {
		assert isWellFormed();
		assert p.isWellFormed();
		assert !isClient(p);
		int sizePre = getNoClients();
		bankData.put(p, new ArrayList<Account>());
		int sizePost = getNoClients();
		assert sizePre + 1 == sizePost;
		assert isClient(p);
		assert isWellFormed();
	}

	/**
	 * @pre isWellFormed()
	 * @post isWellFormed()
	 */
	public void removePerson(Person p) {
		assert isWellFormed();
		assert p.isWellFormed();
		assert isClient(p);
		int sizePre = getNoClients();
		bankData.remove(p);
		int sizePost = getNoClients();
		assert sizePre - 1 == sizePost;
		assert !isClient(p);
		assert isWellFormed();
	}

	/**
	 * @pre isWellFormed()
	 * @post isWellFormed()
	 */
	public void addAccount(Account a, Person p) {
		assert isWellFormed();
		assert a.isWellFormed();
		assert p.isWellFormed();
		assert isClient(p);
		assert !hasAccount(p, a);
		int noAccountsPre = getNoClientAccounts(p);
		bankData.get(p).add(a);
		int noAccountsPost = getNoClientAccounts(p);
		assert noAccountsPre + 1 == noAccountsPost;
		assert hasAccount(p, a);
		assert isWellFormed();
	}

	/**
	 * @pre isWellFormed()
	 * @post isWellFormed()
	 */
	public void removeAccount(Account a, Person p) {
		assert isWellFormed();
		assert a.isWellFormed();
		assert p.isWellFormed();
		assert isClient(p);
		assert hasAccount(p, a);
		int noAccountsPre = getNoClientAccounts(p);
		bankData.get(p).remove(a);
		int noAccountsPost = getNoClientAccounts(p);
		assert noAccountsPre - 1 == noAccountsPost;
		assert !hasAccount(p, a);
		assert isWellFormed();
	}
	
	public int getNoClients() {
		return bankData.keySet().size();
	}
	
	public int getNoClientAccounts(Person p) {
		return bankData.get(p).size();
	}
	
	public boolean isClient(Person p) {
		Set<Person> clients = bankData.keySet();
		for(Person c : clients) {
			if(c.equals(p)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean hasAccount(Person p, Account a) {
		List<Account> accounts = bankData.get(p);
		for(Account acc : accounts) {
			if(acc.equals(a)) {
				return true;
			}
		}
		return false;
	}
	
	public void updatePersonDetails(Person p) {
		Set<Person> people = bankData.keySet();
		for(Person pers : people) {
			if(pers.equals(p)) {
				pers.setLastName(p.getLastName());
				pers.setFirstName(p.getFirstName());
				pers.setPhone(p.getPhone());
				break;
			}
		}
	}
	
	public Set<Person> viewClients() {
		return bankData.keySet();
	}
	
	public ArrayList<Account> viewClientAccounts(Person p) {
		return bankData.get(p);
	}
	
	public Person findPersonByName(String fullName) {
		Set<Person> people = bankData.keySet();
		for(Person p : people) {
			if(fullName.equals(p.getLastName() + " " + p.getFirstName())) {
				return p;
			}
		}
		return null;
	}
	
	public Account findAccountByNumber(Person p, int accountNumber) {
		List<Account> accounts = bankData.get(p);
		for(Account a : accounts) {
			if(a.getAccountNumber() == accountNumber) {
				return a;
			}
		}
		return null;
	}
	
	public Map<Person, ArrayList<Account>> getBankData() {
		return bankData;
	}
	
	public void setBankData(Map<Person, ArrayList<Account>> bankData) {
		this.bankData = bankData;
	}
	
	public void reestablishRelations() {
		Set<Person> people = bankData.keySet();
		for(Person p : people) {
			List<Account> accounts = viewClientAccounts(p);
			for(Account ac : accounts) {
				ac.addObserver(p);
			}
		}
	}
}