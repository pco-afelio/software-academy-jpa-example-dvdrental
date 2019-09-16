package be.afelio.software_academy.jpa.example.dvdrental.beans;

import java.util.Objects;

public abstract class Customer {
	
	public abstract String getFirstname();
	
	public abstract String getLastname();

	public abstract String getEmail();

	@Override
	public final int hashCode() {
		return Objects.hash(getEmail(), getFirstname(), getLastname());
	}

	@Override
	public final boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Customer))
			return false;
		Customer other = (Customer) obj;
		return Objects.equals(getEmail(), other.getEmail()) 
				&& Objects.equals(getFirstname(), other.getFirstname())
				&& Objects.equals(getLastname(), other.getLastname());
	}
}
