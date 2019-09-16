package be.afelio.software_academy.jpa.example.dvdrental;

import be.afelio.software_academy.jpa.example.dvdrental.DvdRentalJpaRepository;
import be.afelio.software_academy.jpa.example.dvdrental.exceptions.DuplicatedActorException;
import be.afelio.software_academy.jpa.utils.DvdRentalTestUtils;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class _07_TestCreateAndStoreNewActor {

	DvdRentalTestUtils dvdRentalTestUtils;

	@BeforeEach
	void init() {
		dvdRentalTestUtils = new DvdRentalTestUtils(Factory.getDatabaseUrl(), Factory.getDatabaseUser(),
				Factory.getDatabasePassword());
	}

	@Test
	void testNotDuplicatedActor() {
		DvdRentalJpaRepository repository = Factory.createDvdRentalJdbcRepository();
		assertNotNull(repository);

		try {
			repository.createAndStoreNewActor("Betty", "Boop");
			assertNotNull(dvdRentalTestUtils.findOneActorIdByFirstnameAndName("Betty", "Boop"));
		} catch(Exception e) {
			throw e;
		} finally {
			dvdRentalTestUtils.deleteActorByFirstnameAndName("Betty", "Boop");
		}
	}

	@Test
	void testDuplicatedActor() {
		DvdRentalJpaRepository repository = Factory.createDvdRentalJdbcRepository();
		assertNotNull(repository);
		assertThrows(DuplicatedActorException.class, () -> { repository.createAndStoreNewActor("Penelope", "Guiness"); });
	}

	@Test
	void testBlankFirstname() {
		DvdRentalJpaRepository repository = Factory.createDvdRentalJdbcRepository();
		assertNotNull(repository);
		try {
			repository.createAndStoreNewActor("", "Boop");
			assertNull(dvdRentalTestUtils.findOneActorIdByFirstnameAndName("", "Boop"));
		} catch(Exception e) {
			throw e;
		} finally {
			dvdRentalTestUtils.deleteActorByFirstnameAndName("", "Boop");
		}
	}

	@Test
	void testBlankName() {
		DvdRentalJpaRepository repository = Factory.createDvdRentalJdbcRepository();
		assertNotNull(repository);
		try {
			repository.createAndStoreNewActor("Betty", "");
			assertNull(dvdRentalTestUtils.findOneActorIdByFirstnameAndName("Betty", ""));
		} catch(Exception e) {
			throw e;
		} finally {
			dvdRentalTestUtils.deleteActorByFirstnameAndName("Betty", "");
		}
	}

	@Test
	void testNullFirstname() {
		DvdRentalJpaRepository repository = Factory.createDvdRentalJdbcRepository();
		assertNotNull(repository);
		try {
			repository.createAndStoreNewActor(null, "Boop");
			assertNull(dvdRentalTestUtils.findOneActorIdByFirstnameAndName(null, "Boop"));
		} catch(Exception e) {
			throw e;
		} finally {
			dvdRentalTestUtils.deleteActorByFirstnameAndName(null, "Boop");
		}
	}

	@Test
	void testNullName() {
		DvdRentalJpaRepository repository = Factory.createDvdRentalJdbcRepository();
		assertNotNull(repository);
		try {
			repository.createAndStoreNewActor("Betty", null);
			assertNull(dvdRentalTestUtils.findOneActorIdByFirstnameAndName("Betty", null));
		} catch(Exception e) {
			throw e;
		} finally {
			dvdRentalTestUtils.deleteActorByFirstnameAndName("Betty", null);
		}
	}
}
