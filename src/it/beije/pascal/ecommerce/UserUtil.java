package it.beije.pascal.ecommerce;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import it.beije.pascal.ecommerce.bean.User;

public class UserUtil {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		login("m.rossi@email.com", "password");

	}
	
	public static User login(String email, String pass) {
		EntityManager em = EntityManagerProvider.getEntityManager();
		Query query =  em.createQuery("Select c from User as c where c.email = :email and password = :pass");
		query.setParameter("email", email).setParameter("pass", pass);
		try {
			User res = (User) query.getSingleResult();
			return res;
		}catch(NoResultException e) {
			em.close();
			throw e;
		}finally{
			em.close();
		}
	}

}
