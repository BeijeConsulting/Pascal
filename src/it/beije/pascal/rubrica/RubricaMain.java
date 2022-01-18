package it.beije.pascal.rubrica;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RubricaMain {

	static Scanner s = null;
	static List<Contatto> workingRubrica = null;
	static DatabaseConnection rubricaDB = RubricaHBM.getInstance();

	public static void main(String[] args) {
		System.out.println("Benvenuti in Rubrica");
		int userInput = -1;
		s = new Scanner(System.in);
		while ((userInput != 0)) {
			System.out.println(
					"\n" + "1. Vedi lista contatti \r\n" + "2. Cerca contatto\r\n" + "3. Inserisci nuovo contatto\r\n"
							+ "4. Modifica contatto\r\n" + "5. Elimina contatto\r\n" + "6. Trova contatti duplicati\r\n"
							+ "7. File import/export \r\n" + "8. Unisci contatti duplicati\n\n" + "0. Esci");
			userInput = s.nextInt();
			s.nextLine();
			handleUserInput(userInput);
		}

		System.out.println("BYE!!");
		s.close();

	}

	private static void handleUserInput(int input) {
		int sel = input;
		switch (sel) {
		case 1:
			stampaListaContatti();
			break;
		case 2:
			cercaContatto();
			break;
		case 3:
			inserisciContatto();
			break;
		case 4:
			modificaContatto();
			break;
		case 5:
			eliminaContatto();
			break;
		case 6:
			trovaDuplicati();
			break;
		case 8:
			unisciContatti();
			break;
		case 7:
			gestisciFile();
			break;
		}
	}

	// functions for the handling of user requests
	private static void inserisciContatto() {
		Contatto c = new Contatto();
		String input;
		System.out.print("Inserisci contatto: \nnome: ");
		input = s.nextLine();
		c.setNome(input.isEmpty() ? null : input);
		System.out.print("cognome: ");
		input = s.nextLine();
		c.setCognome(input.isEmpty() ? null : input);
		System.out.print("telefono: ");
		input = s.nextLine();
		c.setTelefono(input.isEmpty() ? null : input);
		System.out.print("email: ");
		input = s.nextLine();
		c.setEmail(input.isEmpty() ? null : input);
		System.out.print("note: ");
		input = s.nextLine();
		c.setNote(input.isEmpty() ? null : input);
		addContatto(c);
	}

	private static void cercaContatto() {
		Contatto c = selezionaContattoDaInput();
		// non trovo nulla
		if (c == null) {
			System.out.println("Nessun risultato");
			return;
		}
		System.out.println("Contatto: " + c.getNome() + " " + c.getCognome() + "\n" + "Telefono : " + c.getTelefono()
				+ "\n" + "Email : " + c.getEmail() + "\n" + "Note : " + c.getNote() + "\n");
	}

	private static void modificaContatto() {
		int scelta;
		Contatto contattoScelto = selezionaContattoDaInput();
		if (contattoScelto == null)
			return;
		do {
			System.out
					.println("Quale campo vuoi modificare: 1.Nome, 2.Cognome, 3.Telefono, 4.Email | 0.fine modifiche");
			scelta = s.nextInt();
			s.nextLine();
			String nuovoCampo = "";
			if (scelta != 0) {
				System.out.println("Nuovo campo: ");
				nuovoCampo = s.nextLine();
				if(nuovoCampo.isEmpty()) nuovoCampo = null;
			}
			switch (scelta) {
			case 0:
				break;
			case 1:
				contattoScelto.setNome(nuovoCampo);
				break;
			case 2:
				contattoScelto.setCognome(nuovoCampo);
				break;
			case 3:
				contattoScelto.setTelefono(nuovoCampo);
				break;
			case 4:
				contattoScelto.setEmail(nuovoCampo);
				break;
			}
		} while (scelta != 0);

		// execute changes to DB
		rubricaDB.modificaContatto(contattoScelto.getId(), contattoScelto);

	}

	private static Contatto selezionaContattoDaInput() {
		List<Contatto> risultati = null;
		System.out.println("1. Ricerca per nome e cognome\t2. Ricerca completa");
		int scelta = s.nextInt();
		s.nextLine();
		switch (scelta) {
		case 1:
			risultati = selezionaContattoNomeCognome();
			break;
		case 2:
			risultati = selezionaContattoCompleto();
			break;
		default:
			break;
		}
		if (risultati == null || risultati.isEmpty()) {
			return null;
		}

		System.out.println("Quale contatto stai cercando?");
		for (int i = 0; i < risultati.size(); i++) {
			System.out.println(i + " : " + risultati.get(i));
		}
		scelta = s.nextInt();
		s.nextLine();
		Contatto contattoScelto = risultati.get(scelta);
		return contattoScelto;
	}

	private static List<Contatto> selezionaContattoCompleto() {
		List<Contatto> risultati;
		System.out.println("Seleziona contatto \nnome: ");
		String nome = s.nextLine();
		System.out.println("cognome: ");
		String cognome = s.nextLine();
		System.out.println("telefono: ");
		String tel = s.nextLine();
		System.out.println("email: ");
		String email = s.nextLine();
		System.out.println("note: ");
		String note = s.nextLine();

		// TODO
		// risultati = rubricaDB.cercaContatto(new Contatto(cognome, nome, tel, email,
		// note));
		// return risultati;
		return null;
	}

	private static List<Contatto> selezionaContattoNomeCognome() {
		List<Contatto> risultati;
		System.out.println("Seleziona contatto \nnome: ");
		String nome = s.nextLine();
		System.out.println("cognome: ");
		String cognome = s.nextLine();

		risultati = cercaPerNomeCognome(nome, cognome);
		return risultati;
	}

	private static void eliminaContatto() {
		Contatto daEliminare = selezionaContattoDaInput();
		rubricaDB.eliminaContatto(daEliminare.getId());
		s.nextLine();
	}

	private static void trovaDuplicati() {
		// TODO
		List<Contatto> contatti = rubricaDB.listDuplicates();
		for (Contatto c : contatti) {
			System.out.println(c.toString());
		}

	}

	private static void unisciContatti() {
		// Auto-generated method stub
		System.out.println("Seleziona contatto da unificare:");
		// show duplicate names ask to select a name
		List<Contatto> duplicati = rubricaDB.listDuplicates();
		int i = 1;
		for (Contatto c : duplicati) {
			System.out.println(i + ": " + c.getNome() + " " + c.getCognome());
			i++;
		}
		// show group of duplicates with index
		int scelta = s.nextInt() - 1;
		s.nextLine();
		Contatto cScelto = duplicati.get(scelta);
		duplicati = rubricaDB.cercaContattoNomeCognome(cScelto.getNome(), cScelto.getCognome());

		System.out.println("Duplicati");
		i = 1;
		for (Contatto c : duplicati) {
			System.out.println(i + ": " + c.toString());
			i++;
		}

		//select which of every field to load
		Contatto nuovoC = cScelto;
		System.out.print("Da quale contatto prendo il Telefono? 	: ");
		String telefono = duplicati.get(s.nextInt() - 1).getTelefono();
		nuovoC.setTelefono(telefono);
		s.nextLine();
		System.out.print("Da quale contatto prendo l Email? 		: ");
		String email = duplicati.get(s.nextInt() - 1).getEmail();
		nuovoC.setTelefono(email);
		s.nextLine();
		System.out.print("Da quale contatto prendo le Note? 		: ");
		String note = duplicati.get(s.nextInt() - 1).getNote();
		nuovoC.setTelefono(note);
		s.nextLine();

		// Delete every duplicate
		for (Contatto c : duplicati) {
			rubricaDB.eliminaContatto(c.getId());
		}

		// add the new merged contact
		rubricaDB.inserisciContatto(nuovoC);

		System.out.println("Contatti uniti!");
	}

	private static void stampaListaContatti() {
		// TODO Auto-generated method stub
		System.out.println("Elenca in ordine di: \n\t1.Nome\n\t2.Cognome");
		int scelta = s.nextInt();
		s.nextLine();
		System.out.println("Elenca in ordine: \n\t1.Ascendente \\n\\t2.Discendente");
		boolean ascdisc = (s.nextInt() == 1) ;
		s.nextLine();

		List<Contatto> contatti = new ArrayList<>();
		switch (scelta) {
		case 1:
			contatti = rubricaDB.listAllOrderedBy("nome", ascdisc);
			break;
		case 2:
			contatti = rubricaDB.listAllOrderedBy("cognome", ascdisc);
			break;
		}
		for (Contatto c : contatti) {
			System.out.println(c.toString());
		}
	}

	private static void gestisciFile() {
		// just handles user input
		System.out.println("1. CSV\t2. XML");
		int sceltaTipo = s.nextInt();
		s.nextLine();
		System.out.println("1. Importa\t2. Esporta");
		int sceltaIO = s.nextInt();
		s.nextLine();
		System.out.println("Nome file: ");
		String fileName = s.nextLine();
		try {
			switch (Integer.parseInt("" + sceltaTipo + sceltaIO)) {
			case 11:
				importCSV(fileName);
				break;
			case 12:
				exportCSV(fileName);
				break;
			case 21:
				importXML(fileName);
				break;
			case 22:
				exportXML(fileName);
				break;
			}
		} catch (IOException ioEx) {
			System.out.println("Errore nella gestione del file");
			ioEx.printStackTrace();
		}
	}

	// Utility methods

	private static void exportXML(String fileName) {
		// TODO untested
		List<Contatto> contatti = rubricaDB.listAllOrderedBy("cognome", true);
		RubricaXML.writeRubricaXML(contatti, fileName);
	}

	// TODO untested
	private static void importXML(String fileName) {
		List<Contatto> contatti = RubricaXML.loadRubricaFromXML(fileName);
		for (Contatto c : contatti) // TODO change to single query
			rubricaDB.inserisciContatto(c);
	}

	private static void exportCSV(String fileName) throws IOException {
		List<Contatto> contatti = rubricaDB.listAllOrderedBy("cognome", true);
		RubricaCSV.writeContatti(contatti, fileName, RubricaCSV.STANDARD_SEPARATOR);

	}

	private static void importCSV(String fileName) throws IOException {
		List<Contatto> contatti = RubricaCSV.loadRubricaFromCSV(fileName, RubricaCSV.STANDARD_SEPARATOR);
		for (Contatto c : contatti) // TODO change to single query
			rubricaDB.inserisciContatto(c);
	}

	private static void addContatto(Contatto c) {
		rubricaDB.inserisciContatto(c);

		System.out.println("Aggiunto");
	}

	private static List<Contatto> cercaPerNomeCognome(String nome, String cognome) {
		List<Contatto> risultati = new ArrayList<>();
		risultati = rubricaDB.cercaContattoNomeCognome(nome, cognome);
		return risultati;
	}
}