package it.beije.pascal.rubrica;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import it.beije.pascal.file.HDBmanager;
import it.beije.pascal.file.JDBCmanager;
import it.beije.pascal.file.XMLmanager;

public class Rubricamanager {

	

	public static void main(String[] args) throws Exception {

		Scanner scanner = new Scanner(System.in);
		List<Contatto> contatti = new ArrayList<Contatto>();
		int scelta = -1;

		do {
			menu();
			System.out.print("inserisci scelta : ");
			scelta = scanner.nextInt();
			scanner.nextLine();
		} while (scelta < 0 || scelta > 7);

		switch (scelta) {
		case 0:
			System.exit(0);
		case 1: {
			System.out.print("inserisci categoria per la quale ordinare : (cognome,nome,telefono,email,note) --> ");
			String categoria = scanner.nextLine();
			contatti = allContacts(categoria);

			for (Contatto contatto : contatti) {
				System.out.println(contatto);
			}

			break;
		}

		case 2: {
			System.out.print("inserisci categoria per la quale cercare : (cognome,nome,telefono,email,note) --> ");
			String categoria = scanner.nextLine();
			System.out.print("inserisci cosa ricercare --> ");
			String s = scanner.nextLine();

			contatti = find(s, categoria);

			if (contatti.size() > 0) {
				for (Contatto contatto : contatti) {
					System.out.println(contatto);
				}
			} else
				System.out.println("Nessun contatto con queste informazioni");
			break;
		}
		case 3: {
			System.out.println("Inserimento nuovo contatto: ");
			System.out.print("inserisci cognome --> ");
			String cognome = scanner.nextLine();
			System.out.print("inserisci nome --> ");
			String nome = scanner.nextLine();
			System.out.print("inserisci telefono --> ");
			String telefono = scanner.nextLine();
			System.out.print("inserisci email --> ");
			String email = scanner.nextLine();
			System.out.print("inserisci note --> ");
			String note = scanner.nextLine();

			Contatto newcontatto = new Contatto(cognome, nome, telefono, email, note);
			insert(newcontatto);

			break;
		}
		case 4: {
			System.out.println("Modifica contatto: ");
			System.out.print("inserisci id --> ");
			int id = scanner.nextInt();
			scanner.nextLine();
			System.out.print("inserisci categoria --> ");
			String categoria = scanner.nextLine();
			System.out.print("inserisci nuovo valore --> ");
			String nuovoValore = scanner.nextLine();
			
			updateContatto(id, categoria, nuovoValore);
			
			break;
		}

		case 5: {
			System.out.println("Elimina contatto: ");
			System.out.print("inserisci id --> ");
			int id = scanner.nextInt();
			scanner.nextLine();
			System.out.print("inserisci cognome --> ");
			String cognome = scanner.nextLine();
			System.out.print("inserisci nome --> ");
			String nome = scanner.nextLine();
			System.out.print("inserisci telefono --> ");
			String telefono = scanner.nextLine();
			System.out.print("inserisci email --> ");
			String email = scanner.nextLine();
			System.out.print("inserisci note --> ");
			String note = scanner.nextLine();

			Contatto deletecontatto = new Contatto(id, cognome, nome, telefono, email, note);
			deleteContatto(deletecontatto);

			break;
		}

		case 6: {
			System.out.println("Contatti duplicati: ");
			Set<Contatto>  c = trovaContattiDuplicati();
			for (Contatto contatto : c) {
				System.out.println(contatto);
			}
			break;
		}

		case 7: {
			System.out.println("Unisci contatti : ");
			unisciContatti();
			break;
		}

		default:
			System.out.println("default");
		}
	}

	private static void menu() {
		System.out.println("#######################");
		System.out.println("Scelta : ");
		System.out.println("1 contatti ordinati : ");
		System.out.println("2 cerca contatto : ");
		System.out.println("3 inserisci nuovo contatto : ");
		System.out.println("4 modifica contatto : ");
		System.out.println("5 cancella contatto : ");
		System.out.println("6 trova contatti duplicati");
		System.out.println("7 unisci contatti duplicati");
		System.out.println("#######################");
	}

	public static List<Contatto> allContacts(String categoria) throws Exception {	
		
		//return JDBCmanager.sortCategoriaJDBC(categoria);
		
		//return JDBCmanager.sortCategoriaJDBCPrepareStamtement(categoria);
		
		//return XMLmanager.allContacts(categoria);
		
		return HDBmanager.sortCategoriaHDB(categoria);
	}

	public static List<Contatto> find(String s, String categoria) throws Exception {
		
		//return JDBCmanager.findJDBCPrepareStamtement(s, categoria);
		
		//return JDBCmanager.findJDBC(s, categoria);
		
		//return XMLmanager.find(s, categoria);
		
		return HDBmanager.findHDB(s, categoria);
	}

	public static void insert(Contatto c) throws Exception {

		//XMLmanager.insert(c);
		
		//JDBCmanager.insertJDBCPrepareStamtement(c);
		
		//JDBCmanager.insertJDBC(c);
		
		HDBmanager.insertHDB(c);
	}
	
	public static void updateContatto(int id, String categoria, String val) throws SQLException {
		
		// JDBCmanager.updateContattoJDBC(id, categoria, val);
		
		// JDBCmanager.updateContattoJDBCPrepareStamtement(id, categoria, val);
		
		HDBmanager.updateContattoHDB(id, categoria, val);
		
	}

	public static void deleteContatto(Contatto c) throws Exception {
		
		// XMLmanager.deleteContatto(c);
		
		// JDBCmanager.deleteContattoJDBC(c);
		
		//JDBCmanager.deleteContattoJDBCPrepareStamtement(c);
		
		HDBmanager.deleteHDB(c.getId());
	}

	public static Set<Contatto> trovaContattiDuplicati() throws Exception {
		return JDBCmanager.trovaContattiDuplicatiJDBC();
	}

	public static void unisciContatti() throws Exception {
		JDBCmanager.unisciContattiDuplicatiJDBC();
	}



}
