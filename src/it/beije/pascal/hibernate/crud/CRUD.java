package it.beije.pascal.hibernate.crud;

import java.util.List;

import javax.persistence.RollbackException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import it.beije.pascal.rubrica.Contatto;

public class CRUD {

	public static void main(String[] args) {
		// read();
		//save();
		update();
		//delete();

	}

	private static void read() {
		// Creazione oggetti per lavorare con il database
		Configuration configuration = new Configuration().configure().addAnnotatedClass(Contatto.class);
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = SessionSource.getInstance().getSession();
		String sql = "SELECT c FROM Contatto AS c";

		Query<Contatto> query = session.createQuery(sql);
		List<Contatto> contatti = query.getResultList();

		for (Contatto contatto : contatti) {
			System.out.println(contatto);
		}
	}

	private static void save() {
		Contatto contatto = new Contatto("nuovo", "nuovo", "nuovo", "nuovo", "nuovo");
		Session session = SessionSource.getInstance().getSession();
		// Transaction transaction = session.getTransaction();
		// transaction.begin();

		// Fa entrambi
		Transaction transaction = session.beginTransaction();
		System.out.println("Contatto pre: " + contatto);
		session.save(contatto);
		System.out.println("Contatto post: " + contatto);
		transaction.commit();		
	}

	private static void update() {
		Contatto contatto = new Contatto(11150,"modificato","modificato","modificato","modificato","modificato");
		//Contatto contatto = new Contatto(11150,"Corona","Emanuele","3335877155","emacorona@gmail.com","breve descrizione");
		Session session = SessionSource.getInstance().getSession();
		Transaction transaction = session.beginTransaction();		
		session.update(contatto);
		transaction.commit();

	}

	private static void delete() {
		Contatto contatto = new Contatto(11165,"nuovo", "nuovo", "nuovo", "nuovo", "nuovo");
		Session session = SessionSource.getInstance().getSession();
		Transaction transaction = session.beginTransaction();
		session.delete(contatto);
		transaction.commit();		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
