package it.beije.pascal.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import it.beije.pascal.jpa.rubrica.bean.Contatto;
import it.beije.pascal.jpa.util.RubricaEntityProvider;



public class RubricaJPA {

	public static void main(String[] args) {
		
//		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pascal-rubrica");
//		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityManager entityManager = RubricaEntityProvider.getEntityManager();
		
		//JPQL
		Query query = entityManager.createQuery("SELECT c FROM Contatto as c");
		//Query query = entityManager.createNativeQuery("SELECT * FROM contatti", Contatto.class);
		List<Contatto> contatti = query.getResultList();
		
		for (Contatto c : contatti) {
			System.out.println(c);
		}
		
//		Contatto contatto = entityManager.find(Contatto.class, Integer.valueOf(18));
//		System.out.println(contatto);

		
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
//		//INSERT
//		Contatto newContatto = new Contatto();
//		//newContatto.setId(30);
//		newContatto.setCognome("Brambilla");
//		newContatto.setNome("Mario");
//		newContatto.setEmail("m.brambilla@beije.it");
//		System.out.println("contatto PRE : " + newContatto);
//		entityManager.persist(newContatto);
//		System.out.println("contatto POST : " + newContatto);
		
//		//UPDATE
//		contatto.setCognome("Fumagalli");
//		contatto.setTelefono("43214342");
//		entityManager.persist(contatto);
		
//		//DELETE
//		entityManager.remove(contatto);
		
		transaction.commit();
//		transaction.rollback();
		
		entityManager.close();

	}

}
