package it.beije.pascal.rubrica;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
//import java.util.ArrayList;
//import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class RubricaApplication {
	
	static Scanner scan = new Scanner(System.in);
	
	
	//METODO DRIVER E CONNESSIONE
	private static Connection getCon() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rubrica?serverTimezone=CET", "root", "baba");
		} catch(Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	
	//METODI PRIMARI
	
	//LISTA TUTTI I CONTATTI PRESENTI NEL DATABASE + ordinamento eventuale 
	public static void listContacts() { 
		
		Connection con = null;
		
		try {
			con = getCon();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM contatti");
			
			while(rs.next()) {
				System.out.println("id: " + rs.getInt("id"));
				System.out.print("cognome: " + rs.getString("cognome"));
				System.out.println(", nome: " + rs.getString("nome"));
				System.out.print("telefono: " + rs.getString("telefono"));
				System.out.println(", email: " + rs.getString("email"));
				System.out.println("note: " + rs.getString("note") + "\n");
			}
			
			System.out.println("Desideri ordinare questa lista di contatti per nome o per cognome? (s/n) ");
			char choice = (char) scan.nextInt();
			
			if(choice == 's') {
				System.out.println("Desideri un ordinamento in base ai nomi (1) o in base ai cognomi (2) ? ");
				int sortingChoice = scan.nextInt();
				switch(sortingChoice) {
				case 1: sortByNames();
					break;
				case 2: sortBySurnames();
					break;
				}
			}
			
			showMenu();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();	
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	//CERCA UN CONTATTO CON NOME E COGNOME
	private static void lookForContact(String nome, String cognome) { 
		
		Connection con = null;
		
		try {
			con = getCon();
			PreparedStatement psSel = con.prepareStatement("SELECT * FROM contatti WHERE nome = ? AND cognome = ?");
			psSel.setString(1, nome);
			psSel.setString(2, cognome);
			ResultSet rs = psSel.executeQuery();
			
			while(rs.next()) {
				System.out.println("id: " + rs.getInt("id"));
				System.out.print("cognome: " + rs.getString("cognome"));
				System.out.println(", nome: " + rs.getString("nome"));
				System.out.print("telefono: " + rs.getString("telefono"));
				System.out.println(", email: " + rs.getString("email"));
				System.out.println("note: " + rs.getString("note") + "\n");
			}
			showMenu();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();	
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	//CREA UN NUOVO CONTATTO
	private static void newContact(String cognome, String nome, String tel, String email, String note) {
		
		Connection con = null;
		
		try {
			con = getCon();		
			PreparedStatement psIns = con.prepareStatement("INSERT INTO contatti (cognome, nome, telefono, email, note) VALUES (?,?,?,?,?)");
			psIns.setString(1, cognome);
			psIns.setString(2, nome);
			psIns.setString(3, tel);
			psIns.setString(4, email);
			psIns.setString(5, note);
			psIns.executeUpdate();
			
			System.out.println("Nuovo contatto creato con successo!");
			showMenu();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();	
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	//AGGIORNA UN CONTATTO ESISTENTE
	private static void updateContact(int Id, String column, String newValue) {
		
		Connection con = null;
		
		try {
			con = getCon();
			PreparedStatement psUpd = con.prepareStatement("UPDATE contatti SET " + column + " = ? WHERE id = ?"); //impossibile mettere un comando SQL al posto di ? senza concatenazione (?)
			psUpd.setString(1, newValue);
			psUpd.setInt(2, Id);
			psUpd.executeUpdate();
			
			showMenu();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();	
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	//ELIMINA UN CONTATTO
	private static void deleteContact(int Id) { 
		
		Connection con = null;
		
		try {
			con = getCon();
			PreparedStatement psDel = con.prepareStatement("DELETE FROM contatti WHERE id = ?");
			psDel.setInt(1, Id);
			psDel.executeUpdate();
			
			showMenu();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();	
			} catch(Exception e) {
				e.printStackTrace();
			} 
		}
	}
	
	
	//TROVA I CONTATTI DUPLICATI
	private static void findAllDuplicate() { 
		
		Connection con = null;
		StringBuilder sb = new StringBuilder();
		List<String> list = new ArrayList<String>();
		List<String> duplicate = new ArrayList<String>();
		
		try {
			con = getCon();
			PreparedStatement st = con.prepareStatement("SELECT * FROM contatti");
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				sb.append(rs.getString("cognome") + " " + rs.getString("nome"));
				list.add(sb.toString());
				sb.delete(0, sb.length());
			}
			
			for(int i = 0; i < list.size(); i++) {
				String temp = list.get(i);
				for(int j = i; j < list.size(); j++) {
					if(temp.equalsIgnoreCase(list.get(j))) {
						duplicate.add(list.get(j));
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		showMenu();
	}
	
	private static void mergeDuplicate() { //unire i contatti duplicati
		//Connection con = getCon();
		
		showMenu();
	}
	
	
	
	//METODI SECONDARI
	
	//ORDINA I CONTATTI IN ORDINE ALFABETICO IN BASE AI NOMI
	private static void sortByNames() {
		try {
			List<Contatto> contacts = new ArrayList<>();
			Connection con = getCon();
			
			PreparedStatement pr = con.prepareStatement("SELECT * FROM contatti");
			ResultSet rs = pr.executeQuery();
			
			while(rs.next()) {
				Contatto contact = new Contatto();
				contact.setId(rs.getInt("id"));
				contact.setCognome(rs.getString("cognome"));
				contact.setNome(rs.getString("nome"));
				contact.setTelefono(rs.getString("telefono"));
				contact.setEmail(rs.getString("email"));
				contact.setNote(rs.getString("note"));
				
				contacts.add(contact);
			}
			
			contacts.sort(new SortName());
			
			for(Contatto cont : contacts) {
				System.out.println("id: " + cont.getId());
				System.out.print("cognome: " + cont.getCognome());
				System.out.println(", nome: " + cont.getNome());
				System.out.print("telefono: " + cont.getTelefono());
				System.out.println(", email: " + cont.getEmail());
				System.out.println("note: " + cont.getNote() + "\n");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void sortBySurnames() {
		try {
			List<Contatto> contacts = new ArrayList<>();
			Connection con = getCon();
			
			PreparedStatement pr = con.prepareStatement("SELECT * FROM contatti");
			ResultSet rs = pr.executeQuery();
			
			while(rs.next()) {
				Contatto contact = new Contatto();
				contact.setId(rs.getInt("id"));
				contact.setCognome(rs.getString("cognome"));
				contact.setNome(rs.getString("nome"));
				contact.setTelefono(rs.getString("telefono"));
				contact.setEmail(rs.getString("email"));
				contact.setNote(rs.getString("note"));
				
				contacts.add(contact);
			}
			
			contacts.sort(new SortSurname());
			
			for(Contatto cont : contacts) {
				System.out.println("id: " + cont.getId());
				System.out.print("cognome: " + cont.getCognome());
				System.out.println(", nome: " + cont.getNome());
				System.out.print("telefono: " + cont.getTelefono());
				System.out.println(", email: " + cont.getEmail());
				System.out.println("note: " + cont.getNote() + "\n");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//ID PER L'ELIMINAZIONE
	private static void askId() {	
		try {
			int IdToDelete;
			
			System.out.println("Seleziona l'Id del contatto che vuoi eliminare: ");
			IdToDelete = scan.nextInt();
			
			deleteContact(IdToDelete);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//INFORMAZIONI PER L'AGGIORNAMENTO
	private static void askUpdateInfo() {
		try {
			int contactId;
			String columnToUpd;
			String newValue;
			
			System.out.print("Seleziona l'Id del contatto che vuoi modificare: ");
			contactId = scan.nextInt();
			System.out.print("Seleziona la voce che vuoi modificare: ");
			scan.nextLine();
			columnToUpd = scan.nextLine();
			System.out.print("Seleziona il nuovo valore che vuoi dare alla voce " + columnToUpd + ": ");
			newValue = scan.nextLine();
			
			updateContact(contactId, columnToUpd, newValue);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//INFORMAZIONI PER LA CREAZIONE DI UN NUOVO CONTATTO
	private static void askNewContactInformation() {
		try {
			String nome;
			String cognome;
			String tel;
			String email;
			String note;
			
			System.out.println("\t--CREAZIONE NUOVO CONTATTO--\t");
			System.out.print("- nome: ");
			nome = scan.next();
			System.out.print("- cognome: ");
			cognome = scan.next();
			System.out.print("- telefono: ");
			tel = scan.next();
			System.out.print("- email: ");
			email = scan.next();
			System.out.print("- note: ");
			scan.nextLine();
			note = scan.nextLine();
			
			newContact(cognome, nome, tel, email, note);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//NOME PER LA RICERCA NEL DATABASE
	private static void askName() {
		try {
			String nome;
			String cognome;
			
			System.out.print("Inserisci il NOME della persona che vuoi cercare: ");
			nome = scan.next();
			
			System.out.print("Inserisci il COGNOME della persona che vuoi cercare: ");
			cognome = scan.next();
			
			System.out.println("");
			
			lookForContact(nome, cognome);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//MOSTRA IL MENU
	private static void showMenu() {
		try {
			System.out.println(
					"1. visualizza tutti i contatti\n"
					+ "2. cerca un contatto\n" 
					+ "3. crea un contatto\n"
					+ "4. modifica un contatto\n"
					+ "5. cancella un contatto\n"
					+ "6. trova contatti duplicati\n"
					+ "7. unisci contatti duplicati\n"
					+ "8. esci");
		
			int choice = scan.nextInt();
			
			switch(choice) {
				case 1: listContacts();
					break;
				case 2: askName();
					break;
				case 3: askNewContactInformation();
					break;
				case 4: askUpdateInfo();
					break;
				case 5: askId();
					break;
				case 6: findAllDuplicate();
					break;
				case 7: mergeDuplicate();
					break;
				case 8: System.exit(0);
					break;
				default: System.out.println("Scegli un'opzione valida");
					showMenu();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		try {
			LocalDateTime ldt = LocalDateTime.now();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd - MM - yyyy   HH:mm ");
			System.out.println("\t\n---RUBRICA---\n" + ldt.format(dtf) + "\n");
			
			showMenu();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
