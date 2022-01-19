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
	
	public static User searchUser(String email, String password){
		User result = null;
		EntityManager entityManager = EntityManagerProvider.getEntityManager();
		
		Query query = entityManager.createQuery("SELECT u FROM User as u WHERE email = '" + email
				                                                     + "' AND password = '" + password + "'");
		
		List<User> temp = query.getResultList();

		try {
            
            result = temp.get(0);
            
        }catch(IndexOutOfBoundsException iOB) {
        	
        	result = null;
            
        }
		
        entityManager.close();
        
        return result;
	}
	
	public static Product searchProduct(String name) {
        
        Product result = null;
        EntityManager entityManager = EntityManagerProvider.getEntityManager();
        Query query = entityManager.createQuery(
                "SELECT u FROM Product as u WHERE name = '" + name + "'");
        List<Product> temp = query.getResultList();
        
        try {
            
            result = temp.get(0);
            
        }catch(IndexOutOfBoundsException iOB) {
            
            if(result == null) {
                
                System.out.println("Prodotto non presente");
            }
            
        }
        finally {
            
            entityManager.close();  
        }
        return result;
    }
    
    public static void updateQuantity(int newValue, String name) {
        
        Product find = searchProduct(name);
        
        EntityManager entityManager = EntityManagerProvider.getEntityManager();
        
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        
        Product update = entityManager.find(Product.class, find.getId());
        
        update.setQuantity(newValue);
        entityManager.persist(update);
        transaction.commit();
        entityManager.close();
    }

}
