package it.beije.pascal.esercizi.database;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class GestoreRubrica {

	public static void main(String[] args) throws Exception {

		Scanner tastiera = new Scanner(System.in);
		int scelta = 0;

		do {
			System.out.println("=== Menù ===");
			System.out.println("1. vedi lista contatti (con possibilità di ordinare per nome e cognome a scelta)\r\n"
					+ "2. cerca contatto\r\n"
					+ "3. inserisci nuovo contatto\r\n"
					+ "4. modifica contatto\r\n"
					+ "5. cancella contatto\r\n"
					+ "6. trova contatti duplicati\r\n"
					+ "7. unisci contatti duplicati");
			System.out.println("Scegli un'opzione: ");
			scelta = tastiera.nextInt();
			if(scelta<1 || scelta>7) {
				System.out.println("\n\nInserisci un numero compreso tra 1 e 7\n\n");
			}
		} while (scelta<1 || scelta>7);


		if(scelta == 1) {
			vediListaContatti();
		}
		else if(scelta == 2) {
			System.out.println("Inserisci il nome del contatto che vuoi cercare:");
			String nome = tastiera.next();
			cercaContatto(nome);
		}
		else if(scelta == 3) {
			
		}

		tastiera.close();
	}

	public static void cercaContatto(String nome) throws Exception{
		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;

		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rubrica?serverTimezone=CET", "root", "andrea23596");
			statement = connection.createStatement();
			rs = statement.executeQuery("SELECT * FROM contatti WHERE nome='" + nome + "'");
			if(rs.next()) {

				System.out.println("id : " + rs.getInt("idcontatti"));
				System.out.println("cognome : " + rs.getString("cognome"));
				System.out.println("nome : " + rs.getString("nome"));
				System.out.println("telefono : " + rs.getString("telefono"));
				System.out.println("email : " + rs.getString("email"));
				System.out.println("note : " + rs.getString("note"));
				System.out.println();

			}else {
				System.out.println("Contatto non trovato");
			}


		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				rs.close();
				statement.close();
				connection.close();
			} catch (Exception fEx) {
				fEx.printStackTrace();
			}
		}
	}

	public static void vediListaContatti() throws Exception{
		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;

		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rubrica?serverTimezone=CET", "root", "andrea23596");
			statement = connection.createStatement();
			rs = statement.executeQuery("SELECT * FROM contatti");

			while (rs.next()) {
				System.out.println("id : " + rs.getInt("idcontatti"));
				System.out.println("cognome : " + rs.getString("cognome"));
				System.out.println("nome : " + rs.getString("nome"));
				System.out.println("telefono : " + rs.getString("telefono"));
				System.out.println("email : " + rs.getString("email"));
				System.out.println("note : " + rs.getString("note"));
				System.out.println();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				rs.close();
				statement.close();
				connection.close();
			} catch (Exception fEx) {
				fEx.printStackTrace();
			}
		}
	}

}
