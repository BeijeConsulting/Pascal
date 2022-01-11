package it.beije.pascal.rubrica;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import it.beije.pascal.file.XMLmanager;

public class Rubricamanager {

	public static final String PATH = "./rubrica.xml";

	public static void main(String[] args) throws Exception {

		Scanner scanner = new Scanner(System.in);
		List<Contatto> contatti = new ArrayList<Contatto>();
		int scelta = -1;

		do {
			menu();
			System.out.print("inserisci scelta : ");
			scelta = scanner.nextInt();
			scanner.nextLine();
		} while (scelta < 0 || scelta > 5);

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
			System.out.println("Elimina contatto: ");
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

			Contatto deletecontatto = new Contatto(cognome, nome, telefono, email, note);
			deleteContatto(deletecontatto);

			break;
		}

		case 5: {

			break;
		}

		case 6: {
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
		System.out.println("4 cancella contatto : ");
		System.out.println("5 trova contatti duplicati");
		System.out.println("6 unisci contatti duplicati");
		System.out.println("#######################");
	}

	public static List<Contatto> allContacts(String categoria) throws Exception {
		List<Contatto> contatti = XMLmanager.loadRubricaFromXML(PATH);

		StringBuilder stringBuilder = new StringBuilder("get")
				.append(categoria.toLowerCase().substring(0, 1).toUpperCase() + categoria.substring(1));

		Collections.sort(contatti, new Comparator<Contatto>() {
			Class<?> c = Class.forName("it.beije.pascal.rubrica.Contatto");

			public int compare(Contatto a1, Contatto a2) {
				Method method = null;
				String a = "", b = "";
				try {
					method = c.getDeclaredMethod(stringBuilder.toString());
					a = (String) method.invoke(a1);
					b = (String) method.invoke(a2);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
						| SecurityException | NoSuchMethodException e) {
					e.printStackTrace();
				}

				return a.compareTo(b);
			}
		});

		
		//JDBC
		
		
		
		return contatti;

	}

	public static List<Contatto> find(String s, String categoria) throws Exception {
		List<Contatto> contatti = XMLmanager.loadRubricaFromXML(PATH);
		List<Contatto> trovati = new ArrayList<Contatto>();

		Class<?> c = Class.forName("it.beije.pascal.rubrica.Contatto");

		StringBuilder stringBuilder = new StringBuilder("get")
				.append(categoria.toLowerCase().substring(0, 1).toUpperCase() + categoria.substring(1));

		for (Contatto cont : contatti) {
			Method method = c.getDeclaredMethod(stringBuilder.toString());
			String a = (String) method.invoke(cont);
			if (a.equals(s))
				trovati.add(cont);
		}

		
		// JDBC
		List<Contatto> trovatijdbc = new ArrayList<Contatto>();
		Class<?> c2 = Class.forName("it.beije.pascal.rubrica.Contatto");

		StringBuilder stringBuilder2 = new StringBuilder()
				.append(categoria.toLowerCase().substring(0, 1).toUpperCase() + categoria.substring(1));

		ResultSet rs = createConn()
				.executeQuery("SELECT * FROM contatti where " + stringBuilder2.toString() + " = '" + s + "';");

		while (rs.next()) {
			trovatijdbc.add(new Contatto(rs.getString("cognome"), rs.getString("nome"), rs.getString("telefono"),
					rs.getString("email"), rs.getString("note")));
		}

		System.out.println("######################");
		for (Contatto contatto : trovatijdbc) {
			System.out.println(contatto);
		}
		System.out.println("######################");

		return trovati;
	}

	public static void insert(Contatto c) throws Exception {
		List<Contatto> contatti = XMLmanager.loadRubricaFromXML(PATH);

		contatti.add(c);
		
		XMLmanager.writeRubricaXML(contatti, PATH);
		
		// JDBC
		int r = createConn().executeUpdate("INSERT INTO contatti VALUES (null, '" + c.getCognome() + "', '"
				+ c.getNome() + "', '" + c.getTelefono() + "', '" + c.getEmail() + "', '" + c.getNote() + "')");


	}

	public static void deleteContatto(Contatto c) throws Exception {
		List<Contatto> contatti = XMLmanager.loadRubricaFromXML(PATH);
		boolean contains = false;
		for (Contatto contatto : contatti) {
			if (contatto.equals(c)) {
				contains = true;
			}
		}
		if (contains) {
			contatti.remove(c);
			XMLmanager.writeRubricaXML(contatti, PATH);
		} else {
			System.out.println("nessun contatto trovato XML");
		}
		// JDBC
		int r = createConn().executeUpdate("DELETE FROM contatti WHERE cognome = '" + c.getCognome() + "' "
				+ "AND nome = '" + c.getNome() + "' AND telefono = '" + c.getTelefono() + "'" + "AND email = '"
				+ c.getEmail() + "'AND note = '" + c.getNote() + "'; ");

		if (r == 0)
			System.out.println("nessun contatto trovato JDBC");
	}

	public static void trovaContattiDuplicati() throws Exception {
		List<Contatto> contatti = XMLmanager.loadRubricaFromXML(PATH);

		List<Contatto> uniqueContatti = new ArrayList<Contatto>();

		if (contatti.size() == 0)
			return;

		for (Contatto contatto : contatti) {
			if (!uniqueContatti.contains(contatto))
				uniqueContatti.add(contatto);
		}

		XMLmanager.writeRubricaXML(contatti, PATH);
	}

	public static void unisciContatti() throws Exception {
		List<Contatto> contatti = XMLmanager.loadRubricaFromXML(PATH);

		List<Contatto> uniqueContatti = new ArrayList<Contatto>();

		if (contatti.size() == 0)
			return;

		for (Contatto contatto : contatti) {
			if (!uniqueContatti.contains(contatto))
				uniqueContatti.add(contatto);
		}

		XMLmanager.writeRubricaXML(contatti, PATH);
	}

	public static Statement createConn() {
		Statement statement = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/rubrica?serverTimezone=CET", "root", "Chinetti");
			statement = connection.createStatement();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		if (statement != null)
			return statement;
		else
			return null;

	}

}
