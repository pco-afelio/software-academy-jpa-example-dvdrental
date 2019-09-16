package be.afelio.software_academy.jpa.example.dvdrental;

import be.afelio.software_academy.jpa.example.dvdrental.DvdRentalJpaRepository;

public class Factory {

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

    public static DvdRentalJpaRepository createDvdRentalJdbcRepository() {
    	// TODO
    	return null;
    }
}
