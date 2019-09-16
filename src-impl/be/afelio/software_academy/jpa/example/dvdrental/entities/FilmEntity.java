package be.afelio.software_academy.jpa.example.dvdrental.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import be.afelio.software_academy.jpa.example.dvdrental.beans.Film;

@Entity(name="Film")
@Table(name="film")
@NamedQueries({
	@NamedQuery(
			name="countFilmsByLanguageName", 
			query="select count(f) from Film f where f.version.name = ?1"),
	@NamedQuery(
			name="findOneFilmByTitleLazy",
			query="select f from Film f where f.title = ?1"),
	@NamedQuery(
			name="findOneFilmByTitleEager",
			query="select f from Film f join fetch f.actors where f.title = ?1")
})
public class FilmEntity extends Film {

	@Id
	@Column(name="film_id")
	private Integer id;
	
	private String title;
	private String description;
	private Integer length;
	@Column(name="release_year") private Integer year;
	
	@ManyToOne
	@JoinColumn(name="language_id")
	private LanguageEntity version;
	
	// @Transient
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="film_actor", 
			joinColumns=@JoinColumn(name="film_id"),
			inverseJoinColumns=@JoinColumn(name="actor_id"))
	private List<ActorEntity> actors;
	
	public String getTitle() {
		return title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public Integer getLength() {
		return length;
	}
	
	public Integer getYear() {
		return year;
	}
	
	public LanguageEntity getLanguage() {
		return version;
	}
	
	public List<ActorEntity> getActors() {
		return actors;
	}
	
}
