package be.afelio.software_academy.jpa.example.dvdrental.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import be.afelio.software_academy.jpa.example.dvdrental.beans.Actor;

@Entity(name="Comedian")
@Table(name="actor")
@NamedQueries({
	@NamedQuery(name="findOneActorByFirstnameAndName", 
			query="select c from Comedian c "
					+ "where c.firstname = :littlename and c.lastname = :name")
})
public class ActorEntity extends Actor {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
}
