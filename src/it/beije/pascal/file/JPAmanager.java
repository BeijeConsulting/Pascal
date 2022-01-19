package it.beije.pascal.file;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.omg.IOP.ENCODING_CDR_ENCAPS;

import it.beije.pascal.rubrica.Contatto;
import it.beije.pascal.rubrica.EntityManagerProvider;

public class JPAmanager {
	
	private static EntityManagerFactory entityManagerFactory = null;
	
	public static EntityManager getEntityManager() {
		if (entityManagerFactory == null) {
			entityManagerFactory = Persistence.createEntityManagerFactory("pascal-rubrica");
		}
		
		return entityManagerFactory.createEntityManager();
	}



	public static List<Contatto> sortCategoriaJPA(String Categoria) {
		EntityManager entityManager = EntityManagerProvider.getEntityManager();
	
		
		Query query = entityManager.createQuery("SELECT c FROM Contatto as c ORDER BY " + Categoria + " ASC");
		List<Contatto> contatti = query.getResultList();

		return contatti;
	}
	
	
	public static List<Contatto> findJPA(String s, String categoria) {
		EntityManager entityManager = EntityManagerProvider.getEntityManager();
	
		
		Query query = entityManager.createQuery("SELECT c FROM Contatto as c WHERE c." + categoria + " = '" + s + "'");
		List<Contatto> contatti = query.getResultList();
		
		return contatti;
	}

	
	public static void insertJPA(Contatto c) {
		EntityManager entityManager = EntityManagerProvider.getEntityManager();

		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

		// INSERT
		Contatto newContatto = new Contatto();
		newContatto.setCognome(c.getCognome());
		newContatto.setNome(c.getNome());
		newContatto.setEmail(c.getEmail());
		newContatto.setTelefono(c.getTelefono());
		newContatto.setNote(c.getNote());

		entityManager.persist(newContatto);
		transaction.commit();
		entityManager.close();

	}
	
	public static void updateContattoJPA(int id, String categoria, String val) {
		EntityManager entityManager = EntityManagerProvider.getEntityManager();

		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		
		Query query = entityManager.createQuery("SELECT c FROM Contatto as c");
		List<Contatto> contatti = query.getResultList();
		Contatto updateContatto = null;
		
		for (Contatto contatto : contatti) {
			if (contatto.getId() == id)
				updateContatto = contatto;
		}
		
		if (updateContatto == null) {
			System.out.println("nessun contatto con queste informazioni");
			return;
		}
		
		if(categoria.equals("cognome")) updateContatto.setCognome(val);
		if(categoria.equals("nome")) updateContatto.setNome(val);
		if(categoria.equals("telefono")) updateContatto.setTelefono(val);
		if(categoria.equals("email")) updateContatto.setEmail(val);
		if(categoria.equals("note")) updateContatto.setNote(val);
		
		entityManager.persist(updateContatto);
		transaction.commit();
		entityManager.close();
	}
	
	
	public static void deleteJPA(int id) {
		EntityManager entityManager = EntityManagerProvider.getEntityManager();

		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		Query query = entityManager.createQuery("SELECT c FROM Contatto AS c");
		List<Contatto> contatti = query.getResultList();
		Contatto toRemove = null;

		for (Contatto contatto : contatti) {
			if (contatto.getId() == id)
				toRemove = contatto;
		}
		if (toRemove == null) {
			System.out.println("nessun contatto con queste informazioni");
			return;
		}

		entityManager.remove(toRemove);
		transaction.commit();
		entityManager.close();
	}
	
}
