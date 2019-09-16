package be.afelio.software_academy.jpa.example.dvdrental;

import org.junit.jupiter.api.Test;

import be.afelio.software_academy.jpa.example.dvdrental.DvdRentalJpaRepository;

import static org.junit.jupiter.api.Assertions.*;

class _03_TestCountFilmsByLanguageName {

	@Test
	void testExistingLanguage() {
		DvdRentalJpaRepository repository = Factory.createDvdRentalJpaRepository();
		assertNotNull(repository);

		long expected = 1000L;
		long actual = repository.countFilmsByLanguageName("English");
		
		assertEquals(expected, actual);
	}

	@Test
	void testNonExistingLanguage() {
		DvdRentalJpaRepository repository = Factory.createDvdRentalJpaRepository();
		assertNotNull(repository);

		long expected = 0L;
		long actual = repository.countFilmsByLanguageName("French");
		
		assertEquals(expected, actual);
	}

	@Test
	void testNullLanguage() {
		DvdRentalJpaRepository repository = Factory.createDvdRentalJpaRepository();
		assertNotNull(repository);

		long expected = 0L;
		long actual = repository.countFilmsByLanguageName(null);
		
		assertEquals(expected, actual);
	}
}
