package it.beije.pascal.rubrica;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class GestoreRubrica {
	//private static RubricaJDBCManager rubrica = new RubricaJDBCManager();
    //private static RubricaHBMManager rubrica = new RubricaHBMManage3r();
	private static RubricaJPAManager rubrica = new RubricaJPAManager();
	
    private static void modificaContatto(Contatto c) throws Exception {
		Contatto daModificare = rubrica.cercaContatto(c);
		Scanner in = new Scanner(System.in);
		System.out.println("Quale campo vuoi modificare?\n"
				+ "1- nome\n"
				+ "2- cognome\n"
				+ "3- telefono\n"
				+ "4- email\n"
				+ "5- note");
		System.out.println("Scelta: ");
		String scelta = in.nextLine();
		String campoMod = null;
		switch(scelta) {
		case "1":
			System.out.println("Nuovo nome: ");
			//daModificare.setNome(in.nextLine());
			campoMod = in.nextLine();
			break;
		case "2":
			System.out.println("Nuovo Cognome: ");
			//daModificare.setCognome(in.nextLine());
			campoMod = in.nextLine();
			break;
		case "3":
			System.out.println("Nuovo Telefono: ");
			//daModificare.setTelefono(in.nextLine());
			campoMod = in.nextLine();
			break;
		case "4":
			System.out.println("Nuova Email: ");
			//daModificare.setEmail(in.nextLine());
			campoMod = in.nextLine();
			break;
		case "5":
			System.out.println("Nuove Note: ");
			//daModificare.setNote(in.nextLine());
			campoMod = in.nextLine();
			break;
		}
		
		//rubricaDB.modificaContatto(daModificare.getId(), daModificare);
		rubrica.updateContact(daModificare.getId(), scelta, campoMod);
	}
	
	private static void importCSVInDB(String pathFile, String separator) throws Exception {
		List<Contatto> contattiCSV = LoadReadXML_CSV.loadRubricaFromCSV(pathFile, separator);
		
		for(Contatto c: contattiCSV) {
			rubrica.inserisciContatto(c);
		}
		
		System.out.println("Import avvenuto\n Rubrica Aggiornata:");
		stampaRubrica(rubrica.getRubrica());
	}
	
	private static void importXMLInDB(String pathFile) throws Exception {
		List<Contatto> contattiXML = LoadReadXML_CSV.loadRubricaFromXML(pathFile);
		
		for(Contatto c: contattiXML) {
			rubrica.inserisciContatto(c);
		}
		
		System.out.println("Import avvenuto\n Rubrica Aggiornata:");
		stampaRubrica(rubrica.getRubrica());
		
	}
	
	private static void exportDBInCSV(String pathFile, String separator) {
		List<Contatto> contattiDB = rubrica.getRubricaOrderCognome();
		
		LoadReadXML_CSV.writeRubricaCSV(contattiDB, pathFile, separator);
		
		System.out.println("Export Avvenuto");
	}
	
	private static void exportDBInXML(String pathFile) throws Exception {
		List<Contatto> contattiDB = rubrica.getRubricaOrderCognome();
		
		LoadReadXML_CSV.writeRubricaXML(contattiDB, pathFile);
		
		System.out.println("Export Avvenuto");
	}
	
	private static void stampaRubrica(List<Contatto> rubrica)
	{
		for(Contatto c : rubrica) {		
			System.out.println(c.toString());
		}
		
	}
	
	private static void stampaContatto(Contatto c) {
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
		System.out.println("Inserisci il numero della funzione da eseguire");
		String scelta = in.nextLine();
		switch(scelta) {
		default:
		case "0":
			System.out.println("Uscita");
			break;
		case "1":
			//stampaRubrica(rubricaDB.ordinaPerNome());
			stampaRubrica(rubrica.getRubricaOrderNome());
			break;
		case "2":
			//stampaRubrica(rubricaDB.ordinaPerCognome());
			stampaRubrica(rubrica.getRubricaOrderCognome());
			break;
		case "3":
			System.out.println("Inserisci dati del contatto da cercare");
			appoggio = inputContatto();
			stampaContatto(rubrica.cercaContatto(appoggio));
			break;
		case "4":
			System.out.println("Inserisci dati del contatto da inserire");
			appoggio = inputContatto();
			rubrica.inserisciContatto(appoggio);
			break;
		case "5":
			System.out.println("Inserisci contatto da modificare");
			appoggio = inputContatto();
			modificaContatto(appoggio);
			break;
		case "6":
			System.out.println("Inserisci contatto da cancellare");
			appoggio = inputContatto();
			rubrica.cancellaContatto(appoggio);
			break;
		case "7":
			List<Contatto> dup = rubrica.trovaContattiDup();
			System.out.println("Lista contatti duplicati");
			stampaRubrica(dup);
			break;
		case "8":
			break;
		case "9":
			System.out.println("Inserisci Percorso del file CSV da cui leggere i dati:");
			String pathFileCSV = in.nextLine();
			System.out.println("Inserisci Separatore Dati:");
			String separator = in.nextLine();
			importCSVInDB(pathFileCSV, separator);
			break;
		case "10":
			System.out.println("Inserisci Percorso del file XML da cui leggere i dati");
			String pathFileXML = in.nextLine();
			importXMLInDB(pathFileXML);
			break;
		case "11":
			System.out.println("Inserisci Nome del file CSV in cui inserire i dati:");
			String pathWriteCSV = in.nextLine();
			System.out.println("Inserisci Separatore Dati:");
			String separatorWrite = in.nextLine();
			exportDBInCSV(pathWriteCSV, separatorWrite);
			break;
		case "12":
			System.out.println("Inserisci Nome del file XML in cui Inserire i dati");
			String pathWriteXML = in.nextLine();
			exportDBInXML(pathWriteXML);
			break;
		}
		in.close();
	}

}
