package br.com.medicine.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table (name= "person")
public class Person {

	@Id @GeneratedValue
	protected Long id;
	
	protected String name;
	protected String email;
	protected String password;
	
	@OneToOne(cascade = CascadeType.ALL)
	protected Address address;

	public Person() {}
	
	public Person(String name, String email, String password,Address address) {
		this.setName(name);
		this.setEmail(email);
		this.setPassword(password);
		this.setAddress(address);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address contact) {
		this.address = contact;
	}

	public Long getId() {
		// TODO Auto-generated method stub
		return this.id;
	}
	
	
	
	
}
