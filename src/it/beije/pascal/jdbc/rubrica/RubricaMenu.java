package it.beije.pascal.jdbc.rubrica;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import it.beije.pascal.bean.Contatto;

public class RubricaMenu {

	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		boolean continua = true;
		while (continua) {
			writeMenu();
			int scelta = readChoice();
			switch (scelta) {

			case 0:
				continua = false;
				break;
			case 1:
				backup();
				break;
			case 2:
				printContacts();
				break;
			case 3:
				findContact();
				break;
			case 4:
				addContact();
				break;
			case 5:
				updateContact();
				break;
			case 6:
				deleteContact();
				break;
			case 7:
				findDuplicates();
				break;
			}
		}
	}

	private static void writeMenu() {
		System.out.println("\n==== MENU ====");
		System.out.println("0: esci");
		System.out.println("1: ripristina database");
		System.out.println("2: stampa lista contatti (Nome crescente)");
		System.out.println("3: cerca contatto");
		System.out.println("4: inserisci contatto");
		System.out.println("5: modifica contatto");
		System.out.println("6: cancella contatto");
		System.out.println("7: trova contatti duplicati");
		System.out.println("8: unisci contatti duplicati\n");
	}

	private static int readChoice() {
		System.out.print("Inserisci scelta: ");
		int scelta = scanner.nextInt();
		while (scelta < 0 || scelta > 8) {
			System.out.print("\nScelta errata,reinserire: ");
			scelta = scanner.nextInt();
		}
		return scelta;
	}

	private static void backup() {
		Contatto contatto1 = new Contatto("Emanuele", "Corona", "3335877155", "emacorona@gmail.com",
				"breve descrizione");
		Contatto contatto2 = new Contatto("Paolo", "Bianchi", "3423546547", "paolobianchi@gmail.com", "il vicino");
		Contatto contatto3 = new Contatto("Mario", "Rossi", "333344455", "mariorossi@gmail.com", "il solito mario");
		Contatto contatto4 = new Contatto("Carlo", "Pascolino", "213124324", "carloPascolino@gmail.com",
				"il falegname");
		Contatto contatto5 = new Contatto("Pino", "Gasto", "3243514354", "pinogasto@gmail.com", "il poliziotto");

		List<Contatto> contatti = new ArrayList<Contatto>();
		contatti.add(contatto1);
		contatti.add(contatto2);
		contatti.add(contatto3);
		contatti.add(contatto4);
		contatti.add(contatto5);

		RubricaService.deleteAll();
		RubricaService.addContact(contatti);
	}

	private static void printContacts() {
		List<Contatto> contatti = RubricaService.getContactsList();
		for (Contatto contatto : contatti) {
			System.out.println(contatto);
		}
	}

	private static void findContact() {
		Contatto contatto = new Contatto("Emanuele", "Corona", "3335877155", "emacorona@gmail.com",
				"breve descrizione");
		Contatto foundContact = RubricaService.findContact(contatto);
		if (foundContact != null) {
			System.out.println(foundContact);
		} else {
			System.err.println("Contatto non trovato");
		}
	}

	private static void addContact() {
		Contatto contatto = new Contatto("nuovo", "nuovo", "nuovo", "nuovo", "nuovo");
		RubricaService.addContact(contatto);
	}

	private static void deleteContact() {
		Contatto contatto = new Contatto("Emanuele", "Corona", "3335877155", "emacorona@gmail.com",
				"breve descrizione");
		;
		RubricaService.deleteContact(contatto);
	}

	private static void updateContact() {
		Contatto contatto = new Contatto("Emanuele", "Corona", "3335877155", "emacorona@gmail.com",	"breve descrizione");
		RubricaService.updateContact(contatto);
	}

	private static void findDuplicates() {
		List<Contatto> contatti = RubricaService.getContactsList();
		// contatti = ContattoDAO.findDuplicates(contatti);
		for (Contatto contatto : contatti) {
			System.out.println(contatto);
		}
	}

}
