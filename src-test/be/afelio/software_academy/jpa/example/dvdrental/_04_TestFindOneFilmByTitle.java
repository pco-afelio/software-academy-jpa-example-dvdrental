package be.afelio.software_academy.jpa.example.dvdrental;

import org.junit.jupiter.api.*;

import be.afelio.software_academy.jpa.example.dvdrental.DvdRentalJpaRepository;
import be.afelio.software_academy.jpa.example.dvdrental.beans.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class _04_TestFindOneFilmByTitle {

	@Test
	void testExistingTitle() {
		DvdRentalJpaRepository repository = Factory.createDvdRentalJpaRepository();
		assertNotNull(repository);

		Film expected = createTestFilm("Bright Encounters",
				"A Fateful Yarn of a Lumberjack And a Feminist who must Conquer a Student in A Jet Boat",
				2006, 73,"English", createTestActors());
		Film actual = repository.findOneFilmByTitle("Bright Encounters");

		assertEquals(expected, actual);

		List<? extends Actor> list = actual.getActors();
		assertNotNull(list);
		list.sort(comparator);
		assertEquals(expected.getActors(), list);
	}

	@Test
	void testNonExistingTitle() {
		DvdRentalJpaRepository repository = Factory.createDvdRentalJpaRepository();
		assertNotNull(repository);

		Film actual = repository.findOneFilmByTitle("xxx");
		assertNull(actual);
	}

	@Test
	void testNullTitle() {
		DvdRentalJpaRepository repository = Factory.createDvdRentalJpaRepository();
		assertNotNull(repository);

		Film actual = repository.findOneFilmByTitle(null);
		assertNull(actual);
	}

	@Test
	void testExistingTitleWithoutActors() {
		DvdRentalJpaRepository repository = Factory.createDvdRentalJpaRepository();
		assertNotNull(repository);

		Film expected = createTestFilm("Drumline Cyclone",
				"A Insightful Panorama of a Monkey And a Sumo Wrestler who must Outrace a Mad Scientist in The Canadian Rockies",
				2006, 110, "English", Collections.emptyList());
		Film actual = repository.findOneFilmByTitle("Drumline Cyclone");

		assertEquals(expected, actual);
		assertEquals(expected.getActors(), actual.getActors());
	}

	private Film createTestFilm(String title, String description, int year, int length, String languageName, List<Actor> actors) {
		return new Film() {
			public Integer getYear() {
				return year;
			}
			public String getTitle() {
				return title;
			}
			public Integer getLength() {
				return length;
			}
			public Language getLanguage() {
				return new Language() {
					public String getName() {
						return languageName;
					}
				};
			}
			public String getDescription() {
				return description;
			}
			public List<? extends Actor> getActors() {
				return actors;
			}
		};
	}
	
	private List<Actor> createTestActors() {
		List<Actor> list = new ArrayList<>();
		list.add(createActor( "Albert", "Nolte"));
		list.add(createActor( "Cameron", "Zellweger"));
		list.add(createActor( "Meryl", "Allen"));
		list.add(createActor( "Renee", "Ball"));
		return list;
	}
	
	private Actor createActor(String firstname, String lastname) {
		return new Actor() {
			public String getLastname() {
				return lastname;
			}
			public String getFirstname() {
				return firstname;
			}
		};
	}
	
	Comparator<Actor> comparator = (Actor a, Actor b) -> {
		if (a == null) return -1;
		if (b == null) return 1;
		if (a.getFirstname() == null && b.getFirstname() != null) return -1;
		if (a.getFirstname() != null && b.getFirstname() == null) return 1;
		int compare = a.getFirstname().compareTo(b.getFirstname());
		if (compare != 0) return compare;
		if (a.getLastname() == null && b.getLastname() != null) return -1;
		if (a.getLastname() != null && b.getLastname() == null) return 1;
		return a.getLastname().compareTo(b.getLastname());
	};
}
