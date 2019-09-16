package be.afelio.software_academy.jpa.example.dvdrental.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import be.afelio.software_academy.jpa.example.dvdrental.beans.Category;

@Entity(name="Category")
@Table(name="category")
@NamedQueries({
	@NamedQuery(
			name="findOneCategoryByName", 
			query="select c from Category c where c.name = ?1")
})
public class CategoryEntity extends Category {

	@Id
	@Column(name="category_id")
	private Integer id;
	private String name;
	@ManyToMany
	@JoinTable(
		name="film_category",
		joinColumns=@JoinColumn(name="category_id"),
		inverseJoinColumns=@JoinColumn(name="film_id")
	)
	private List<FilmEntity> films;
	
	public String getName() {
		return name;
	}
}
