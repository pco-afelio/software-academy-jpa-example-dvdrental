package be.afelio.software_academy.jpa.example.dvdrental;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import be.afelio.software_academy.jpa.example.dvdrental.beans.Film;
import be.afelio.software_academy.jpa.example.dvdrental.entities.ActorEntity;
import be.afelio.software_academy.jpa.example.dvdrental.entities.CustomerEntity;
import be.afelio.software_academy.jpa.example.dvdrental.entities.LanguageEntity;
import be.afelio.software_academy.jpa.example.dvdrental.exceptions.DuplicatedActorException;

public class PcoDvdRentalJpaRepositoryImpl implements DvdRentalJpaRepository {

	private EntityManager em;
	
	public PcoDvdRentalJpaRepositoryImpl(EntityManager em) {
		super();
		this.em = em;
	}

	@Override
	public CustomerEntity findOneCustomerByCustomerFirstNameAndName(String firstName, String name) {
		CustomerEntity customer = null;
		
		if (firstName != null && !firstName.isBlank()
				&& name != null && !name.isBlank()) {
		
			String jpql = "select c from CustomerEntity c "
					+ "where c.firstname = ?1 and c.lastname = ?2";
			
			TypedQuery<CustomerEntity> query
				= em.createQuery(jpql, CustomerEntity.class);
			
			query.setParameter(1, firstName);
			query.setParameter(2, name);
			
			try {
				customer = query.getSingleResult();
			} catch(NoResultException ignored) {}
		}
		
		return customer;
	}

	@Override
	public List<ActorEntity> findAllActorsByActorFirstNameIgnoreCase(String firstName) {
		List<ActorEntity> list = null;
		
		String jpql = "select c from Comedian c "
				+ "where lower(c.firstname) = lower(?1)";
		
		TypedQuery<ActorEntity> query
			= em.createQuery(jpql, ActorEntity.class);
		
		query.setParameter(1, firstName);
		
		list = query.getResultList();
		
		return list;
	}

	@Override
	public long countFilmsByLanguageName(String languageName) {
		return countFilmsByLanguageNameWithOneToMany(languageName);
	}

	private long countFilmsByLanguageNameWithOneToMany(String languageName) {
		long count = 0L;
		TypedQuery<LanguageEntity> query
			= em.createNamedQuery("findOneLanguageByName", LanguageEntity.class);
		query.setParameter(1, languageName);
		try {
			LanguageEntity language = query.getSingleResult();
			count = language.getFilms().size();
		} catch(NoResultException ignored) {}
		return count;
	}
	
	private long countFilmsByLanguageNameWithCount(String languageName) {
		TypedQuery<Long> query
			= em.createNamedQuery("countFilmsByLanguageName", Long.class);
		query.setParameter(1, languageName);
		Long count = query.getSingleResult();
		return count;
	}
	
	@Override
	public Film findOneFilmByTitle(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateStaffPasswordByEmail(String email, String newPassword) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteCategoryByName(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void createAndStoreNewActor(String firstName, String lastName) throws DuplicatedActorException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Film createAndStoreNewFilm(String title, String description, int releaseYear, String languageName,
			short length, List<Integer> actorIds) {
		// TODO Auto-generated method stub
		return null;
	}

}
