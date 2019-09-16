package be.afelio.software_academy.jpa.example.dvdrental.beans;

import java.util.*;

public abstract class Category {

	public abstract String getName();
	
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
		if (!(obj instanceof Category))
			return false;
		Category other = (Category) obj;
		return Objects.equals(getName(), other.getName());
	}

	@Override
	public String toString() {
		return "Category [getName()=" + getName() + "]";
	}
	
}
