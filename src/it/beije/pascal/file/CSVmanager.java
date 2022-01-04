package it.beije.pascal.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// estrarre i metodi
// arraylist

// mettere tra doppi apici i campi

// invertire i campi come nome cognome email e telefono

public class CSVmanager {

	static List<String> readingFile(File file) {
		List<String> rows = new ArrayList<String>();
		FileReader reader = null;
		String row;

		try {
			reader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(reader);
			while (bufferedReader.ready()) {
				row = bufferedReader.readLine();
				rows.add(row);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (Exception fEx) {
				fEx.printStackTrace();
			}
		}

		return rows;
	}

	static void writeFile(File file, List<String> rows, String sorroundingString) {
		FileWriter writer = null;
		try {
			writer = new FileWriter(file);

			for (String r : rows) {
				
				String[] c = r.split("\t");

				StringBuilder newRow = new StringBuilder(sorroundingString).append(c[1])
						.append(sorroundingString + '#' + sorroundingString).append(c[0])
						.append(sorroundingString + '#' + sorroundingString).append(c[3])
						.append(sorroundingString + '#' + sorroundingString).append(c[2]).append(sorroundingString)
						.append('\n');

				writer.write(newRow.toString());	
				
			}
			writer.flush();

		} catch (IOException ioEx) {
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

	public static void main(String[] args) {

		File fileReader = new File("C:/Users/andre/git/Pascal/rubrica.csv");
		File fileWriter = new File("C:/Users/andre/git/Pascal/rubrica2.csv");

		List<String> rows = readingFile(fileReader);

		for (String string : rows) {
			System.out.println(string);
		}

		writeFile(fileWriter, rows, "\"");
		
		System.out.println("########################################");
		// Esercizio 2 
		/*
		Tree tree = new Tree("C:/Users/andre/OneDrive/Desktop/Prova");
		File maindir = new File("C:/Users/andre/OneDrive/Desktop/Prova");
		File arr[] = maindir.listFiles();
	    tree.RecursivePrint(arr, 0, 0);*/
		
		
		

	}

}
