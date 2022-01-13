package it.beije.pascal.rubrica;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestoreRubrica {
	static DBConnection connection = new DBConnection();
		
		public void InsertAll(List<Contatto> listcont, DBConnection db) {
			for(Contatto c: listcont) {
				db.Insert(c);
			}
		}
		
		public static void InsertFromKb(List<Contatto> listcont) {
			Contatto cont = new Contatto();
			Scanner s = new Scanner(System.in);
			String st = null;
			
			System.out.println("Inserisci cognome:");
			st = s.next();//aggiunge cognome
			cont.setCognome(st);
			
			System.out.println("Inserisci nome:");
			st = s.next();// aggiunge nome
			cont.setNome(st);
			
			System.out.println("Inserisci telefono:");
			st = s.next();// aggiunge telefono
			cont.setTelefono(st);
			
			System.out.println("Inserisci e-mail:");
			st = s.next(); // aggiunge email
			cont.setEmail(st);
			
			System.out.println("Inserisci descrizione:");
			st = s.next(); //aggiunge note
			cont.setNote(st);
			s.close();
			
			listcont.add(cont);
			connection.Insert(cont);
		}
		
		public static void main(String[] args) throws Exception {
			XMLCSVmanager man = new XMLCSVmanager();
			Scanner sc = new Scanner(System.in);
			boolean quit = false;
			int choice;
			String fileXML = "C:/Users/franc/git/Pascal/rubrica.xml";
			String fileCSV = "C:/Users/franc/git/Pascal/rubrica3.csv";
			String newFileXML = "C:/Users/franc/git/Pascal/rubrica2.xml";
			String newFileCSV = "C:/Users/franc/git/Pascal/rubrica2.csv";
			List<Contatto> listcontatto = new ArrayList<Contatto>();
			List<Contatto> dupcontatto = new ArrayList<Contatto>();
			connection.Connection();
			
			try {
				listcontatto = connection.selectAll();
			} catch (Exception e) {
				System.out.println("GestoreRubrica Soos");
				e.printStackTrace();
			}
			//man.printContatti(listcontatto);
			
			while (!quit) {
				System.out.println("\n");
				System.out.println("--------Menu' principale--------");
				System.out.println("0: Visualizza intera lista");
				System.out.println("1: Cerca contatto");
				System.out.println("2: Inserisci nuovo contatto");
				System.out.println("3: Modifica contatto");
				System.out.println("4: Cancella contatto");
				System.out.println("5: Trova contatti duplicati");
				System.out.println("6: Unisci contatti duplicati");
				System.out.println("7: Chiudi applicazione");
				System.out.println("\n");
				choice = sc.nextInt();
				sc.nextLine();
				switch (choice) {
				case 0:
					man.printContatti(listcontatto);
					break;
				case 1: 
					System.out.println("Inserisci il cognome del contatto");
					Scanner s = new Scanner(System.in);
					String str = s.next();
					String searched = man.searchContatto(listcontatto, str).toString();
					System.out.println(searched);
					s.close();
					break;
				case 2:
					InsertFromKb(listcontatto);
					break;
				case 3:
					System.out.println("Inserisci il cognome del contatto da modificare");
					Scanner mod = new Scanner(System.in);
					str = mod.next();
					mod.close();
					break;
				case 4:
					System.out.println("Inserisci il cognome del contatto da eliminare");
					Scanner sca = new Scanner(System.in);
					String cognome = sca.next();
					connection.removeContatto(listcontatto, cognome);
					sca.close();
					break;
				case 5:
					List<Contatto> duplicates = connection.findDuplicateContatti(listcontatto);
					/*
					for(Contatto c: duplicates) {
						System.out.println(c.toString());
					}
					*/
					break;
				case 6:
					man.mergeContatti(dupcontatto,listcontatto);
					break;
				case 7:
					quit = true;
					break;
				   }



	}
		}
}
