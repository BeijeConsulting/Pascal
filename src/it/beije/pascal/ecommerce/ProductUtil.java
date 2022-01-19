package it.beije.pascal.ecommerce;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import it.beije.pascal.ecommerce.bean.Product;

public class ProductUtil {
//	for testing
	public static void main(String[] args) {
		List<Product> mart = search("hammer");
		for(Product p : mart) {
			System.out.println(p);
		}
	}
	
	public static List<Product> search(String name){
		List<Product> res = null;
		EntityManager em = EntityManagerProvider.getEntityManager();
		Query query = em.createQuery("SELECT p FROM Product AS p WHERE p.name = :name ", Product.class);
		query.setParameter("name", name);
		try {
			res = query.getResultList();
			return res;
		} catch(NoResultException e) {
			em.close();
			throw e;
		}
		finally{
			em.close();
		}
	}
	
}
