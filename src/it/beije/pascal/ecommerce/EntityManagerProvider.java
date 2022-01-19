package it.beije.pascal.ecommerce;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerProvider  {
	
	private EntityManagerProvider() {}
	
	private static EntityManagerFactory entityManagerFactory = null;
	
	public static EntityManager getEntityManager() {
		if (entityManagerFactory == null) {
			entityManagerFactory = Persistence.createEntityManagerFactory("pascal-ecommerce");
		}
		
		return entityManagerFactory.createEntityManager();
	}
	
	

}
