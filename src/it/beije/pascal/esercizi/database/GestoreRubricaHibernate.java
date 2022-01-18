package it.beije.pascal.esercizi.database;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import it.beije.pascal.rubrica.Contatto;

public class GestoreRubricaHibernate {

	public static Session ottieniSessione() {
		Configuration configuration = new Configuration().configure().addAnnotatedClass(Contatto.class);
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();

		return session;
	}

	public static void leggiContatti() {
		Session session = ottieniSessione();

		Query<Contatto> query = session.createQuery("SELECT c FROM Contatto as c");
		List<Contatto> contatti = query.getResultList();

		for (Contatto c : contatti) {
			System.out.println(c);
		}

		session.close();
	}
	
	public static void trovaContattiDublicati() {
		Session session = ottieniSessione();

		Query<Contatto> query = session.createQuery("SELECT c FROM Contatto as c");
		List<Contatto> contatti = query.getResultList();
		Transaction transaction = session.beginTransaction();

		//FINIREEEEEEEEE
		for (Contatto c : contatti) {
			if(contatti.contains(c)) {
				System.out.println("Il contatto\n" + c + "\nera duplicato quindi è stato eliminato");
				session.remove(c);	
			}
		}
		session.close();
	}

	public static void cercaContatto(String nome, String cognome) {

		Session session = ottieniSessione();

		Query<Contatto> query = session.createQuery("SELECT c FROM Contatto as c");
		List<Contatto> contatti = query.getResultList();

		for (Contatto c : contatti) {
			if (c.getCognome().equalsIgnoreCase(cognome) && c.getNome().equalsIgnoreCase(nome))
				System.out.println(c);
		}

		session.close();

	}

	public static void registraNuovoContatto(String nome, String cognome, String email, String telefono, String note) {
		Session session = ottieniSessione();

		Transaction transaction = session.beginTransaction();

		Contatto newContatto = new Contatto();
		newContatto.setCognome(cognome);
		newContatto.setNome(nome);
		newContatto.setEmail(email);
		newContatto.setNote(note);
		newContatto.setTelefono(telefono);

		session.save(newContatto);

		transaction.commit();
		System.out.println("Contatto inserito");

		session.close();
	}

	public static void modificaContatto(String cognomeContattoDaCercare, String nomeContattoDaCercare,
			String cosaVuoiModificare, String nuovo) {
		Session session = ottieniSessione();

		Query<Contatto> query = session.createQuery("SELECT c FROM Contatto as c");
		List<Contatto> contatti = query.getResultList();

		Transaction transaction = session.beginTransaction();

		Contatto contatto = null;
		for (Contatto c : contatti) {
			if (c.getCognome().equalsIgnoreCase(cognomeContattoDaCercare)
					&& c.getNome().equalsIgnoreCase(nomeContattoDaCercare))
				contatto = c;
		}

		if (cosaVuoiModificare.equalsIgnoreCase("nome")) {
			contatto.setNome(nuovo);
		} else if (cosaVuoiModificare.equalsIgnoreCase("cognome")) {
			contatto.setCognome(nuovo);
		} else if (cosaVuoiModificare.equalsIgnoreCase("telefono")) {
			contatto.setTelefono(nuovo);
		} else if (cosaVuoiModificare.equalsIgnoreCase("email")) {
			contatto.setEmail(nuovo);
		} else if (cosaVuoiModificare.equalsIgnoreCase("note")) {
			contatto.setNote(nuovo);
		}

		session.save(contatto);
		System.out.println("Contatto modificato con successo!");

		transaction.commit();

		session.close();
	}

	public static void cancellaContatto(String cognomeDelContatto, String nomeContattoDaCercare) {
		Session session = ottieniSessione();

		Query<Contatto> query = session.createQuery("SELECT c FROM Contatto as c");
		List<Contatto> contatti = query.getResultList();

		Transaction transaction = session.beginTransaction();

		Contatto contatto = null;
		for (Contatto c : contatti) {
			if (c.getCognome().equalsIgnoreCase(cognomeDelContatto)
					&& c.getNome().equalsIgnoreCase(nomeContattoDaCercare))
				contatto = c;
		}

		session.remove(contatto);
		System.out.println("Il contatto " + contatto + " è stato eliminato");

		transaction.commit();

		session.close();
	}

	public static void main(String[] args) {

		Scanner tastiera = new Scanner(System.in);
		int scelta = 0;

		do {
			System.out.println("=== Menù ===");
			System.out.println("1. vedi lista contatti (con possibilità di ordinare per nome e cognome a scelta)\r\n"
					+ "2. cerca contatto\r\n" + "3. inserisci nuovo contatto\r\n" + "4. modifica contatto\r\n"
					+ "5. cancella contatto\r\n" + "6. trova contatti duplicati\r\n" + "7. unisci contatti duplicati");
			System.out.println("Scegli un'opzione: ");
			scelta = tastiera.nextInt();
			if (scelta < 1 || scelta > 7) {
				System.out.println("\n\nInserisci un numero compreso tra 1 e 7\n\n");
			}
		} while (scelta < 1 || scelta > 7);

		if (scelta == 1) {
			leggiContatti();
		} else if (scelta == 2) {
			System.out.println("Inserisci il nome del contatto che vuoi cercare:");
			String nome = tastiera.next();
			System.out.println("Inserisci il cognome del contatto che vuoi cercare:");
			String cognome = tastiera.next();
			cercaContatto(nome, cognome);
		} else if (scelta == 3) {
			System.out.println("Nome:");
			String nome = tastiera.next();
			System.out.println("Cognome:");
			String cognome = tastiera.next();
			System.out.println("Email:");
			String email = tastiera.next();
			System.out.println("Telefono:");
			String telefono = tastiera.next();
			System.out.println("Note:");
			String note = tastiera.next();
			registraNuovoContatto(nome, cognome, email, telefono, note);
		} else if(scelta == 4) {
			System.out.println("Cognome da cercare:");
			String cognome = tastiera.next();
			System.out.println("Nome da cercare:");
			String nome = tastiera.next();
			cercaContatto(nome, cognome);
			System.out.println("\nCosa vuoi modificare?");
			String cosaModificare = tastiera.next();
			System.out.println("Cosa ci vuoi inserire:");
			String cosaInserire = tastiera.next();
			modificaContatto(cognome, nome, cosaModificare, cosaInserire);
		} else if(scelta == 5) {
			System.out.println("Cognome da cercare:");
			String cognome = tastiera.next();
			System.out.println("Nome da cercare:");
			String nome = tastiera.next();
			cancellaContatto(cognome, nome);
		} else if(scelta == 6) {
			trovaContattiDublicati();
		}

		tastiera.close();

//		leggiContatti();
//		registraNuovoContatto();
//		modificaContatto();
//		cancellaContatto();

	}

}
