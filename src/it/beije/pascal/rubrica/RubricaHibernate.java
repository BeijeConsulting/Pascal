package it.beije.pascal.rubrica;

import java.util.ArrayList;
import java.util.List;


import org.hibernate.Transaction;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class RubricaHibernate {
	
	//GET SESSION
	private static Session getSession() {
		Configuration configuration = new Configuration().configure().addAnnotatedClass(Contatto.class);					
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		return session;
	}
	
	//SELECT TUTTI I CONTATTI
	public static List<Contatto> listContacts() {	
		Session session = getSession();
		Query<Contatto> query = session.createQuery("SELECT c FROM Contatto as c");
		List<Contatto> contatti = query.getResultList();
		
		return contatti;
	}
	
	//SELECT ORDINATO PER NOME
	public static List<Contatto> listContactsByName() {	
		Session session = getSession();
		Query<Contatto> query = session.createQuery("SELECT c FROM Contatto as c ORDERED BY nome");
		List<Contatto> contatti = query.getResultList();
		
		return contatti;
	}
	
	//SELECT ORDINATO PER COGNOME
	public static List<Contatto> listContactsByCognome() {	
		Session session = getSession();
		Query<Contatto> query = session.createQuery("SELECT c FROM Contatto as c ORDERED BY cognome");
		List<Contatto> contatti = query.getResultList();
		
		return contatti;
	}
	
	//TROVA UN CONTATTO
	public List<Contatto> lookForContact(String nome, String cognome) {
		Session session = getSession();
		Query<Contatto> query = session.createQuery("FROM Contatto as C WHERE C.nome = '" + nome + "' AND C.cognome = '" + cognome + "'");
		List<Contatto> contatti = query.getResultList();
		
		return contatti;
	}
	
	//INSERT UN NUOVO CONTATTO
	public void newContact(String cognome, String nome, String tel, String email, String note) {
		Session session = getSession();

		Transaction transaction = session.getTransaction();
		transaction.begin();
		
		Contatto cont = new Contatto();
		cont.setCognome(cognome);
		cont.setNome(nome);
		cont.setEmail(email);
		cont.setTelefono(tel);
		cont.setNote(note);
		
		session.save(cont);
		transaction.commit();
		session.close();
	}
	
	//UPDATE DI UN CONTATTO ESISTENTE
	public void updateContact(int Id, String column, String newValue) {
		Session session = getSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		
		List<Contatto> contatti = listContacts();
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
	
	//DELETE DI UN CONTATTO A PIACERE
	public void deleteContact(int Id) {
		Session session = getSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		
		List<Contatto> contatti = listContacts();
		Contatto contattoElim = null;
		
		for(Contatto cont : contatti) {
			if(cont.getId() == Id) {
				contattoElim = cont;
			}
		}
		
		session.remove(contattoElim);
		transaction.commit();
		session.close();
	}
	
	//SELEZIONA DUPLICATI
	public static List<Contatto> selectDuplicate() {
        List<Contatto> dup = new ArrayList<>();

        List<Contatto> appoggio = listContacts();

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
	
	public static void main(String... args) {
//		List<Contatto> cont = RubricaHibernate.listContacts();
//		for(Contatto contatto : cont) {
//			System.out.println(contatto);
//		}
		
		RubricaHibernate rh = new RubricaHibernate();
		//rh.newContact("Baba", "Marco", "37492349", "marian.baba@gmail.com", "sono");
//		rh.deleteContact(7);
		RubricaHibernate.selectDuplicate();
		
	}
}
