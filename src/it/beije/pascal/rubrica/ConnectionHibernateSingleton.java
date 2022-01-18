package it.beije.pascal.rubrica;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ConnectionHibernateSingleton {
	
	private static ConnectionHibernateSingleton instance;
	
	
	private ConnectionHibernateSingleton() {
		
	}
	
	public static ConnectionHibernateSingleton getInstance() {
		if (instance == null) {
			instance = new ConnectionHibernateSingleton();
		}
		return instance;
	}
	
	public Session openConnectionHibernate() {
		
		Configuration configuration = new Configuration().configure()
				.addAnnotatedClass(Contatto.class);			
				//.addAnnotatedClass(AltraClasse.class)				
				
		
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		
		Session session = sessionFactory.openSession();
		
		System.out.println("session is open ? " + session.isOpen());
		
		return session;
		
	}
	
	public void closeConnectionHibernate(Session session) {
		
		session.close();
		System.out.println("session is open ? " + session.isOpen());
		
	}

}
