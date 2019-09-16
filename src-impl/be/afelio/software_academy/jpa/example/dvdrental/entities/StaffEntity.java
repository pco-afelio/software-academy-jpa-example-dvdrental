package be.afelio.software_academy.jpa.example.dvdrental.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import be.afelio.software_academy.jpa.example.dvdrental.beans.Staff;

@Entity(name="Staff")
@Table(name="staff")
@NamedQueries({
	@NamedQuery(
			name="findOneStaffByEmail", 
			query="select s from Staff s where s.email = ?1"),
	@NamedQuery(
			name="findOneStaffByPassword", 
			query="select s from Staff s where s.password = ?1")
})
public class StaffEntity extends Staff {

	@Id
	@Column(name="staff_id")
	private Integer id;
	private String email;
	private String password;
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	
	
}
