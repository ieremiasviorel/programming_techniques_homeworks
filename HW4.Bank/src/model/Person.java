package model;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

public class Person implements Observer, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String lastName;
	private String firstName;
	private String nin;
	private String phone;
	
	public Person(String lastName, String firstName, String nin, String phone) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.nin = nin;
		this.phone = phone;
	}

	public boolean isWellFormed() {
		String regex = "^[0-9]*$";
		if(!(nin.matches(regex)) || !(nin.length() == 13)) {
			return false;
		}
		if(!(phone.matches(regex)) || !(phone.length() == 10)) {
			return false;
		}
		return true;
	}
	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getNin() {
		return nin;
	}

	public void setNin(String nin) {
		this.nin = nin;
	}
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public int hashCode() {
		return (Long.valueOf(this.nin)).hashCode();
	}
	
	@Override
	public boolean equals(Object p) {
		return this.nin.equals(((Person)p).nin);
	}
	
	@Override
	public void update(Observable account, Object message) {
		System.out.println(message);
	}
	
	@Override
	public String toString() {
		return "[" + this.lastName + " | " + this.firstName + " | " + this.nin + " | " + this.phone + "]";
	}
}