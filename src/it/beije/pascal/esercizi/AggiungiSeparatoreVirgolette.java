package it.beije.pascal.esercizi;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class AggiungiSeparatoreVirgolette {
	
	public static void aggiungiSeparatoreVirgolette(String path) {
		BufferedReader inputStream = null;
		try {
			inputStream = new BufferedReader(new FileReader(path));
		} catch (FileNotFoundException e) {
			System.out.println("Non è stato possibile trovare il file " + path);
		}
		System.out.println("Il file "+ path + "\ncontiene le seguenti righe\n");
		String riga;
		List<String> righe = new ArrayList<String>();
		List<String> righeModificate = new ArrayList<String>();
		try {
			riga = inputStream.readLine();
			while(riga != null) {
				System.out.println(riga);
				righe.add(riga);
				riga = inputStream.readLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("\nE dopo l'aggiunta del separatore virgolette le righe sono le seguenti\n");
		for(String elemento : righe) {
			String[]elementi = elemento.split("\t");
			StringBuilder sb = new StringBuilder();
			for(int i=0; i<elementi.length; i++) {
				sb.append('"' + elementi[i] + '"' + "\t");
			}
//			System.out.println(sb);
			righeModificate.add(sb.toString());
		}
		for(String s : righeModificate) {
			System.out.println(s);
		}
		
		PrintWriter outputStream = null;
		try {
			outputStream = new PrintWriter(new FileOutputStream(path, true));
		} catch (FileNotFoundException e) {
			System.out.println("Non è stato possibile trovare il file " + path);
		}
		for(String s : righeModificate) {
			outputStream.println(s);
		}
		outputStream.close();
		System.out.println("\nHo aggiunto al file " + path + " l'elenco con le virgolette aggiunte!");
	}

}
