package it.beije.pascal.ecommerce;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import it.beije.pascal.rubrica.Contatto;
import it.beije.pascal.rubrica.EntityManagerProvider;

public class ManagerDB {
	
	public static List<User> getUsers(){
		EntityManager entityManager = EntityManagerProvider.getEntityManager();
		
		Query query = entityManager.createQuery("SELECT u FROM User as u");
		
		List<User> users = query.getResultList();
		
		entityManager.close();
		return users;
	}
	
	public static List<Product> getProducts(){
		EntityManager entityManager = EntityManagerProvider.getEntityManager();
		
		Query query = entityManager.createQuery("SELECT p FROM Product as p");
		
		List<Product> products = query.getResultList();
		
		entityManager.close();
		return products;
	}
	
	public static void addUser(User newUser) {
		
		EntityManager entityManager = EntityManagerProvider.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		entityManager.persist(newUser);
		
		transaction.commit();
		entityManager.close();
		
	}
	
	public static void addProduct(Product newProduct) {
		
		EntityManager entityManager = EntityManagerProvider.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		entityManager.persist(newProduct);
		
		transaction.commit();
		entityManager.close();
		
	}
	
	public static User searchUser(User u){
		EntityManager entityManager = EntityManagerProvider.getEntityManager();
		
		Query query = entityManager.createQuery("SELECT u FROM User as u WHERE email = '" + u.getEmail()
				                                                              + "' AND name = '" + u.getName()
				                                                              + "' AND surname = '" + u.getSurname()
				                                                              + "' AND password = '" + u.getPassword() + "'");
		
		List<User> temp = query.getResultList();
		User result = temp.get(0);
		
		entityManager.close();
		return result;
	}
	
	public static Product searchProduct(Product p){
		EntityManager entityManager = EntityManagerProvider.getEntityManager();
		
		Query query = entityManager.createQuery("SELECT u FROM User as u WHERE name = '" + p.getName()
				                                                              + "' AND description = '" + p.getDescription()
				                                                              + "' AND price = '" + p.getPrice()
				                                                              + "' AND quantity = '" + p.getQuantity() + "'");
		
		List<Product> temp = query.getResultList();
		Product result = temp.get(0);
		
		entityManager.close();
		return result;
	}
	
	

}
