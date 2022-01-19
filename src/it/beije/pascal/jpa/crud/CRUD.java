package it.beije.pascal.jpa.crud;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import it.beije.pascal.Contatto;
import it.beije.pascal.jpa.util.EntityManagerProvider;

public class CRUD {
	public static void main(String[] args) {
		//read();
		//delete(11150);
		//update(11166);
		save();

	}

	private static void read() {
		EntityManager entityManager = EntityManagerProvider.getEntityManager();
		// JPQL
		String jpql = "SELECT c FROM Contatto AS c";
		Query query = entityManager.createQuery(jpql);
		List<Contatto> contatti = query.getResultList();
		entityManager.close();
		
		for (Contatto contatto : contatti) {
			System.out.println(contatto);
		}
		
		
	}
	
	private static void delete(int id) {
		EntityManager entityManger = EntityManagerProvider.getEntityManager();
		EntityTransaction transaction = entityManger.getTransaction();
		transaction.begin();
		
		// JPQL
		String jpql = "SELECT c FROM Contatto AS c WHERE id = :id";
		Query query = entityManger.createQuery(jpql);
		query.setParameter("id", id);		
		//Contatto contatto = (Contatto) query.getResultList().get(0);
		Contatto contatto = (Contatto)query.getSingleResult();
		System.out.println(contatto);
		entityManger.remove(contatto);
		transaction.commit();
		entityManger.close();
		
	}
	
	private static void update(int id) {
		EntityManager entityManager = EntityManagerProvider.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		// JPQL
		String jpql = "SELECT c FROM Contatto AS c WHERE id = :id";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("id", id);
		Contatto contatto = (Contatto) query.getSingleResult();
		contatto.setCognome("modificato");
		contatto.setNome("modificato");
		contatto.setTelefono("modificato");
		contatto.setEmail("modificato");
		contatto.setNote("modificato");
		// Stessa funzionalità del save di session
		entityManager.persist(contatto);
		transaction.commit();
		entityManager.close();
	}
	
	private static void save() {
		Contatto contatto = new Contatto("nuovo", "nuovo", "nuovo", "nuovo", "nuovo");
		EntityManager entityManger = EntityManagerProvider.getEntityManager();
		EntityTransaction transaction = entityManger.getTransaction();
		transaction.begin();
		
		entityManger.persist(contatto);
		
		transaction.commit();
		entityManger.close();
	}

}
