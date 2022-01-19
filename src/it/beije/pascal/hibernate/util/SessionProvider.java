package it.beije.pascal.hibernate.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import it.beije.pascal.Contatto;
import it.beije.pascal.jdbc.util.DataSource;

public class SessionProvider {
	private Configuration configuration = new Configuration().configure().addAnnotatedClass(Contatto.class);
	private SessionFactory sessionFactory = configuration.buildSessionFactory();
	private Session session = sessionFactory.openSession();

	private static SessionProvider instance;

	public static SessionProvider getInstance() {
		if (instance == null) {
			instance = new SessionProvider();
		}
		return instance;
	}

	public Session getSession() {
		return session;
	}
}
