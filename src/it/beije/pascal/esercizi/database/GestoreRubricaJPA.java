package it.beije.pascal.esercizi.database;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import it.beije.pascal.rubrica.Contatto;

public class GestoreRubricaJPA {

	public static void main(String[] args) {
		
		EntityManager em = EntityManagerProvider.getEntityManager();
//		leggiContatti(em);
//		
//		scriviContatto(em, "Marco", "Vela", "3200553186");
		
		

	}
	
	public static void leggiContatti(EntityManager entityManager) {
		EntityManager em = entityManager;
		
		Query q = em.createQuery("SELECT c FROM Contatto as c");
		List<Contatto> contatti = q.getResultList();
		
		for(Contatto c : contatti) {
			System.out.println(c);
		}
	}
	
	public static void scriviContatto(EntityManager entityManager, String nome, String cognome, String telefono) {
		EntityManager em = entityManager;
		
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		Contatto newContatto = new Contatto();
		newContatto.setNome(nome);
		newContatto.setCognome(cognome);
		newContatto.setTelefono(telefono);
		
		entityManager.persist(newContatto);
		
		transaction.commit();
	}
	
	

}
