package it.beije.pascal.JPA;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.transaction.Transactional;



import it.beije.pascal.rubrica.Contatto;
import it.beije.pascal.rubrica.EntityManagerProvider;

public class JPAConnection {
	public static final String JPA_DUPLICATES = "SELECT {c1.*}, {c2.*}  FROM Contatti c1, Contatti c2 WHERE c1.cognome = c2.cognome;";
	//public static final String DELETE_CONTACT = "DELETE FROM contatti WHERE (id = ?)";
	public static final String JPA_SELECT = "UPDATE Contatti c SET cognome= :cognome, nome = :nome, telefono = :telefono, email = :email, note= :note WHERE (c.id = :id)";
	
	static EntityManager entityManager = EntityManagerProvider.getEntityManager();
	
	public static void main(String[] args) {
		deleteAll();
	}
	
	public void Insert(Contatto c) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(c);
		transaction.commit();
	}
	
	
	public List<Contatto> SelectAll() {
		Query query = entityManager.createQuery("SELECT c FROM Contatto as c");//SELECT * FROM contatti
		List<Contatto> listcontatti = query.getResultList();
		return listcontatti;
	}
	
	public List<Contatto> findDuplicateContatti(List<Contatto> listcont) {
		List<Contatto> duplicatecont = new ArrayList<Contatto>();
		Query query = entityManager.createQuery(JPA_DUPLICATES);
		duplicatecont = query.getResultList();
		return duplicatecont;
	}
	
	public void UpdateContatto(Contatto c) {
		//cerco contatto(implementare ricerca)
		
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Contatto modifiedContatto = new Contatto();
		modifiedContatto = c;
		int updatecontatto = entityManager.createQuery(JPA_SELECT).setParameter("cognome ", modifiedContatto.getCognome())
				.setParameter("nome ", modifiedContatto.getNome())
				.setParameter("telefono ", modifiedContatto.getTelefono())
				.setParameter("email ", modifiedContatto.getEmail())
				.setParameter("note ", modifiedContatto.getNote()).executeUpdate();
		//entityManager.persist(modifiedContatto);
		transaction.commit();
	}
	
	public static  void deleteAll() {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		String stringQuery = "DELETE FROM Contatto";
		Query query = entityManager.createQuery(stringQuery);
		query.executeUpdate();
		transaction.commit();
	}

}
