package be.afelio.software_academy.jpa.example.dvdrental.beans;

import java.util.Objects;

public abstract class Actor {

	public abstract String getFirstname();

	public abstract String getLastname();

	@Override
	public int hashCode() {
		return Objects.hash(getFirstname(), getLastname());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Actor))
			return false;
		Actor other = (Actor) obj;
		return Objects.equals(getFirstname(), other.getFirstname()) 
				&& Objects.equals(getLastname(), other.getLastname());
	}

	@Override
	public String toString() {
		return "Actor [getFirstname()=" + getFirstname() + ", getLastname()=" + getLastname() + "]";
	}
}
