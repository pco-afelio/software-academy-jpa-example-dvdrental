package be.afelio.software_academy.jpa.example.dvdrental;

import be.afelio.software_academy.jpa.example.dvdrental.DvdRentalJpaRepository;
import be.afelio.software_academy.jpa.utils.DvdRentalTestUtils;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class _05_TestUpdateStaffPasswordByEmail {

	DvdRentalTestUtils dvdRentalTestUtils;

	@BeforeEach
	void init() {
		dvdRentalTestUtils = new DvdRentalTestUtils(Factory.getDatabaseUrl(), Factory.getDatabaseUser(),
				Factory.getDatabasePassword());
	}

	@AfterEach
	void reset() {
		dvdRentalTestUtils.updateStaffPasswordForEmail(
				"8cb2237d0679ca88db6464eac60da96345513964", "Jon.Stephens@sakilastaff.com");
	}

	@Test
	void testExistingEmail() {
		DvdRentalJpaRepository repository = Factory.createDvdRentalJdbcRepository();
		assertNotNull(repository);

		String expected = String.valueOf(System.currentTimeMillis());

		boolean updated = repository.updateStaffPasswordByEmail("Jon.Stephens@sakilastaff.com", expected);
		assertTrue(updated);

		String actual = dvdRentalTestUtils.findOneStaffPasswordByEmail("Jon.Stephens@sakilastaff.com");

		assertEquals(expected, actual);
	}

	@Test
	void testNonExistingEmail() {
		DvdRentalJpaRepository repository = Factory.createDvdRentalJdbcRepository();
		assertNotNull(repository);

		boolean updated = repository.updateStaffPasswordByEmail("je_n_existe_pas@nulle_part.com",
				String.valueOf(System.currentTimeMillis()));
		assertFalse(updated);
	}

	@Test
	void testNullEmail() {
		DvdRentalJpaRepository repository = Factory.createDvdRentalJdbcRepository();
		assertNotNull(repository);

		boolean updated = repository.updateStaffPasswordByEmail(null, String.valueOf(System.currentTimeMillis()));
		assertFalse(updated);
	}

	@Test
	void testNullPassword() {
		DvdRentalJpaRepository repository = Factory.createDvdRentalJdbcRepository();
		assertNotNull(repository);

		String expected = dvdRentalTestUtils.findOneStaffPasswordByEmail("Jon.Stephens@sakilastaff.com");

		boolean updated = repository.updateStaffPasswordByEmail("Jon.Stephens@sakilastaff.com", null);
		assertFalse(updated);

		String actual = dvdRentalTestUtils.findOneStaffPasswordByEmail("Jon.Stephens@sakilastaff.com");

		assertEquals(expected, actual);
	}

	@Test
	void testDuplicatePassword() {
		DvdRentalJpaRepository repository = Factory.createDvdRentalJdbcRepository();
		assertNotNull(repository);

		String expected = dvdRentalTestUtils.findOneStaffPasswordByEmail("Jon.Stephens@sakilastaff.com");
		String duplicate = dvdRentalTestUtils.findOneStaffPasswordByEmail("Mike.Hillyer@sakilastaff.com");

		boolean updated = repository.updateStaffPasswordByEmail("Jon.Stephens@sakilastaff.com", duplicate);
		assertFalse(updated);

		String actual = dvdRentalTestUtils.findOneStaffPasswordByEmail("Jon.Stephens@sakilastaff.com");

		assertEquals(expected, actual);
	}
}
