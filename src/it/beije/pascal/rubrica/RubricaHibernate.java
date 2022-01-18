package it.beije.pascal.rubrica;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Transaction;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class RubricaHibernate {
	
	RubricaHibernate core = new RubricaHibernate();
	private static Scanner scan = new Scanner(System.in);
	
	//GET SESSION
	private static Session getSession() {
		Configuration configuration = new Configuration().configure().addAnnotatedClass(Contatto.class);					
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		return session;
	}
	
	//SELECT TUTTI I CONTATTI
	public List<Contatto> listContacts() {	
		Session session = getSession();
		@SuppressWarnings("unchecked")
		Query<Contatto> query = session.createQuery("SELECT c FROM Contatto as c");
		List<Contatto> contatti = query.getResultList();
		
		return contatti;
	}
	
	//SELECT ORDINATO PER NOME
	public List<Contatto> listContactsByName() {	
		Session session = getSession();
		@SuppressWarnings("unchecked")
		Query<Contatto> query = session.createQuery("SELECT c FROM Contatto as c ORDERED BY nome");
		List<Contatto> contatti = query.getResultList();
		
		return contatti;
	}
	
	//SELECT ORDINATO PER COGNOME
	public List<Contatto> listContactsByCognome() {	
		Session session = getSession();
		@SuppressWarnings("unchecked")
		Query<Contatto> query = session.createQuery("SELECT c FROM Contatto as c ORDERED BY cognome");
		List<Contatto> contatti = query.getResultList();
		
		return contatti;
	}
	
	//TROVA UN CONTATTO
	public List<Contatto> lookForContact(String nome, String cognome) {
		Session session = getSession();
		@SuppressWarnings("unchecked")
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
		
		List<Contatto> contatti = core.listContacts();
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
		
		List<Contatto> contatti = core.listContacts();
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
	public List<Contatto> selectDuplicate() {
        List<Contatto> dup = new ArrayList<>();

        List<Contatto> appoggio = core.listContacts();

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
	
	//UNISCI TUTTI I DUPLICATI, OVVERO ELIMINALI TUTTI
	public void deleteDuplicate(List<Contatto> contactsToDelete) {
		Session session = getSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		
		List<Contatto> contatti = core.listContacts();
	
		Contatto cont = null;
		
		for(int i = 0; i < contactsToDelete.size(); i++) {
			for(int j = 0; j < contatti.size(); j++) {
				if(contactsToDelete.get(i).getId() == contatti.get(j).getId()) {
					cont = contatti.get(j);
					session.delete(cont);
					transaction.commit();
					cont = null;
				}
			}
		}
		session.close();
	}
	
	//ESPORTA I CONTATTI LETTI IN UN FILE XML
	public void exportContactsXML(String filePath) {
		List<Contatto> contatti = core.listContacts();
		try {
			RubricaUtils.writeRubricaXML(contatti, filePath);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//ESPORTA I CONTATTI LETTI IN UN FILE CSV
	public void exportContactsCSV(String filePath, String separator) {
		List<Contatto> contatti = core.listContacts();
		try {
			RubricaUtils.writeRubricaCSV(contatti, filePath, separator);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//IMPORTA DEI CONTATTI DA UN FILE XML
	public void importContactsXML(String pathFile) {
		List<Contatto> contatti = null;
		try {
			contatti = RubricaUtils.loadRubricaFromXML(pathFile);
		} catch(Exception e) {
			e.printStackTrace();
		}
		for(Contatto c : contatti) {
			core.newContact(c.getCognome(), c.getNome(), c.getTelefono(), c.getEmail(), c.getNote());
		}
	}
	
	//IMPORTA DEI CONTATTI DA UN FILE CSV
	public void importContactsCSV(String pathFile, String separator) {
		List<Contatto> contatti = null;
		try {
			contatti = RubricaUtils.loadRubricaFromCSV(pathFile, separator);
		} catch(Exception e) {
			e.printStackTrace();
		}
		for(Contatto c : contatti) {
			core.newContact(c.getCognome(), c.getNome(), c.getTelefono(), c.getEmail(), c.getNote());
		}
	}
	
	//mostra il menù
	public void showMenu() {
		System.out.println("scegli un'operazione: \n"
							+ "1. mostra tutti i contatti\n"
							+ "2. cerca un contatto\n"
							+ "3. aggiungi un contatto\n"
							+ "4. modifica un contatto\n"
							+ "5. elimina un contatto\n"
							+ "6. mostra i contatti duplicati\n"
							+ "7. elimina i contatti duplicati\n"
							+ "8. esporta i contatti in un file (XML / CSV)\n"
							+ "9. importa i contatti di un file (XML / CSV)\n"
							);
	
		int choice = scan.nextInt();
		switch(choice) {
			case 1: {
				
				break;
			}
			case 2: {
				break;
			}
			case 3: {
				break;
			}
			case 4: {
				break;
			}
			case 5: {
				break;
			}
			case 6: {
				break;
			}
			case 7: {
				break;
			}
			case 8: {
				break;
			}
			case 9: {
				break;
			}
		}
	}
	
	public void askSorting() {
		
	}
	
	public static void main(String... args) {
		System.out.println("Benvenuto in Rubrica");
	}
}
