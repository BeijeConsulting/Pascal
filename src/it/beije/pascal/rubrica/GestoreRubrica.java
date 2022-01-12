package it.beije.pascal.rubrica;

import java.util.List;
import java.util.Scanner;

public class GestoreRubrica {
	public static RubricaJDBC rubricaDB = new RubricaJDBC();

	public static void modificaContatto(Contatto c) throws Exception {
		Contatto daModificare = rubricaDB.cercaContatto(c);
		Scanner in = new Scanner(System.in);
		System.out.println("Quale campo vuoi modificare?\n"
				+ "1- nome\n"
				+ "2- cognome\n"
				+ "3- telefono\n"
				+ "4- email\n"
				+ "5- note");
		System.out.println("Scelta: ");
		String scelta = in.nextLine();
		switch(scelta) {
		case "1":
			System.out.println("Nuovo nome: ");
			daModificare.setNome(in.nextLine());
			break;
		case "2":
			System.out.println("Nuovo Cognome: ");
			daModificare.setCognome(in.nextLine());
			break;
		case "3":
			System.out.println("Nuovo Telefono: ");
			daModificare.setTelefono(in.nextLine());
			break;
		case "4":
			System.out.println("Nuova Email: ");
			daModificare.setEmail(in.nextLine());
			break;
		case "5":
			System.out.println("Nuove Note: ");
			daModificare.setNote(in.nextLine());
			break;
		}
		
		rubricaDB.modificaContatto(daModificare.getId(), daModificare);
		in.close();
	}
	
	public static void importCSVInDB() {
		
	}
	
	public static void importXMLInDB() {
		
	}
	
	public static void exportDBInCSV() {
		
	}
	
	public static void exportDBInXML() {
		
	}
	
	public static void stampaRubrica(List<Contatto> rubrica)
	{
		for(Contatto c : rubrica) {		
			System.out.println(c.toString());
		}
		
	}
	
	public static void stampaContatto(Contatto c) {
		System.out.println(c.toString());	
	}
	
	public static Contatto inputContatto() {
		Scanner in = new Scanner(System.in);
		Contatto appoggio = new Contatto();
		System.out.print("Cognome: ");
		appoggio.setCognome(in.nextLine()); 
		System.out.print("Nome: ");
		appoggio.setNome(in.nextLine());
		System.out.print("Telefono: ");
		appoggio.setTelefono(in.nextLine());
		System.out.print("Email: ");
		appoggio.setEmail(in.nextLine());
		System.out.print("Note: ");
		appoggio.setNote(in.nextLine());
		//in.close();
		return appoggio;
	}

	public static void main(String[] args) throws Exception {
		Contatto appoggio = new Contatto();
		Scanner in = new Scanner(System.in);
		System.out.println("-Gestore Rubrica-");
		System.out.println("Menu:");
		System.out.println("1 -Vedi lista contatti ordinata per Nome\n" 
		                   + "2 -Vedi lista contatti ordinata per Cognome\n"
				           + "3 -Cerca Contatto\n" 
		                   + "4 -Inserisci Contatto\n"  
				           + "5 -Modifica Contatto\n"
		                   + "6 -Cancella Contatto\n"
		                   + "7 -Trova Contatti Duplicati\n"
		                   + "8 -Unisci Contatti Duplicati\n"
		                   + "9 -Importa Rubrica da CSV a DB\n"
		                   + "10 -Importa Rubrica da XML a DB\n"
		                   + "11 -Esporta Rubrica da DB a CSV\n"
		                   + "12 -Esporta Rubrica da DB a XML\n"
		                   + "0 -Esci");
		System.out.println("Inserisci il numero della funzione da eseguire: ");
		String scelta = in.nextLine();
		switch(scelta) {
		default:
		case "0":
			System.out.println("Uscita");
			break;
		case "1":
			stampaRubrica(rubricaDB.ordinaPerNome());
			break;
		case "2":
			stampaRubrica(rubricaDB.ordinaPerCognome());
			break;
		case "3":
			System.out.println("Inserisci dati del contatto da cercare");
			appoggio = inputContatto();
			stampaContatto(rubricaDB.cercaContatto(appoggio));
			break;
		case "4":
			System.out.println("Inserisci dati del contatto da inserire");
			appoggio = inputContatto();
			rubricaDB.inserisciContatto(appoggio);
			break;
		case "5":
			System.out.println("Inserisci contatto da modificare");
			appoggio = inputContatto();
			modificaContatto(appoggio);
			break;
		case "6":
			break;
		case "7":
			break;
		case "8":
			break;
		case "9":
			break;
		case "10":
			break;
		case "11":
			break;
		case "12":
			break;
		}
		in.close();
	}

}
