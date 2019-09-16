package be.afelio.software_academy.jpa.example.dvdrental.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import be.afelio.software_academy.jpa.example.dvdrental.beans.Customer;

@Entity
@Table(name="customer")
public class CustomerEntity extends Customer {

	@Id
	@Column(name="customer_id")
	private Integer id;
	
	@Column(name="first_name")
	private String firstname;
	
	@Column(name="last_name")
	private String lastname;
	
	private String email; 
	
	@Override
	public String getFirstname() {
		return firstname;
	}

	@Override
	public String getLastname() {
		return lastname;
	}

	@Override
	public String getEmail() {
		return email;
	}

}
