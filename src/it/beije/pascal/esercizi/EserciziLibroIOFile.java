package it.beije.pascal.esercizi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class EserciziLibroIOFile {

	public static void main(String[] args) {
		
		Scanner tastiera = new Scanner(System.in);
		System.out.println("Inserisci il path di una cartella");
		String path = tastiera.nextLine();
		fileDiTestoOutput(path);
		
	}

	public static void fileDiTestoOutput(String path) {
		String nomeFile = path;
		PrintWriter outputStream = null;
		try {
			outputStream = new PrintWriter(nomeFile);
		}
		catch(FileNotFoundException e) {
			System.out.println("Errore nell'apertura del file " + nomeFile);
			System.exit(0);
		}

		System.out.println("Inserire tre righe di testo:");
		Scanner tastiera = new Scanner(System.in);
		for(int i=0; i<3; i++) {
			String riga = tastiera.nextLine();
			outputStream.println(riga);
		}
		outputStream.close();
		System.out.println("Le righe sono state scritte su : " + nomeFile);
	}

	public static void aggiungiAFileDiTesto() {
		PrintWriter outputStream = null;
		String nomeFile = "C:\\Users\\Padawan01\\OneDrive\\Desktop\\ciao\\Animali\\pesci\\trota.txt";

		try {
			outputStream = new PrintWriter(new FileOutputStream(nomeFile, true));
		}
		catch (FileNotFoundException e) {
			System.out.println("Errore nell'apertura del file " + nomeFile);
			System.exit(0);
		}

		outputStream.println("Ecco qua finalmente del testo scritto senza eliminare quello precedente");
		outputStream.close();
	}

	public static void fileDiTestoInputConScanner() {
		String nomeFile = "C:\\Users\\Padawan01\\OneDrive\\Desktop\\ciao\\Animali\\pesci\\trota.txt";
		Scanner inputStream = null;

		System.out.println("Il seguente file di testo " + nomeFile + "\ncontiene le seguenti righe:\n");

		try {
			inputStream = new Scanner(new File(nomeFile));
		}
		catch(FileNotFoundException e) {
			System.out.println("Errore nell'apertura del file " + nomeFile);
			System.exit(0);
		}

		while(inputStream.hasNextLine()) {
			String riga = inputStream.nextLine();
			System.out.println(riga);
		}
		inputStream.close();
	}

	public static void fileDiTestoInputConBufferedReader() {

		String nomeFile = "C:/Users/Padawan01/OneDrive/Desktop/ciao/Animali/pesci/trota.txt";
		BufferedReader inputStream = null;
		System.out.println("Il file " + nomeFile + "\ncontiene le seguenti righe\n");

		try {
			inputStream = new BufferedReader(new FileReader(nomeFile));
		}catch (FileNotFoundException e) {
			System.out.println("Problema nell'apertura del file");
		}
		
		try {
			String riga = inputStream.readLine();
			while(riga != null) {
				System.out.println(riga);
				riga = inputStream.readLine();
			}
			inputStream.close();
		}catch (IOException e) {
			System.out.println("Errore nella lettura del file");
		}
		
	}
	
	public static void letturaTransazioni() {
		String nomeFile = "C:\\Users\\Padawan01\\OneDrive\\Desktop\\ciao/transazioni.txt";
		try {
			Scanner inputStream = new Scanner(new File(nomeFile));
			//legge la prima riga ma la ignora, così saltiamo l'intestazione
			String riga = inputStream.nextLine();
			//vendite totali
			double totale = 0;
			
			//legge il resto del file riga per riga
			while(inputStream.hasNextLine()) {
				//riga contiene codice,quantità,prezzo,descrizione
				riga = inputStream.nextLine();
				//splitta la stringa in un array di stringhe in base al separatore
				String[]pezziDiRiga = riga.split(",");
				//estrae ogni elemento in una variabile opportuna
				String codice = pezziDiRiga[0];
				int quantita = Integer.parseInt(pezziDiRiga[1]);
				double prezzo = Double.parseDouble(pezziDiRiga[2]);
				String descrizione = pezziDiRiga[3];
				//stampa la registrazione 
				System.out.printf("Venduti %d di %s (codice: %s) a " + "€%1.2f l'uno.\n", quantita, descrizione, codice, prezzo);
				//calcola il totale
				totale += quantita * prezzo;
			}
			System.out.printf("Vendite totali: €%1.2f\n", totale);
			inputStream.close();
		} catch (FileNotFoundException e) {
			System.out.println("Impossibile trovare il file " + nomeFile);
		}catch (IOException e) {
			System.out.println("Errore nella lettura del file " + nomeFile);
		}
	}

}
