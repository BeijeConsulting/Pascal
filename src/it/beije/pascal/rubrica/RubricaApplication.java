package it.beije.pascal.rubrica;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
//import java.util.ArrayList;
//import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class RubricaApplication {
	
	static Scanner scan = new Scanner(System.in);
	
	//private List<Contatto> contatti = new ArrayList<>();
	
	private static Connection getCon() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rubrica?serverTimezone=CET", "root", "baba");
		return con;
	}
	
	public static void listContacts() throws Exception { //vedi lista contatti, con possibilità di ordinare per nome e cognome a scelta
		Connection con = getCon();
		
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
	}
	 
	private static void lookForContact(String nome) throws Exception { //cercare un contatto
	
		Connection con = getCon();
		
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM contatti WHERE nome = '"+nome+"';");
		
		while(rs.next()) {
			System.out.println("sono qui");
			System.out.println("id: " + rs.getInt("id"));
			System.out.print("cognome: " + rs.getString("cognome"));
			System.out.println(", nome: " + rs.getString("nome"));
			System.out.print("telefono: " + rs.getString("telefono"));
			System.out.println(", email: " + rs.getString("email"));
			System.out.println("note: " + rs.getString("note") + "\n");
		}
	}
	
	private static void newContact() throws Exception { //cercare un contatto
		Connection con = getCon();
		
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM contatti");
	}
	
	private static void updateContact() throws Exception { //modificare un contatto
		Connection con = getCon();
		
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM contatti");
	}
	
	private static void deleteContact() throws Exception { //cancellare un contatto
		Connection con = getCon();
		
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM contatti");
	}
	
	private static void findAllDuplicate() throws Exception { //trovare i contatti duplicati
		Connection con = getCon();
		
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM contatti");
	}
	
	private static void mergeDuplicate() throws Exception { //unire i contatti duplicati
		Connection con = getCon();
		
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM contatti");
	}
	
	private static void askName() throws Exception {
		System.out.println("Inserisci il nome della persona che vuoi cercare: ");
		scan.next();
		String nome = scan.nextLine();
		nome = nome.trim();
		lookForContact(nome);
		
	}
	
	private static void showMenu() throws Exception {
		LocalDateTime ldt = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd - MM - yyyy   HH:mm ");
		System.out.println("---RUBRICA---\n" + ldt.format(dtf) + "\nScegli una delle seguenti operazioni:\n"
				+ "1. visualizza tutti i contatti\n"
				+ "2. cerca un contatto\n" 
				+ "3. crea un contatto\n"
				+ "4. modifica un contatto\n"
				+ "5. cancella un contatto\n"
				+ "6. trova contatti duplicati\n"
				+ "7. unisci contatti duplicati");
	
		int choice = scan.nextInt();
		
		switch(choice) {
			case 1: listContacts();
				break;
			case 2: askName();
				break;
			case 3: newContact();
				break;
			case 4: updateContact();
				break;
			case 5: deleteContact();
				break;
			case 6: findAllDuplicate();
				break;
			case 7: mergeDuplicate();
				break;
			default: System.out.println("Scegli un'opzione valida");
				showMenu();
				break;
		}
	}
	
	public static void main(String[] args) throws Exception {
		showMenu();
	}
}
