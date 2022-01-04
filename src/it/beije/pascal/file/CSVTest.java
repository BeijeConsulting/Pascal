package it.beije.pascal.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVTest {
	

	private static List<String> readFile(File file) {
		FileReader reader = null;
		List<String> rows = new ArrayList<String>();
		try {
			reader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(reader);
			String row;
			while (bufferedReader.ready()) {
				row = bufferedReader.readLine();
				rows.add(row);
				
				String[] r = row.split("\t");
				System.out.println("COGNOME : " + r[0]);
				System.out.println("NOME : " + r[1]);
				System.out.println("TELEFONO : " + r[2]);
				System.out.println("EMAIL : " + r[3]);
			} 
		}catch(IOException e) {
			System.out.println("Soos");
			}
		return rows;
		}

	private static void writeFile(File file, List<String> rows) {
		FileWriter writer = null;
		List<String> rows2 = rows;
		try {
		writer = new FileWriter(file);
		
		for (String r : rows) {
			String[] c = r.split("\t");
			
			StringBuilder newRow = new StringBuilder(c[0]).append(';')
					.append(c[1]).append(';')
					.append(c[2]).append(';')
					.append(c[3]).append('\n');
			
			writer.write(newRow.toString());
		}
		writer.flush();
		
	} catch (IOException ioEx) {
		ioEx.printStackTrace();
		}
	}
		
	private static void changeTitles(File file, List<String> rows) {
		FileWriter writer = null;
		try {
			writer = new FileWriter(file);
			for (String r : rows) {
				String[] c = r.split("\t");
			
				StringBuilder newRow = new StringBuilder(c[1]).append(';')
						.append(c[0]).append(';')
						.append(c[2]).append(';')
						.append(c[3]).append('\n');
			
				writer.write(newRow.toString());
			}
			writer.flush();
		}catch (IOException e) {
			System.out.println("Sees");
		}
	}
	
	public static void main(String[] args) {
		File file = new File("C:/Users/franc/git/Pascal/rubrica.csv");
		File newFile = new File("C:/Users/franc/git/Pascal/rubrica2.csv");
		List<String> rows = new ArrayList<String>();
		
		System.out.println("esiste ? " + file.exists());
		System.out.println("isDirectory ? " + file.isDirectory());
		
		rows = readFile(file);
		//writeFile(newFile, rows);
		changeTitles(newFile, rows);
	}


}
