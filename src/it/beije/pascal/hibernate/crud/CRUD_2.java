package it.beije.pascal.hibernate.crud;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import it.beije.pascal.bean.Contatto;
import it.beije.pascal.hibernate.util.SessionProvider;

public class CRUD_2 {

	public static void main(String[] args) {
		update();

	}
	

	private static void read() {
		// Creazione oggetti per lavorare con il database
		Configuration configuration = new Configuration().configure().addAnnotatedClass(Contatto.class);
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		String sql = "SELECT c FROM Contatto AS c";

		Query<Contatto> query = session.createQuery(sql);
		List<Contatto> contatti = query.getResultList();

		for (Contatto contatto : contatti) {
			System.out.println(contatto);
		}
	}
	
	private static void save() {
		Contatto contatto = new Contatto("nuovo", "nuovo", "nuovo", "nuovo", "nuovo");
		Session session = SessionProvider.getInstance().getSession();
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
		Session session = SessionProvider.getInstance().getSession();
		Transaction transaction = session.beginTransaction();		
		String sql = "UPDATE Contatto SET nome = 'modificato' WHERE id = '11151' ";
		Query<Contatto> query = session.createQuery(sql);
		query.executeUpdate();
		transaction.commit();
	}

}
















