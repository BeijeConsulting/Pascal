package it.beije.pascal.rubrica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Gestore_Rubrica {

	public static void main(String[] args) throws Exception {
		
		String cognome;
		String cognome2;
		String nome;
		String telefono;
		String email;
		String note;
		String pathCSV = "C:/temp/rubrica - cognome.csv";
		
		Contatto contattoProvvisorio;
		List<Contatto> rubrica = new ArrayList<Contatto>();
		
		System.out.println("Benvenuto nella Rubrica");
		System.out.println("Premere 1 per visualizzare la lista dei contatti ordinata per nome");
		System.out.println("Premere 2 per visualizzare la lista dei contatti ordinata per cognome");
		System.out.println("Premere 3 per cercare un contatto");
		System.out.println("Premere 4 per inserire un nuovo contatto");
		System.out.println("Premere 5 per modificare un contatto esistente");
		System.out.println("Premere 6 per cancellare un contatto esistente");
		System.out.println("Premere 7 per trovare eventuali contatti duplicati");
		System.out.println("Premere 8 per unire eventuali contatti duplicati");
		System.out.println("Premere 9 per scrivere la rubrica dal DB a CSV e XML");
		System.out.println("Premere 10 per scrivere la rubrica da un file CSV al Database");
		System.out.println("Premere 0 per chiudere");
		
		String s = readKeyboard();
		
		int scelta = Integer.parseInt(s);
		
		switch(scelta){
		
		
		case 0: 
			break;
			
		case 1:
			
//			rubrica = readRubrica("C:/temp/Gestore_Rubrica.csv",",");
			
			rubrica = ConnectionDB.trovaRubricaDB();			
			rubrica = sortRubricaNome(rubrica);
			visualRubrica(rubrica);
			
			break;
			
		case 2:
			
//			rubrica = readRubrica("C:/temp/Gestore_Rubrica.csv",",");
			
			rubrica = ConnectionDB.trovaRubricaDB();
			rubrica = sortRubricaCognome(rubrica);
			visualRubrica(rubrica);
			
			break;
		case 3:
			
			System.out.println("Inserire il cognome del contatto da cercare");
			cognome = readKeyboard();
			
			contattoProvvisorio = ConnectionDB.cercaCognomeDB(cognome);
			visualContatto(contattoProvvisorio);
			
			break;
			
		case 4:
			
			System.out.println("\n");
			System.out.println("Inserisci il Cognome del nuovo contatto");
			cognome = readKeyboard();
			System.out.println("Inserisci il Nome del nuovo contatto");
			nome = readKeyboard();
			System.out.println("Inserisci il numero di telefono del nuovo contatto");
			telefono = readKeyboard();
			System.out.println("Inserisci l'email del nuovo contatto");
			email = readKeyboard();
			System.out.println("Inserisci le note per il nuovo contatto");
			note = readKeyboard();
			
			contattoProvvisorio = buildContact(cognome, nome, telefono, email, note);
//			addContact(contattoProvvisorio);
			
			ConnectionDB.addDB(contattoProvvisorio);
			
			System.out.println(contattoProvvisorio.toString());
			
			break;
			
		case 5:
			
			System.out.println("Inserire il Cognome del Contatto da Modificare");
			cognome = readKeyboard();
			System.out.println("Inserire il nuovo Cognome");
			cognome2 = readKeyboard();
			
			ConnectionDB.updateDB(cognome, cognome2);		
			
			break;
			
		case 6:
			
			System.out.println("Inserire il cognome del contatto da eliminare");
			cognome = readKeyboard();
			
			ConnectionDB.deleteFromDB(cognome);
			
			break;
			
		case 7:
			
			rubrica=ConnectionDB.searchDuplicateDB();
			visualRubrica(rubrica);
			
			break;
		case 8:
			
			System.out.println("Sei in caso 8");
			
			break;
		case 9:
			
			rubrica = ConnectionDB.trovaRubricaDB();
			scriviRubricaCSV(rubrica);
			
			
			break;
			
		case 10:
			
			rubrica=readRubrica(pathCSV,";");
			ConnectionDB.addListDB(rubrica);
			
			break;
			
		
		}
		
	}
	
	// UTILITY
	

	public static String readKeyboard() {
		
		Scanner scanner = new Scanner(System.in);
		String st = scanner.next();
		// scanner.close();
		
		return st;
	}
	
	
	public static Contatto buildContact(String cognome, String nome, String telefono, String email, String note) {
		
		Contatto c = new Contatto();
		
		c.setNome(nome);
		c.setCognome(cognome);
		c.setTelefono(telefono);
		c.setEmail(email);
		c.setNote(note);
		
		return c;
		
	}
	
	
	public static List<Contatto> sortRubricaNome(List<Contatto> rubrica)
	{
		
		ArrayList<Contatto> ordine = new ArrayList<Contatto>(rubrica);
		
		/*
		System.out.println("Ordine iniziale");
		System.out.println(ordine);
		System.out.println("\n");
		*/
		
		ordine.sort(new OrdinaNome());
		
		/*
		System.out.println("Ordine alfabetico per Nome");
		System.out.println(ordine);
		System.out.println("\n");
		*/
		return ordine;
		
	}
	
	public static List<Contatto> sortRubricaCognome(List<Contatto> rubrica)
	{
		
		ArrayList<Contatto> ordine = new ArrayList<Contatto>(rubrica);
		
		/*
		System.out.println("Ordine iniziale");
		System.out.println(ordine);
		System.out.println("\n");
		*/
		
		ordine.sort(new OrdinaCognome());
		
		/*
		System.out.println("Ordine alfabetico per Nome");
		System.out.println(ordine);
		System.out.println("\n");
		*/
		return ordine;
		
	}
	
	public static void visualRubrica(List<Contatto> rubrica)
	{
		
		
		for(Contatto c : rubrica) {
			
			
			System.out.println(c.getCognome());
			System.out.println(c.getNome());
			System.out.println(c.getTelefono());
			System.out.println(c.getEmail());
			System.out.println(c.getNote());
			
		}
		
	}
	
	public static void visualContatto(Contatto c) {
		
		System.out.println(c.getId());
		System.out.println(c.getCognome());
		System.out.println(c.getNome());
		System.out.println(c.getTelefono());
		System.out.println(c.getEmail());
		System.out.println(c.getNote());
		
	}
	
	// METODI PER CSV
	
	public static void addContact(Contatto nuovo) {
		
		FileWriter writer = null;
		String stampa;
		
		try {
			
			File newFile = new File("/temp/Gestore_rubrica.csv");
			System.out.println("esiste ? " + newFile.exists());
			writer = new FileWriter(newFile, true);
			
			stampa = nuovo.toString();
			
			writer.write(stampa);
			writer.write("\n");
			
			writer.flush();
			
		}catch (IOException ioEx) {
			ioEx.printStackTrace();
			
		} finally {
			
			try {
				
				if (writer != null) {
					writer.close();
				}
				
			} catch (Exception fEx) {
				fEx.printStackTrace();
			}
		}
		
	}
	
	public static List<Contatto> readRubrica(String path, String sep) throws IOException {
		
		
		List<Contatto> rows = new ArrayList<Contatto>();

		FileReader reader = null;
		BufferedReader bufferedReader = null;

		try {

			reader = new FileReader(path);
			bufferedReader = new BufferedReader(reader);

			String row;
			Contatto contatto;
			String[] r;

			while (bufferedReader.ready()) {
				
				row = bufferedReader.readLine();
				
				r = row.split(sep,-1);
				
				contatto = new Contatto();
				contatto.setCognome(r[0]);
				contatto.setNome(r[1]);
				contatto.setTelefono(r[2]);
				contatto.setEmail(r[3]);

				rows.add(contatto);
			}

		} catch (IOException ioEx) {
			ioEx.printStackTrace();
			throw ioEx;

		} finally {

			try {
				if (bufferedReader != null) {
					bufferedReader.close();
				}
				if (reader != null) {
					reader.close();
				}
			} catch (Exception fEx) {
				fEx.printStackTrace();
			}
		}
		return rows;
		
	}
	
	public static void scriviRubricaCSV(List<Contatto> contatti)
	{
		
		FileWriter writer = null;
		String stampa;
		
		try {
			
			File newFile = new File("/temp/rubricaGrande.csv");
			System.out.println("esiste ? " + newFile.exists());
			writer = new FileWriter(newFile);
			
			stampa = contatti.toString() + "\n";
			
			writer.write(stampa);
			
			writer.flush();
			
		}catch (IOException ioEx) {
			ioEx.printStackTrace();
			
		} finally {
			
			try {
				
				if (writer != null) {
					writer.close();
				}
				
			} catch (Exception fEx) {
				fEx.printStackTrace();
			}
		}
		
		
		
	}
}