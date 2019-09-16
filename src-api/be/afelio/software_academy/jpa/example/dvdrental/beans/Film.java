package be.afelio.software_academy.jpa.example.dvdrental.beans;

import java.util.*;

public abstract class Film {

	public abstract String getTitle();

	public abstract String getDescription();

	public abstract Integer getYear();

	public abstract Integer getLength();

	public abstract Language getLanguage();

	public abstract List<? extends Actor> getActors();

	@Override
	public int hashCode() {
		return Objects.hash(getDescription(), getLanguage(), 
				getLength(), getTitle(), getYear());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Film))
			return false;
		Film other = (Film) obj;
		return Objects.equals(getDescription(), other.getDescription())
				&& Objects.equals(getLanguage(), other.getLanguage()) 
				&& Objects.equals(getLength(), other.getLength())
				&& Objects.equals(getTitle(), other.getTitle()) 
				&& Objects.equals(getYear(), other.getYear());
	}

	@Override
	public String toString() {
		return "Film [getTitle()=" + getTitle() + ", getDescription()=" + getDescription() + ", getYear()=" + getYear()
				+ ", getLength()=" + getLength() + ", getLanguage()=" + getLanguage()
				+ "]";
	}
}
