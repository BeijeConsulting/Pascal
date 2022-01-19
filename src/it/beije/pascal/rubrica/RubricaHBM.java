package it.beije.pascal.rubrica;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javassist.bytecode.analysis.Type;


public class RubricaHBM implements DatabaseConnection{

	private Configuration configuration;
	private SessionFactory sessionFactory;
	private static RubricaHBM instance;


	private RubricaHBM() {
		this.configuration = new Configuration().configure().addAnnotatedClass(Contatto.class);
		this.sessionFactory = this.configuration.buildSessionFactory();
	}

	//	Singleton instancing
	public static RubricaHBM getInstance() {
		if(instance == null) {
			instance = new RubricaHBM();
			instance.init();
		}
		return instance;
	}

	private void init() {

	}

	public static void main(String[] args) {

//		RubricaHBM instance= getInstance();
		//		instance.cercaContattoNomeCognome("mario", "rossi");

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
		//		
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

	}


	@Override
	public List<Contatto> cercaContattoNomeCognome(String nome, String cognome) {
		Session session = sessionFactory.openSession();

		System.out.println("session is open ? " + session.isOpen());
		//HQL
		Query<Contatto> query = session.createQuery("SELECT c FROM Contatto as c WHERE nome = :nome AND cognome = :cognome");
		query.setParameter("nome", nome).setParameter("cognome", cognome);
		List<Contatto> contatti = query.getResultList();
		session.close();
		return contatti;
	}

	@Override
	public List<Contatto> cercaContatto(Contatto contatto) {
		// TODO test
		Session session = sessionFactory.openSession();
		
		Query<Contatto> query = session.createQuery("Select c FROM Contatto AS c WHERE c.nome = :nome , c.cognome = :cognome , c.telefono = :telefono , c.email = :email , c.note = :note")
				.setProperties(contatto);
		List<Contatto> risultati = query.getResultList();
		
		session.close();
		return risultati;
	}

	@Override
	public void inserisciContatto(Contatto contatto) {
		Session session = sessionFactory.openSession();
		session.save(contatto);

		System.out.println("contatto inserito : " + contatto);

		session.close();
	}

	@Override
	public void modificaContatto(int id, Contatto contatto) {		

		System.out.println("contatto PRE update : " + contatto);
		
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		Query<Contatto> query = session.createQuery("Select c FROM Contatto AS c WHERE c.id = :id")
				.setProperties(contatto);
		
		Contatto daModificare = query.getSingleResult();
		daModificare.setNome(contatto.getNome());
		daModificare.setCognome(contatto.getCognome());
		daModificare.setTelefono(contatto.getTelefono());
		daModificare.setEmail(contatto.getEmail());
		daModificare.setNote(contatto.getNote());
		                            
		session.save(daModificare);
		System.out.println("contatto POST update : " + daModificare);
		
		transaction.commit();
		session.close();
	}

	@Override
	public void eliminaContatto(int id) {
		// TODO test
		Session session = sessionFactory.openSession();
		Contatto contatto = null;
		Transaction transaction = session.beginTransaction();
		Query<Contatto> query = session.createQuery("Select c FROM Contatto AS c WHERE c.id = :id").setParameter("id", id);
		contatto = query.getSingleResult();
		session.delete(contatto);
		
		transaction.commit();
		session.close();
	}

	@Override
	public List<Contatto> listDuplicates() {
		List<Contatto> risultato;
		//TODO
		Session session = sessionFactory.openSession();
		Query<Contatto> query = session.createQuery("SELECT c FROM  Contatto AS c GROUP BY c.nome, c.cognome, c.telefono, c.email, c.note HAVING COUNT(c.id) > 1");
		risultato = query.getResultList();

		session.close();
		return risultato;
	}

	@Override
	public List<Contatto> listAllOrderedBy(String column, boolean ascendent) {
		List<Contatto> risultato;
		
		Session session = sessionFactory.openSession();
		Query<Contatto> query = session.createQuery("Select c FROM Contatto AS c ORDER BY c." + column + " " + (ascendent ? "ASC" : "DESC"));
		risultato = query.getResultList();

		session.close();
		return risultato;
	}

	@Override
	public void autoDeleteDuplicati() {
		// TODO Auto-generated method stub
		
	}


}
