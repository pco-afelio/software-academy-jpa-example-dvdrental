package be.afelio.software_academy.jpa.example.dvdrental;

import be.afelio.software_academy.jpa.example.dvdrental.DvdRentalJpaRepository;
import be.afelio.software_academy.jpa.utils.DvdRentalTestUtils;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class _06_TestDeleteCategoryByName {

	DvdRentalTestUtils dvdRentalTestUtils;

	@BeforeEach
	void init() {
		dvdRentalTestUtils = new DvdRentalTestUtils(Factory.getDatabaseUrl(), Factory.getDatabaseUser(),
				Factory.getDatabasePassword());
	}

	@Test
	void testExistingCategoryName() {
		String categoryName = "Zoology";
		String filmTitle = "Arachnophobia Rollercoaster";

		DvdRentalJpaRepository repository = Factory.createDvdRentalJdbcRepository();
		assertNotNull(repository);
		try {
			dvdRentalTestUtils.createCategoryForFilm(categoryName, filmTitle);
			assertTrue(repository.deleteCategoryByName("Zoology"));
			assertNull(dvdRentalTestUtils.findCategoryByName(categoryName));
		} catch(Exception e) {
			throw e;
		} finally {
			dvdRentalTestUtils.deleteCategory(categoryName);
		}
	}

	@Test
	void testNonExistingCategoryName() {
		DvdRentalJpaRepository repository = Factory.createDvdRentalJdbcRepository();
		assertNotNull(repository);
		assertFalse(repository.deleteCategoryByName("JeNeSuisPasDansLaDb"));
	}

	@Test
	void testNullCategoryName() {
		DvdRentalJpaRepository repository = Factory.createDvdRentalJdbcRepository();
		assertNotNull(repository);
		assertFalse(repository.deleteCategoryByName(null));
	}
}
