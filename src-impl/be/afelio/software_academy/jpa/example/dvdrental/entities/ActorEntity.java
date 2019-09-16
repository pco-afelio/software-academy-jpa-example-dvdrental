package be.afelio.software_academy.jpa.example.dvdrental.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import be.afelio.software_academy.jpa.example.dvdrental.beans.Actor;

@Entity(name="Comedian")
@Table(name="actor")
public class ActorEntity extends Actor {

	@Id
	@Column(name="actor_id")
	private Integer id;
	
	@Column(name="first_name")
	private String firstname;
	
	@Column(name="last_name")
	private String lastname;
	
	@Override
	public String getFirstname() {
		return firstname;
	}

	@Override
	public String getLastname() {
		return lastname;
	}

}
