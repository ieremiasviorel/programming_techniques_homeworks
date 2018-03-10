package model;

public interface BankProc {

	/**
	 * Adds a new client to the HashMap
	 * @pre p.isWellFormed()
	 * @pre !isClient(p)
	 * @post getNoClients() == getNoClients()@pre + 1
	 * @post isClient(p)
	 */
	public void addPerson(Person p);
	
	/**
	 * Removes a specific person from the HashMap
	 * @pre p.isWellFormed()
	 * @pre isClients(p)
	 * @post getNoClients() == getNoClients()@pre - 1
	 * @post !isClient(p)
	 */
	public void removePerson(Person p);
	
	/**
	 * Adds account a for the client p
	 * @pre p.isWellFormed()
	 * @pre a.isWellFormed()
	 * @pre isClient(p)
	 * @pre !hasAccount(p, a)
	 * @post getNoClientAccounts(p)==getNoClientAccounts(p)@pre+1
	 * @post hasAccount(p, a)
	 */
	public void addAccount(Account a, Person p);
	
	/**
	 * Removes account a for the client p
	 * @pre p.isWellFormed()
	 * @pre a.isWellFormed()
	 * @pre isClient(p)
	 * @pre hasAccount(p, a)
	 * @post getNoClientAccounts(p) == getNoClientAccounts(p)@pre - 1
	 * @post !hasAccount(p, a)  
	 */
	public void removeAccount(Account a, Person p);
}