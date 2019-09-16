package be.afelio.software_academy.jpa.example.dvdrental.beans;

import java.util.Objects;

public abstract class Language {

    public abstract String getName() ;

	@Override
	public int hashCode() {
		return Objects.hash(getName());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Language))
			return false;
		Language other = (Language) obj;
		return Objects.equals(getName(), other.getName());
	}

	@Override
	public String toString() {
		return "Language [name=" + getName() + "]";
	}

    
}
