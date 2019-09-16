package be.afelio.software_academy.jpa.example.dvdrental;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import be.afelio.software_academy.jpa.example.dvdrental.DvdRentalJpaRepository;

public class Factory {

    public static DvdRentalJpaRepository createDvdRentalJpaRepository() {
    	DvdRentalJpaRepository repository = null;
    	
    	EntityManagerFactory entityManagerFactory
    		= Persistence.createEntityManagerFactory("jpa-dvdrental");
    	EntityManager entityManager = entityManagerFactory.createEntityManager();
    	
    	repository = new PcoDvdRentalJpaRepositoryImpl(entityManager);
    	return repository;
    }
	
    static String databaseUrl = "jdbc:postgresql://localhost:5432/dvdrental";
    static String databaseUser = "postgres";
    static String databasePassword = "postgres";

    public static String getDatabaseUrl() {
        return databaseUrl;
    }

    public static String getDatabaseUser() {
        return databaseUser;
    }

    public static String getDatabasePassword() {
        return databasePassword;
    }
}
