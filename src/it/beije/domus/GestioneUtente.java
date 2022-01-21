package it.beije.domus;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import javax.persistence.Query;

public class GestioneUtente {
	
	//CREARE
	public static Utente createUser(int id, String email, String avatarUrl, String password, boolean spamCheck, boolean admin) {
		Utente ut = new Utente();
		ut.setId(id);
		ut.setEmail(email);
		ut.setAvatar_url(avatarUrl);
		ut.setPassword(password);
		ut.setSpam_check(spamCheck);
		ut.setAmministratore(admin);
		
		return ut;
	}
	
	//INSERIRE
	public static void addUser(Utente utente) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("domus");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(utente);
		transaction.commit();
		entityManager.close();
	}

	
	//CERCARE
	public static Utente lookForUser(int id) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("domus");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		String jpql = "SELECT c FROM Utente AS c WHERE id = :id";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("id", id);
		Utente utente = (Utente) query.getSingleResult();
		entityManager.close();
		
		return utente;
	}
	
	
	//ELIMINARE
	public static void delete(int id) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("domus");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		String jpql = "SELECT c FROM Utente AS c WHERE id = :id";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("id", id);
		Utente utente = (Utente) query.getSingleResult();
		entityManager.remove(utente);
		transaction.commit();
		entityManager.close();
	}
	
	public static void main(String...strings) {
		GestioneUtente.delete(1);
	}	
}
