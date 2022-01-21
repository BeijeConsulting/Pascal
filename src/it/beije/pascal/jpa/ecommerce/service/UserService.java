package it.beije.pascal.jpa.ecommerce.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import it.beije.pascal.jpa.ecommerce.Ecommerce;
import it.beije.pascal.jpa.ecommerce.bean.User;
import it.beije.pascal.jpa.util.EcommerceEntityProvider;

public class UserService {

	private UserService() {

	}

	public static void login(String email, String password) {
		String jpql = "SELECT u FROM User AS u WHERE email = :email";
		EntityManager entityManager = EcommerceEntityProvider.getEntityManager();
		User user = null;
		Query query = entityManager.createQuery(jpql);
		query.setParameter("email", email);
		try {
			user = (User) query.getSingleResult();
			if (password.equals(user.getPassword())) {
				// Metodo Menu dopo essersi loggati
				Ecommerce.launchEcommerce();
			} else {
				System.err.println("\nPassword sbagliata!");
			}

		} catch (NoResultException e) {
			System.err.println("\nUtente non registrato!");
		}
	}

	public static void addUser(User user) {
		EntityManager entityManager = EcommerceEntityProvider.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(user);
		transaction.commit();
		entityManager.close();
	}
}
