package be.afelio.software_academy.jpa.example.dvdrental;

import org.junit.jupiter.api.Test;

import be.afelio.software_academy.jpa.example.dvdrental.DvdRentalJpaRepository;
import be.afelio.software_academy.jpa.example.dvdrental.beans.Customer;

import static org.junit.jupiter.api.Assertions.*;

class _01_TestFindOneCustomerEmailByCustomerFirstNameAndName {

	@Test
	void testExistingCustomer() {
		DvdRentalJpaRepository repository = Factory.createDvdRentalJdbcRepository();
		assertNotNull(repository);

		Customer expected = createJaredEly();
		Customer actual = repository.findOneCustomerEmailByCustomerFirstNameAndName("Jared", "Ely");

		assertEquals(expected, actual);
	}

	@Test
	void testNonExistingCustomer() {
		DvdRentalJpaRepository repository = Factory.createDvdRentalJdbcRepository();
		assertNotNull(repository);

		Customer actual = repository.findOneCustomerEmailByCustomerFirstNameAndName("xxx", "yyy");

		assertNull(actual);
	}

	@Test
	void testNonExistingCustomerFirstNameNull() {
		DvdRentalJpaRepository repository = Factory.createDvdRentalJdbcRepository();
		assertNotNull(repository);

		Customer actual = repository.findOneCustomerEmailByCustomerFirstNameAndName(null, "Ely");

		assertNull(actual);
	}

	@Test
	void testNonExistingCustomerNameNull() {
		DvdRentalJpaRepository repository = Factory.createDvdRentalJdbcRepository();
		assertNotNull(repository);

		Customer actual = repository.findOneCustomerEmailByCustomerFirstNameAndName("Jared", null);

		assertNull(actual);
	}
	
	private Customer createJaredEly() {
		return new Customer() {
			public String getLastname() {
				return "Ely";
			}
			public String getFirstname() {
				return "Jared";
			}
			public String getEmail() {
				return "jared.ely@sakilacustomer.org";
			}
		};
	}
}
