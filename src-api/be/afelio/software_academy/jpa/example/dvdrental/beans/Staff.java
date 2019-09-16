package be.afelio.software_academy.jpa.example.dvdrental.beans;

import java.util.Objects;

public abstract class Staff {

	public abstract String getEmail();
	public abstract String getPassword();
	
	@Override
	public int hashCode() {
		return Objects.hash(getEmail(), getPassword());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Staff))
			return false;
		Staff other = (Staff) obj;
		return Objects.equals(getEmail(), other.getEmail()) 
				&& Objects.equals(getPassword(), other.getPassword());
	}
	@Override
	public String toString() {
		return "Staff [getEmail()=" + getEmail() + ", getPassword()=" + getPassword() + "]";
	}
	
	
	
}
