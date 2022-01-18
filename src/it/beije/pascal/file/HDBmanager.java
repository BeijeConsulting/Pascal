package it.beije.pascal.file;

import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import it.beije.pascal.rubrica.Contatto;

public class HDBmanager {

	private static Session initConnection() {
		Configuration configuration = new Configuration().configure().addAnnotatedClass(Contatto.class);
		// .addAnnotatedClass(AltraClasse.class)

		SessionFactory sessionFactory = configuration.buildSessionFactory();

		Session session = sessionFactory.openSession();

		return session;
	}

	public static void insertHDB(Contatto c) {
		Session session = initConnection();

		Transaction transaction = session.beginTransaction();

		// Transaction transaction = session.getTransaction();
		// transaction.begin();

		// INSERT
		Contatto newContatto = new Contatto();
		newContatto.setCognome(c.getCognome());
		newContatto.setNome(c.getNome());
		newContatto.setEmail(c.getEmail());
		newContatto.setTelefono(c.getTelefono());
		newContatto.setNote(c.getNote());

		session.save(newContatto);
		transaction.commit();
		session.close();

	}

	public static void deleteHDB(int id) {
		Session session = initConnection();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		Query<Contatto> query = session.createQuery("SELECT c FROM Contatto AS c");
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
		session.remove(toRemove);
		transaction.commit();
		session.close();
	}

	public static List<Contatto> sortCategoriaHDB(String Categoria) {
		Session session = initConnection();

		Query<Contatto> query = session.createQuery("SELECT c FROM Contatto as c ORDER BY " + Categoria + " ASC");
		List<Contatto> contatti = query.getResultList();

		return contatti;
	}

	public static List<Contatto> findHDB(String s, String categoria) {
		Session session = initConnection();

		Query<Contatto> query = session
				.createQuery("SELECT c FROM Contatto as c WHERE c." + categoria + " = '" + s + "'");
		List<Contatto> contatti = query.getResultList();

		return contatti;
	}
	
	
	public static void updateContattoHDB(int id, String categoria, String val) {
		Session session = initConnection();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		Query<Contatto> query = session.createQuery("SELECT c FROM Contatto as c");
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
		
		session.save(updateContatto);
		transaction.commit();
		session.close();
	}

}
