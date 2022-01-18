package it.beije.pascal.rubrica;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

public class RubricaJPAManager {
	public List<Contatto> getRubrica(){
		EntityManager entityManager = EntityManagerProvider.getEntityManager();
		
		Query query = entityManager.createQuery("SELECT c FROM Contatto as c");
		
		List<Contatto> contatti = query.getResultList();
		
		entityManager.close();
		return contatti;
	}
	
	public List<Contatto> getRubricaOrderNome(){
		EntityManager entityManager = EntityManagerProvider.getEntityManager();
		
		Query query = entityManager.createQuery("SELECT c FROM Contatto as c ORDER BY nome");
		
		List<Contatto> contatti = query.getResultList();
		
		entityManager.close();
		return contatti;
	}
	
	public List<Contatto> getRubricaOrderCognome(){
		EntityManager entityManager = EntityManagerProvider.getEntityManager();
		
		Query query = entityManager.createQuery("SELECT c FROM Contatto as c ORDER BY cognome");
		
		List<Contatto> contatti = query.getResultList();
		
		entityManager.close();
		return contatti;
	}
	
	public Contatto cercaContatto(Contatto c){
		EntityManager entityManager = EntityManagerProvider.getEntityManager();
		
		Query query = entityManager.createQuery("SELECT c FROM Contatto as c WHERE cognome = '" + c.getCognome()
				                                                              + "' AND nome = '" + c.getNome()
				                                                              + "' AND telefono = '" + c.getTelefono()
				                                                              + "' AND email = '" + c.getEmail()
				                                                              + "' AND note = '" + c.getNote() + "'");
		
		List<Contatto> appoggio = query.getResultList();
		Contatto risultato = appoggio.get(0);
		
		entityManager.close();
		return risultato;
	}
	
	public void inserisciContatto(Contatto c) throws Exception {
		EntityManager entityManager = EntityManagerProvider.getEntityManager();
		
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		entityManager.persist(c);
		
		transaction.commit();
		entityManager.close();
	}
	
	public void cancellaContatto(Contatto c) throws Exception {
		EntityManager entityManager = EntityManagerProvider.getEntityManager();
		
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		entityManager.remove(c);
		
		transaction.commit();
		entityManager.close();
		
	}
	
	public void updateContact(int Id, String column, String newValue) {
		EntityManager entityManager = EntityManagerProvider.getEntityManager();
		
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();

        List<Contatto> contatti = getRubrica();
        Contatto contattoAggiornato = null;

        for(Contatto cont : contatti) {
            if(cont.getId() == Id) {
                contattoAggiornato = cont;
            }
        }

        switch(column) {
        case "nome": {
            contattoAggiornato.setNome(newValue);
            break;
            }
        case "cognome": {
            contattoAggiornato.setCognome(newValue);
            break;
            }
        case "telefono": {
            contattoAggiornato.setTelefono(newValue);
            break;
            }
        case "email": {
            contattoAggiornato.setEmail(newValue);
            break;
            }
        case "note": {
            contattoAggiornato.setNote(newValue);
            break;
            }
        }

        entityManager.persist(contattoAggiornato);
        transaction.commit();
        entityManager.close();
    }
	
	public List<Contatto> trovaContattiDup() {
		EntityManager entityManager = EntityManagerProvider.getEntityManager();
		
		Query query = entityManager.createQuery("SELECT c FROM  Contatto AS c GROUP BY c.nome, c.cognome, c.telefono, c.email, c.note HAVING COUNT(c.id) > 1");
		
		List<Contatto> dup = query.getResultList();
		
		return dup;
	}
	
	public void unisciContattiDup() {
		List<Contatto> duplicati = new ArrayList<>();
		
		
	}

}
