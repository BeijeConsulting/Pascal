package it.beije.pascal.rubrica;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class RubricaMain {

	static Scanner s = null;
	static List<Contatto> workingRubrica = null;
	static RubricaJDBC rubricaDB = new RubricaJDBC();

	public static void main(String[] args) {
		System.out.println("Benvenuti in Rubrica");
		String userInput = "";
		s = new Scanner(System.in);
		while (!userInput.equalsIgnoreCase("exit")) {
			System.out.println("Vedi lista contatti \r\n"
					+ "Cerca contatto\r\n"
					+ "Inserisci nuovo contatto\r\n"
					+ "Modifica contatto\r\n"
					+ "Elimina contatto\r\n"
					+ "Trova contatti duplicati\r\n"
					+ "File import/export \r\n"
					+ "Unisci contatti duplicati");
			userInput = s.nextLine();
			handleUserInput(userInput);
		}

		System.out.println("BYE!!");
		s.close();

	}

	private static void handleUserInput(String input) {
		char sel = input.toLowerCase().charAt(0);
		switch(sel) {
		case 'v': stampaListaContatti(); break;
		case 'c': cercaContatto();break;
		case 'i': inserisciContatto();break;
		case 'm': modificaContatto();break;
		case 'e': eliminaContatto();break;
		case 't': trovaDuplicati();break;
		case 'u': unisciContatti();break;
		case 'f': gestisciFile();break;
		}
	}


	//functions for the handling of user requests
	private static void inserisciContatto() {
		Contatto c = new Contatto();

		System.out.print("Inserisci contatto: \nnome: ");
		c.setNome(s.nextLine());
		System.out.print("cognome: ");
		c.setCognome(s.nextLine());
		System.out.print("telefono: ");
		c.setTelefono(s.nextLine());
		System.out.print("email: ");
		c.setEmail(s.nextLine());
		System.out.print("note: ");
		c.setNote(s.nextLine());

		addContatto(c);
	}

	private static void cercaContatto() {
		Contatto c = selezionaContattoDaInput();
		System.out.println("Contatto: "+ c.getNome() + " " + c.getCognome() + "\n"
				+ "Telefono : " + c.getTelefono() + "\n"
				+ "Email : " + c.getEmail() + "\n"
				+ "Note : " + c.getNote());
	}

	private static void modificaContatto() {
		int scelta;
		Contatto contattoScelto = selezionaContattoDaInput();
		do {
			System.out.println("Quale campo vuoi modificare: 1.Nome, 2.Cognome, 3.Telefono, 4.Email | 0.fine modifiche");
			scelta = s.nextInt();
			s.nextLine();
			if(scelta ==0) return;
			System.out.println("Nuovo campo: ");
			String nuovoCampo = s.nextLine();
			switch (scelta) {
			case 0: break;
			case 1: contattoScelto.setNome(nuovoCampo); break;
			case 2: contattoScelto.setCognome(nuovoCampo);break;
			case 3: contattoScelto.setTelefono(nuovoCampo);break;
			case 4: contattoScelto.setEmail(nuovoCampo);break;
			}
		}while(scelta !=0);

		//execute changes to DB
		rubricaDB.modificaContatto(contattoScelto.getId(), contattoScelto);


	}

	private static Contatto selezionaContattoDaInput() {
		System.out.println("Seleziona contatto \nnome: ");
		String nome = s.nextLine();
		System.out.println("cognome: ");
		String cognome = s.nextLine();

		List<Contatto> risultati = new ArrayList<>();
		risultati = cercaPerNomeCognome(nome, cognome);
		System.out.println("Quale contatto stai cercando?");
		for(int i =0; i<risultati.size(); i++) {
			System.out.println(i + " : " + risultati.get(i));
		}
		int scelta = s.nextInt();
		Contatto contattoScelto = risultati.get(scelta);
		return contattoScelto;
	}

	private static void eliminaContatto() {
		Contatto daEliminare = selezionaContattoDaInput();
		rubricaDB.eliminaContatto(daEliminare.getId());
		s.nextLine();
	}

	private static void trovaDuplicati() {
		//TODO
		List<Contatto> contatti = rubricaDB.listDuplicates();
		for(Contatto c : contatti) {
			System.out.println(c.toString());
		}

	}

	private static void unisciContatti() {
		// TODO Auto-generated method stub

	}

	private static void stampaListaContatti() {
		// TODO Auto-generated method stub
		System.out.println("Elenca in ordine di: \n\t1.Nome\n\t2.Cognome");
		int scelta = s.nextInt();
		s.nextLine();
		List<Contatto> contatti = new ArrayList<>();
		switch(scelta) {
		case 1: contatti = rubricaDB.listAllOrderedBy("nome", true); break;
		case 2: contatti = rubricaDB.listAllOrderedBy("cognome", true); break;
		}
		for(Contatto c : contatti) {
			System.out.println(c.toString());
		}
	}

	private static void gestisciFile() {
		//just handles user input
		System.out.println("1. CSV\t2. XML");
		int sceltaTipo = s.nextInt();s.nextLine();
		System.out.println("1. Importa\t2. Esporta");
		int sceltaIO = s.nextInt();s.nextLine();
		System.out.println("Nome file: ");
		String fileName = s.nextLine();
		try {
		switch(Integer.parseInt(""+sceltaTipo+sceltaIO)) {
		case 11: importCSV(fileName); break;
		case 12: exportCSV(fileName); break;
		case 21: importXML(fileName); break;
		case 22: exportXML(fileName); break;
		}
		} catch(IOException ioEx) {
			System.out.println("Errore nella gestione del file");
			ioEx.printStackTrace();
		}
	}
	
	
	//Utility methods

	private static void exportXML(String fileName) {
		// TODO Auto-generated method stub
		
	}

	private static void importXML(String fileName) {
		// TODO Auto-generated method stub
		
	}

	private static void exportCSV(String fileName) throws IOException {
		List<Contatto> contatti = rubricaDB.listAllOrderedBy("cognome", true);
		RubricaCSV.writeContatti(contatti, fileName, RubricaCSV.STANDARD_SEPARATOR);
		
	}

	private static void importCSV(String fileName) throws IOException {
		List<Contatto> contatti = RubricaCSV.loadRubricaFromCSV(fileName, RubricaCSV.STANDARD_SEPARATOR);
		for(Contatto c : contatti)
			rubricaDB.inserisciContatto(c);
	}

	//methods to separate the implementation of the db from the rest
	private static void removeContatto(Contatto contatto) {
		RubricaCSV.removeContatto(contatto);
	}

	private static void addContatto(Contatto c) {
		rubricaDB.inserisciContatto(c);
		System.out.println("Aggiunto");
	}

	private static List<Contatto> cercaPerNomeCognome(String nome, String cognome) {
		List<Contatto> contatti = new ArrayList();
		List<Contatto> risultati = new ArrayList<>();

		//csv
		/*
		try {
			contatti = caricaRubrica();
		} catch (IOException e) {
			System.out.println("errore nel caricamento della rubrica");
			e.printStackTrace();
			return risultati;
		}
		for(Contatto c : contatti) {
			if (nome==null || nome.equals("")|| c.getNome().equals(nome))
				if (cognome == null || cognome.equals("") || c.getCognome().equals(cognome))
					risultati.add(c);
		}
		 */

		//DB
		risultati = rubricaDB.cercaContatto(nome, cognome);
		return risultati;
	}

	private static List<Contatto> caricaRubrica() throws IOException{
		List<Contatto> rubrica = RubricaCSV.loadRubricaFromCSV();
		return rubrica;
	}

	private static void caricaRubrica(List<Contatto> rubrica) throws IOException {
		RubricaCSV.writeContatti(rubrica);
	}
}