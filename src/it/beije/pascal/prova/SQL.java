package it.beije.pascal.prova;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


public class SQL {
	
	public static void main(String[] args) {
		Persona persona = new Persona("usernrfgsgsegrsame","password");
		save(persona);
	}
	
	
	public static void save(Persona persona) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persona");
		EntityManager entityManger = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = entityManger.getTransaction();
		transaction.begin();
		
		entityManger.persist(persona);
		transaction.commit();
		entityManger.close();
		
		
	}

}
