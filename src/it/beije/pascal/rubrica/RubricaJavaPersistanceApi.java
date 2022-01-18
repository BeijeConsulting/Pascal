package it.beije.pascal.rubrica;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

public class RubricaJavaPersistanceApi {
	
	//SELECT
	public List<Contatto> listContacts() {
		EntityManager entityManager = EntityManagerProvider.getEntityManager();
		
		Query query = entityManager.createQuery("SELECT c FROM Contatto as c");
		@SuppressWarnings("unchecked")
		List<Contatto> contatti = query.getResultList();
		
		return contatti;
	}
	
	//INSERT
	public void newContact(String cognome, String nome, String tel, String email, String note) {
		EntityManager entityManager = EntityManagerProvider.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		Contatto contatto = new Contatto();
		contatto.setNome(nome);
		contatto.setCognome(cognome);
		contatto.setTelefono(tel);
		contatto.setEmail(email);
		contatto.setNote(note);
		
		entityManager.persist(contatto);
		transaction.commit();
		entityManager.close();
	}
	
	//UPDATE
	public void updateContact(int Id, String colonna, String nuovoValore) {
		EntityManager entityManager = EntityManagerProvider.getEntityManager();
		
		Contatto contatto = entityManager.find(Contatto.class, Integer.valueOf(Id));
		
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		switch(colonna) {
		case "nome": {
			contatto.setNome(nuovoValore);
			break;
			}
		case "cognome": {
			contatto.setCognome(nuovoValore);
			break;
			}
		case "telefono": {
			contatto.setTelefono(nuovoValore);
			break;
			}
		case "email": {
			contatto.setEmail(nuovoValore);
			break;
			}
		case "note": {
			contatto.setNote(nuovoValore);
			break;
			}
		}
		
		entityManager.persist(contatto);
		transaction.commit();
		entityManager.close();
	}
	
	//DELETE
	public void deleteContact(int Id) {
		EntityManager entityManager = EntityManagerProvider.getEntityManager();
		
		Contatto contatto = entityManager.find(Contatto.class, Integer.valueOf(Id));
		
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		entityManager.remove(contatto);
		transaction.commit();
		entityManager.close();
	}
	
	public static void main(String[] args) {
		RubricaJavaPersistanceApi r = new RubricaJavaPersistanceApi();
		List<Contatto> contatti = r.listContacts();
		for(Contatto c : contatti) {
			System.out.println(c.getNome());
		}
	}
	
}
