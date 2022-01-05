package it.beije.pascal.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import it.beije.pascal.util.FileUtil;

public class ManagerCSV {

	public static void main(String[] args) {		
		String path = "/Users/ema29/JavaFile/Esercizio1/lettura.txt";
		String path2 = "/Users/ema29/JavaFile/Esercizio1/scrittura.txt";
		
		
		
		System.out.println("==== Lettura ====");
		scriviFile(leggiFile(path), path2);
	}

	
	private static List<String> leggiFile(String path) {
		
		List<String> rows = new ArrayList<>();
		FileReader reader = null;

		try {
			reader = new FileReader(path);
			// Componente per lettura del file riga per riga
			BufferedReader bufferedReader = new BufferedReader(reader);
			// Stringa per salvataggio righe
			String row;
			while (bufferedReader.ready()) {
				// Lettura riga per riga
				row = bufferedReader.readLine();
				rows.add(row);
			}
			for (String r : rows) {
				System.out.println(r);
			}

		} catch (IOException e) {
			e.printStackTrace();

		} finally {
			FileUtil.closeReader(reader);
		}
		return rows;
	}

	private static void scriviFile(List<String> rows, String path) {
		System.out.println("\n==== Scrittura ====");
		FileWriter writer = null;
		StringBuilder newRow = null;
		try {
			writer = new FileWriter(path);
			for (String row : rows) {
				String[] rowsArray = row.split(";");
				//System.out.println("rowsArray: " + rowsArray.length);

				newRow = new StringBuilder(rowsArray[1]).append('$');
				newRow.append(rowsArray[0]).append('$');
				newRow.append(rowsArray[2]).append('$');
				newRow.append(rowsArray[3]).append('\n');
				writer.write(newRow.toString());
			}

			writer.flush();
			leggiFile(path);
		} catch (IOException e) {
			e.printStackTrace();

		} finally {
			FileUtil.closeWriter(writer);
		}
	}

	

}
