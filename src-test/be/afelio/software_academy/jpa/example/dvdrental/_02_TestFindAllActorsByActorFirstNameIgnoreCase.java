package be.afelio.software_academy.jpa.example.dvdrental;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.*;

import be.afelio.software_academy.jpa.example.dvdrental.beans.Actor;

class _02_TestFindAllActorsByActorFirstNameIgnoreCase {

	@Test
	void testExistingFirstName() {
		DvdRentalJpaRepository repository = Factory.createDvdRentalJdbcRepository();
		assertNotNull(repository);

		List<? extends Actor> expected = createActors();
		List<? extends Actor> actual = repository.findAllActorsByActorFirstNameIgnoreCase("Christian");

		assertNotNull(actual);
		Collections.sort(actual, Comparator.comparing(a -> a.getLastname()));

		assertEquals(expected, actual);
	}

	@Test
	void testExistingFirstNameCaseSensitive() {
		DvdRentalJpaRepository repository = Factory.createDvdRentalJdbcRepository();
		assertNotNull(repository);

		List<? extends Actor> expected = createActors();
		List<? extends Actor> actual = repository.findAllActorsByActorFirstNameIgnoreCase("cHrIsTiAn");

		assertNotNull(actual);
		Collections.sort(actual, Comparator.comparing(a -> a.getLastname()));

		assertEquals(expected, actual);
	}

	@Test
	void testNonExistingFirstName() {
		DvdRentalJpaRepository repository = Factory.createDvdRentalJdbcRepository();
		assertNotNull(repository);

		List<? extends Actor> actual = repository.findAllActorsByActorFirstNameIgnoreCase("xxx");

		assertNotNull(actual);
		assertEquals(0, actual.size());
	}

	@Test
	void testNullFirstName() {
		DvdRentalJpaRepository repository = Factory.createDvdRentalJdbcRepository();
		assertNotNull(repository);

		List<? extends Actor> actual = repository.findAllActorsByActorFirstNameIgnoreCase(null);

		assertNotNull(actual);
		assertEquals(0, actual.size());
	}
	
	private List<Actor> createActors() {
		List<Actor> list = new ArrayList<>();
		list.add(createActor( "Christian", "Akroyd"));
		list.add(createActor( "Christian", "Gable"));
		list.add(createActor( "Christian", "Neeson"));
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
}
