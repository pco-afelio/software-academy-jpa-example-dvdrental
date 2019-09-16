package be.afelio.software_academy.jpa.example.dvdrental;

import org.junit.jupiter.api.Test;

import be.afelio.software_academy.jpa.example.dvdrental.DvdRentalJpaRepository;
import be.afelio.software_academy.jpa.example.dvdrental.beans.Customer;

import static org.junit.jupiter.api.Assertions.*;

class _01_TestFindOneCustomerEmailByCustomerFirstNameAndName {

	@Test
	void testExistingCustomer() {
		DvdRentalJpaRepository repository = Factory.createDvdRentalJpaRepository();
		assertNotNull(repository);

		Customer expected = createJaredEly();
		Customer actual = repository.findOneCustomerByCustomerFirstNameAndName("Jared", "Ely");

		assertEquals(expected, actual); // assertTrue(expected.equals(actual))
	}

	@Test
	void testNonExistingCustomer() {
		DvdRentalJpaRepository repository = Factory.createDvdRentalJpaRepository();
		assertNotNull(repository);

		Customer actual = repository.findOneCustomerByCustomerFirstNameAndName("xxx", "yyy");

		assertNull(actual);
	}

	@Test
	void testNonExistingCustomerFirstNameNull() {
		DvdRentalJpaRepository repository = Factory.createDvdRentalJpaRepository();
		assertNotNull(repository);

		Customer actual = repository.findOneCustomerByCustomerFirstNameAndName(null, "Ely");

		assertNull(actual);
	}

	@Test
	void testNonExistingCustomerNameNull() {
		DvdRentalJpaRepository repository = Factory.createDvdRentalJpaRepository();
		assertNotNull(repository);

		Customer actual = repository.findOneCustomerByCustomerFirstNameAndName("Jared", null);

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
