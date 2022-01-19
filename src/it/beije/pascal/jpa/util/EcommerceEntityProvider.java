package it.beije.pascal.jpa.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EcommerceEntityProvider {
	
	private EcommerceEntityProvider() {}
	
	private static EntityManagerFactory entityManagerFactory = null;
	
	public static EntityManager getEntityManager() {
		if (entityManagerFactory == null) {
			entityManagerFactory = Persistence.createEntityManagerFactory("ecommerce");
		}
		
		return entityManagerFactory.createEntityManager();
	}

}
