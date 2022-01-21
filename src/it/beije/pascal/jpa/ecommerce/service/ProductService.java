package it.beije.pascal.jpa.ecommerce.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import it.beije.pascal.jpa.ecommerce.bean.Product;
import it.beije.pascal.jpa.rubrica.bean.Contatto;
import it.beije.pascal.jpa.util.EcommerceEntityProvider;

public class ProductService {
	
	private ProductService() {
		
	}
	
	public static List<Product> getProducts() {
		String jpql = "SELECT p FROM Product AS p";
		EntityManager entityManager = EcommerceEntityProvider.getEntityManager();
		Query query = entityManager.createQuery(jpql);
		List<Product> products = query.getResultList();
		return products;
	}
	
	
}
