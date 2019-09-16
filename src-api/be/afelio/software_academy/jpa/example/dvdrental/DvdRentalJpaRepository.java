package be.afelio.software_academy.jpa.example.dvdrental;

import java.util.List;

import be.afelio.software_academy.jpa.example.dvdrental.beans.Actor;
import be.afelio.software_academy.jpa.example.dvdrental.beans.Customer;
import be.afelio.software_academy.jpa.example.dvdrental.beans.Film;
import be.afelio.software_academy.jpa.example.dvdrental.exceptions.DuplicatedActorException;

public interface DvdRentalJpaRepository {

    Customer findOneCustomerByCustomerFirstNameAndName(String firstName, String name);
    List<? extends Actor> findAllActorsByActorFirstNameIgnoreCase(String firstName);
    Film findOneFilmByTitle(String title);
    long countFilmsByLanguageName(String languageName);
    boolean updateStaffPasswordByEmail(String email, String newPassword);
    boolean deleteCategoryByName(String name);
    void createAndStoreNewActor(String firstName, String lastName) throws DuplicatedActorException;
    Film createAndStoreNewFilm(String title, String description, int releaseYear, String languageName,
                               short length, List<Integer> actorIds);
}
