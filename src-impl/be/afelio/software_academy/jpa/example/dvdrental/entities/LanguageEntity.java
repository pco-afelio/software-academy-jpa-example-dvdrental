package be.afelio.software_academy.jpa.example.dvdrental.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import be.afelio.software_academy.jpa.example.dvdrental.beans.Language;

@Entity(name="Language")
@Table(name="language")
@NamedQueries({
	@NamedQuery(
			name="findOneLanguageByName", 
			query="select l from Language l where l.name = ?1")
})
public class LanguageEntity extends Language {

	@Id
	@Column(name="language_id")
	private Integer id;
	private String name;
	@OneToMany(mappedBy="version")
	private List<FilmEntity> films;
	
	@Override
	public String getName() {
		return name;
	}
	
	public List<FilmEntity> getFilms() {
		return films;
	}
}
