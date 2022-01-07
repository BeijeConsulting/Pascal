package it.beije.pascal.rubrica;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import it.beije.pascal.rubrica.model.Contatto;
import it.beije.pascal.rubrica.util.RubricaXmlUtil;

public class RubricaXML {
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws Exception {
		boolean keepGoing = true;
		while (keepGoing) {
			writeMenu();
			int scelta = leggiScelta();
			switch (scelta) {
			case 0:
				keepGoing = false;
				break;
			case 1:
				backup();
				break;
			case 2:
				writeContacts();
				break;
			case 3:
				findContact();
				break;
			case 4:
				insertContact();
				break;
			case 5:
				updateContact();
				break;
			case 6:
				deleteContact();
				break;
			case 7:
				//trovaDuplicati();
				break;
			}
		}

	}

	private static void writeMenu() {
		System.out.println("\n==== MENU ====");
		System.out.println("0: esci");
		System.out.println("1: backup");
		System.out.println("2: stampa lista contatti (cognome decrescente)");
		System.out.println("3: cerca contatto");
		System.out.println("4: inserisci contatto");
		System.out.println("5: modifica contatto");
		System.out.println("6: cancella contatto");
		System.out.println("7: trova contatti duplicati");
		System.out.println("8: unisci contatti duplicati\n");
	}

	private static int leggiScelta() {
		System.out.print("Inserisci scelta: ");
		int scelta = scanner.nextInt();
		while (scelta < 0 || scelta > 8) {
			System.out.print("\nScelta errata,reinserire: ");
			scelta = scanner.nextInt();
		}
		return scelta;
	}

	private static void writeContacts() {
		List<Contatto> contatti = RubricaXmlUtil.getContactList();
		for (Contatto contatto : contatti) {
			System.out.println(contatto);
		}
	}

	private static void findContact() {
		Contatto contatto = new Contatto("Emanuele", "Corona", "3335877155", "emacorona@gmail.com",
				"breve descrizione");
		List<Contatto> contatti = RubricaXmlUtil.getContactList();
		Contatto c = RubricaXmlUtil.findContact(contatto, contatti);
		if (c != null) {
			System.out.println(c);
		} else {
			System.out.println("Contatto non trovato");
		}
	}

	private static void insertContact() {
		Contatto contatto = new Contatto("Nuovo nome", "Nuovo cognome", "Nuovo telefono", "Nuova email", "Nuova nota");
		List<Contatto> contatti = RubricaXmlUtil.getContactList();
		contatti.add(contatto);
		RubricaXmlUtil.insertContacts(contatti);

	}

	private static void updateContact() {
		Contatto contatto = new Contatto("Emanuele", "Corona", "3335877155", "emacorona@gmail.com",
				"breve descrizione");
		Contatto newContatto = new Contatto("modificato", "modificato", "modificato", "modificato", "modificato");
		List<Contatto> contatti = RubricaXmlUtil.getContactList();
		for (int i = 0; i < contatti.size(); i++) {
			if (contatto.equals(contatti.get(i))) {
				contatti.set(i, newContatto);
			}
		}
		RubricaXmlUtil.insertContacts(contatti);
	}

	private static void deleteContact() {
		Contatto contatto = new Contatto("Emanuele", "Corona", "3335877155", "emacorona@gmail.com",
				"breve descrizione");
		List<Contatto> contatti = RubricaXmlUtil.getContactList();
		for (int i = 0; i < contatti.size(); i++) {
			// Overload metodo equals della classe Contatto
			if (contatto.equals(contatti.get(i))) {
				contatti.remove(i);
			}
		}
		RubricaXmlUtil.insertContacts(contatti);
	}

	/*
	private static void trovaDuplicati() {
		List<Contatto> contatti = RubricaXmlUtil.getContactList();
		List<Contatto> contattiDuplicati = new ArrayList<Contatto>();
		int tmp = 0;
		OUTER: for (int i = 0; i < contatti.size(); i++) {
			INNER: for (int j = i + 1; j < contatti.size(); j++) {
				if (contatti.get(i).equals(contatti.get(j))) {
					System.out.println(contatti.get(i));
					continue OUTER;
				}
			}
			//i = tmp;
		}
	}
	*/

	private static void eliminaDuplicati() {
		List<Contatto> contatti = RubricaXmlUtil.getContactList();
		for (int i = 0; i < contatti.size(); i++) {

		}
	}

	private static void backup() {
		Contatto contatto1 = new Contatto("Emanuele", "Corona", "3335877155", "emacorona@gmail.com",
				"breve descrizione");
		Contatto contatto2 = new Contatto("Emanuele", "Corona", "3335877155", "emacorona@gmail.com",
				"breve descrizione");
		Contatto contatto3 = new Contatto("Paolo", "Bianchi", "3423546547", "paolobianchi@gmail.com", "il vicino");
		Contatto contatto4 = new Contatto("Mario", "Rossi", "333344455", "mariorossi@gmail.com", "il solito mario");
		Contatto contatto5 = new Contatto("Mario", "Rossi", "333344455", "mariorossi@gmail.com", "il solito mario");
		Contatto contatto6 = new Contatto("Mario", "Rossi", "333344455", "mariorossi@gmail.com", "il solito mario");
		Contatto contatto7 = new Contatto("Paolo", "Bianchi", "3423546547", "paolobianchi@gmail.com", "il vicino");
		List<Contatto> contatti = new ArrayList<Contatto>();
		contatti.add(contatto1);
		contatti.add(contatto2);
		contatti.add(contatto3);
		contatti.add(contatto4);
		contatti.add(contatto5);
		contatti.add(contatto6);
		contatti.add(contatto7);
		RubricaXmlUtil.insertContacts(contatti);
	}

}
