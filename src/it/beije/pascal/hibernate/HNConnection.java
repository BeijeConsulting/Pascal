package it.beije.pascal.hibernate;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import it.beije.pascal.rubrica.Contatto;

public class HNConnection {
	//public static final String INSERT_INTO_RUBRICA = "INSERT INTO contatti (cognome, nome, telefono, email, note) VALUES (?,?,?,?,?)";
	/*public static final String SELECT_DUPLICATES = "SELECT * FROM contatti C1, contatti C2"
			+ " WHERE C1.nome = C2.nome AND C1.cognome = C2.cognome;  ";*/
	public static final String HIBER_DUPLICATES = "SELECT {c1.*}, {c2.*}  FROM Contatti c1, Contatti c2 WHERE c1.cognome = c2.cognome)";
	public static final String DELETE_CONTACT = "DELETE FROM contatti WHERE (id = ?)";

	
	Configuration configuration = null;
	SessionFactory sessionFactory = null;
	Session session = null;
	
	public void startSession() {
	
		Configuration configuration = new Configuration().configure()
				.addAnnotatedClass(Contatto.class);			
				//.addAnnotatedClass(AltraClasse.class)				
			
	
		SessionFactory sessionFactory = configuration.buildSessionFactory();
	
		Session session = sessionFactory.openSession();
	}
	
	public void Insert(Contatto c) {
		Transaction transaction = session.beginTransaction();
		transaction.begin();
		session.save(c);
		transaction.commit();
	}
	
	
	public List<Contatto> SelectAll() {
		Query<Contatto> query = session.createQuery("SELECT c FROM Contatto as c");//SELECT * FROM contatti
		List<Contatto> listcontatti = query.getResultList();
		return listcontatti;
	}
	
	public List<Contatto> findDuplicateContatti(List<Contatto> listcont) {
		List<Contatto> duplicatecont = new ArrayList<Contatto>();
		Query<Contatto> query = session.createQuery(HIBER_DUPLICATES);
		//Transaction transaction = session.beginTransaction();
		//transaction.begin();
		duplicatecont = query.getResultList();
		return duplicatecont;
	}
	
	
	public void UpdateContatto(Contatto c) {
		//cerco contatto(implementare ricerca)
		
		Transaction transaction = session.beginTransaction();
		transaction.begin();
		Contatto modifiedContatto = new Contatto();
		modifiedContatto = c;
		session.save(modifiedContatto);
		transaction.commit();
	}
	
	
	
	
	
}
