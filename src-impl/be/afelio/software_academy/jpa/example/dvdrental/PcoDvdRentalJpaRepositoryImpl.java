package be.afelio.software_academy.jpa.example.dvdrental;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import be.afelio.software_academy.jpa.example.dvdrental.beans.Film;
import be.afelio.software_academy.jpa.example.dvdrental.entities.ActorEntity;
import be.afelio.software_academy.jpa.example.dvdrental.entities.CategoryEntity;
import be.afelio.software_academy.jpa.example.dvdrental.entities.CustomerEntity;
import be.afelio.software_academy.jpa.example.dvdrental.entities.FilmEntity;
import be.afelio.software_academy.jpa.example.dvdrental.entities.LanguageEntity;
import be.afelio.software_academy.jpa.example.dvdrental.entities.StaffEntity;
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
		return countFilmsByLanguageNameWithCount(languageName);
		// return countFilmsByLanguageNameWithOneToMany(languageName);
	}

	@SuppressWarnings("unused")
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
	public FilmEntity findOneFilmByTitle(String title) {
		FilmEntity film = null;
		try {
			TypedQuery<FilmEntity> query
				// = em.createNamedQuery("findOneFilmByTitleLazy", FilmEntity.class);
				= em.createNamedQuery("findOneFilmByTitleEager", FilmEntity.class);
			query.setParameter(1, title);
			query.setMaxResults(1);
			film = query.getSingleResult();
		} catch(NoResultException ignored) {}
		return film;
	}

	@Override
	public boolean updateStaffPasswordByEmail(String email, String newPassword) {
		boolean updated = false;
		if (email != null && !email.isBlank()
				&& newPassword != null && !newPassword.isBlank()) {
			StaffEntity staff = findOneStaffByEmail(email);
			if (staff != null 
					&& findOneStaffByPassword(newPassword) == null) {
				staff.setPassword(newPassword);
				if (em.isJoinedToTransaction()) {
					em.persist(staff);
				} else {
					em.getTransaction().begin();
					em.persist(staff);
					em.getTransaction().commit();
				}
				updated = true;
			}
		}
		return updated;
	}

	private StaffEntity findOneStaffByEmail(String email) {
		StaffEntity staff = null;
		try {
			TypedQuery<StaffEntity> query 
				= em.createNamedQuery("findOneStaffByEmail", StaffEntity.class);
			query.setParameter(1, email);
			staff = query.getSingleResult();
		} catch(NoResultException ignored) {}
		return staff;
	}
	
	private StaffEntity findOneStaffByPassword(String password) {
		StaffEntity staff = null;
		try {
			TypedQuery<StaffEntity> query 
				= em.createNamedQuery("findOneStaffByPassword", StaffEntity.class);
			query.setParameter(1, password);
			staff = query.getSingleResult();
		} catch(NoResultException ignored) {}
		return staff;
	}
	
	@Override
	public boolean deleteCategoryByName(String name) {
		boolean deleted = false;
		if (name != null && !name.isBlank()) {
			CategoryEntity category = findOneCategoryByName(name);
			if (category != null) {
				if (em.isJoinedToTransaction()) {
					em.remove(category);
				} else {
					em.getTransaction().begin();
					em.remove(category);
					em.getTransaction().commit();
				}
				deleted = true;
			}
		}
		return deleted;
	}

	private CategoryEntity findOneCategoryByName(String name) {
		CategoryEntity category = null;
		try {
			TypedQuery<CategoryEntity> query 
				= em.createNamedQuery("findOneCategoryByName", CategoryEntity.class);
			query.setParameter(1, name);
			category = query.getSingleResult();
		} catch(NoResultException ignored) {}
		return category;
	}
	
	@Override
	public void createAndStoreNewActor(String firstName, String lastName) 
			throws DuplicatedActorException {
		if (firstName != null && !firstName.isBlank()
				&& lastName != null && !lastName.isBlank()) {
			if (findOneActorByFirstnameAndName(firstName, lastName) != null) {
				throw new DuplicatedActorException();
			}
			ActorEntity actor = new ActorEntity();
			actor.setFirstname(firstName);
			actor.setLastname(lastName);
			
			if (em.isJoinedToTransaction()) {
				em.persist(actor);
			} else {
				em.getTransaction().begin();
				em.persist(actor);
				em.getTransaction().commit();
			}
		}
		
	}

	private ActorEntity findOneActorByFirstnameAndName(String firstName, String lastName) {
		ActorEntity actor = null;
		try {
			TypedQuery<ActorEntity> query 
				= em.createNamedQuery("findOneActorByFirstnameAndName", ActorEntity.class);
			query.setParameter("littlename", firstName);
			query.setParameter("name", lastName);
			actor = query.getSingleResult();
		} catch(NoResultException ignored) {}
		return actor;
	}
	
	@Override
	public Film createAndStoreNewFilm(String title, String description, int releaseYear, String languageName,
			short length, List<Integer> actorIds) {
		// TODO Auto-generated method stub
		return null;
	}

}
