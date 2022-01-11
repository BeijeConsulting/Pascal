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

		//test
		System.out.println("Inserito " + c.toString());
	}


	private static void cercaContatto() {
		System.out.println("Cerca per: Nome, Cognome, Telefono, Email");
		String input = s.nextLine();

	}

	private static void modificaContatto() {
		System.out.println("Seleziona contatto da modificare\nnome: ");
		String nome = s.nextLine();
		System.out.println("cognome: ");
		String cognome = s.nextLine();

		List<Contatto> risultati = new ArrayList<>();
		risultati = cercaPerNomeCognome(nome, cognome);
		System.out.println("Quale contatto vuoi modificare?");
		for(int i =0; i<risultati.size(); i++) {
			System.out.println(i + " : " + risultati.get(i));
		}
		int scelta = s.nextInt();
		Contatto contattoScelto = risultati.get(scelta);
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
		
		
	}

	private static List<Contatto> cercaPerNomeCognome(String nome, String cognome) {
		List<Contatto> contatti = new ArrayList();
		List<Contatto> risultati = new ArrayList<>();
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
		return risultati;
	}

	private static void eliminaContatto() {
		// TODO Auto-generated method stub

	}

	private static void trovaDuplicati() {
		// TODO Auto-generated method stub
		try {
			List<Contatto> contatti = caricaRubrica();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Contatto> duplicati = new ArrayList<Contatto>();
		for (int i = 0; i < duplicati.size(); i++) {
			Contatto c = duplicati.get(i);
		}


	}

	private static void unisciContatti() {
		// TODO Auto-generated method stub

	}

	private static void stampaListaContatti() {
		// TODO Auto-generated method stub

	}

	//Utility methods

	//methods to separate the implementation of the db from the rest
	private static void removeContatto(Contatto contatto) {
		RubricaCSV.removeContatto(contatto);
	}
	
	private static void addContatto(Contatto c) {
		rubricaDB.inserisciContatto(c);
		System.out.println("Aggiunto");
	}

	private static List<Contatto> caricaRubrica() throws IOException{
		List<Contatto> rubrica = RubricaCSV.loadRubricaFromCSV();
		return rubrica;
	}

	private static void caricaRubrica(List<Contatto> rubrica) throws IOException {
		RubricaCSV.writeContatti(rubrica);
	}
}