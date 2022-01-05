package it.beije.pascal.rubrica;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Gestore_Rubrica {

	public static void main(String[] args) {
		
		String cognome;
		String nome;
		String telefono;
		String email;
		String note;
		
		Contatto contattoProvvisorio;
		
		System.out.println("Benvenuto nella Rubrica");
		System.out.println("Premere 1 per visualizzare la lista dei contatti ordinata per nome");
		System.out.println("Premere 2 per visualizzare la lista dei contatti ordinata per cognome");
		System.out.println("Premere 3 per cercare un contatto");
		System.out.println("Premere 4 per inserire un nuovo contatto");
		System.out.println("Premere 5 per modificare un contatto esistente");
		System.out.println("Premere 6 per cancellare un contatto esistente");
		System.out.println("Premere 7 per trovare eventuali contatti duplicati");
		System.out.println("Premere 8 per unire eventuali contatti duplicati");
		System.out.println("Premere 0 per chiudere");
		
		String s = readKeyboard();
		
		int scelta = Integer.parseInt(s);
		
		switch(scelta){
		
		
		case 0: 
			break;
		case 1:
			System.out.println("Sei in caso 1");
			break;
		case 2:
			System.out.println("Sei in caso 2");
			break;
		case 3:
			System.out.println("Sei in caso 3");
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
			addContact(contattoProvvisorio);
			
			System.out.println(contattoProvvisorio.toString());
			
			
			break;
		case 5:
			System.out.println("Sei in caso 5");
			break;
		case 6:
			System.out.println("Sei in caso 6");
			break;
		case 7:
			System.out.println("Sei in caso 7");
			break;
		case 8:
			System.out.println("Sei in caso 8");
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
	
	
	// METODI PER RUBRICA
	
	public static void addContact(Contatto nuovo) {
		
		FileWriter writer = null;
		String stampa;
		
		try {
			
			File newFile = new File("/temp/Gestore_rubrica.csv");
			System.out.println("esiste ? " + newFile.exists());
			writer = new FileWriter(newFile, true);
			
			stampa = nuovo.toString();
			
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
