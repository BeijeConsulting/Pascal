package it.beije.pascal.hibernate.crud;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import it.beije.pascal.rubrica.Contatto;
import it.beije.pascal.rubrica.jdbc.util.DataSource;

public class SessionSource {
	private Configuration configuration = new Configuration().configure().addAnnotatedClass(Contatto.class);
	private SessionFactory sessionFactory = configuration.buildSessionFactory();
	private Session session = sessionFactory.openSession();

	private static SessionSource instance;

	public static SessionSource getInstance() {
		if (instance == null) {
			instance = new SessionSource();
		}
		return instance;
	}

	public Session getSession() {
		return session;
	}
}
