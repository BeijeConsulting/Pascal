package it.beije.pascal.rubrica;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class RubricaHBMManager {
	public static void main(String[] args) {
		List<Contatto> prova = null;
		
	}
	
	private static Session getSession() {
		Configuration configuration = new Configuration().configure()
				.addAnnotatedClass(Contatto.class);			
		
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		
		Session session = sessionFactory.openSession();

		return session;
	}
	
	private static List<Contatto> getRubrica(){
		Session session = getSession();
		
		Query<Contatto> query = session.createQuery("SELECT c FROM Contatto as c");//SELECT * FROM contatti
		List<Contatto> contatti = query.getResultList();
		
		session.close();
		return contatti;
	}
	
	public List<Contatto> getRubricaOrderNome(){
		Session session = getSession();
		Query<Contatto> query = session.createQuery("SELECT c FROM Contatto as c ORDER BY nome");
		List<Contatto> contatti = query.getResultList();
		
		session.close();
		return contatti;
	}
	
	public List<Contatto> getRubricaOrderCognome(){
		Session session = getSession();
		Query<Contatto> query = session.createQuery("SELECT c FROM Contatto as c ORDER BY cognome");
		List<Contatto> contatti = query.getResultList();
		
		session.close();
		return contatti;
	}
	
	public static Contatto cercaContatto(Contatto c){
		Session session = getSession();
		Query<Contatto> query = session.createQuery("SELECT c FROM Contatto as c WHERE cognome = '" + c.getCognome()
				                                                              + "' AND nome = '" + c.getNome()
				                                                              + "' AND telefono = '" + c.getTelefono()
				                                                              + "' AND email = '" + c.getEmail()
				                                                              + "' AND note = '" + c.getNote() + "'");
		
		Contatto risultato = query.getSingleResult();
		
		session.close();
		return risultato;
	}
	
	public void inserisciContatto(Contatto c) throws Exception {
		Session session = getSession();
		
		Transaction transaction = session.beginTransaction();
		
		session.save(c);
		
		transaction.commit();
		session.close();
	}
	
	public void cancellaContatto(Contatto c) throws Exception {
		Session session = getSession();
		
		Transaction transaction = session.beginTransaction();
		
		session.remove(c);
		
		transaction.commit();
		session.close();
		
	}
	
	public void updateContact(int Id, String column, String newValue) {
        Session session = getSession();
        Transaction transaction = session.getTransaction();
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

        session.save(contattoAggiornato);
        transaction.commit();
        session.close();
    }
	
	public static List<Contatto> trovaContattiDup() {
		List<Contatto> dup = new ArrayList<>();
		
		List<Contatto> appoggio = getRubrica();
		
		int cont = 1;
		for(Contatto c : appoggio) {
			for(int i = cont; i < appoggio.size(); i++) {
				if(c.getCognome().equals(appoggio.get(i).getCognome())
				   && c.getNome().equals(appoggio.get(i).getNome())
				   && c.getTelefono().equals(appoggio.get(i).getTelefono())
				   && c.getEmail().equals(appoggio.get(i).getEmail())
				   && c.getNote().equals(appoggio.get(i).getNote())) {
					dup.add(c);
				}
			}
			cont++;
		}
		
		
		return dup;
	}
	
	public void unisciContattiDup() {
		
	}

}
