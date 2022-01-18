package it.beije.pascal.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import it.beije.pascal.rubrica.Contatto;


public class RubricaHBM {

	public static void main(String[] args) {

		Configuration configuration = new Configuration().configure()
				.addAnnotatedClass(Contatto.class);			
				//.addAnnotatedClass(AltraClasse.class)				
				
		
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		
		Session session = sessionFactory.openSession();
		
		System.out.println("session is open ? " + session.isOpen());
		
		//HQL
		Query<Contatto> query = session.createQuery("SELECT c FROM Contatto as c");//SELECT * FROM contatti
		List<Contatto> contatti = query.getResultList();
		
		Contatto contatto = null;
		for (Contatto c : contatti) {
			System.out.println(c);
			if (c.getId() == 15) contatto = c;
		}

//		Transaction transaction = session.beginTransaction();

//		Transaction transaction = session.getTransaction();
//		transaction.begin();
		
		//INSERT
//		Contatto newContatto = new Contatto();
//		newContatto.setId(13);
//		newContatto.setCognome("Rosario");
//		newContatto.setNome("Chinetti");
//		newContatto.setEmail("r.chinetti@beije.it");
//		System.out.println("contatto PRE : " + newContatto);
//		vv
//		session.save(newContatto);
//		
//		System.out.println("contatto POST : " + newContatto);
		
		
//		//UPDATE
//		System.out.println("modifico : " + contatto);
//		contatto.setId(20);
//		contatto.setNote("queste sono le note");
//		contatto.setNome("Roberto");
//		session.save(contatto);
//		System.out.println("contatto POST update : " + contatto);

		
//		//DELETE
//		session.remove(contatto);
		
//		transaction.commit();
		
		session.close();
	}

}
