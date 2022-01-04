package it.beije.pascal.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class CSVmanager {
	public static void main(String[] args) {
	
		List<String> fileLetto = new ArrayList<String>();
		
		fileLetto = readFile("/temp/rubrica.csv");  
		writeFile(fileLetto);
		
		printFileTokenizer("rubrica.csv");
		printFileSplit("rubrica.csv");
		
	}
	
	public static List<String> readFile(String percorso) {
		File file = new File(percorso);
		FileReader reader = null;
		List<String> rows = new ArrayList<String>();	
		
		try {
			reader = new FileReader(file);
			
			BufferedReader bufferedReader = new BufferedReader(reader);
			
			String row;
			while (bufferedReader.ready()) {
				row = bufferedReader.readLine();
				rows.add(row);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if (reader != null) {
					reader.close();
				}
			}catch (Exception fEx) {
				fEx.printStackTrace();
			}
			
			return rows;
			
		}	
	}
	
	public static void writeFile(List<String> fileLetto) {
		FileWriter writer = null;
		
		File newFile = new File("/temp/rubrica2.csv");
		
		try {
			writer = new FileWriter(newFile);
			
			for (String r : fileLetto) {
				String[] c = r.split("\t");
				
				StringBuilder newRow = new StringBuilder('"' + c[1] + '"').append(';')
						.append('"' + c[0] + '"').append(';')
						.append('"' + c[3] + '"').append(';')
						.append('"' + c[2] + '"').append('\n');
				
				
			}
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static void printFileTokenizer(String percorso) {
		
		FileReader reader = null;
		
		try {
			reader = new FileReader(percorso);
			
			BufferedReader bufferedReader = new BufferedReader(reader);
			String row;
			while (bufferedReader.ready()) {
				row = bufferedReader.readLine();
				StringTokenizer tokenizer = new StringTokenizer(row, "\t");
				System.out.println(tokenizer.countTokens());
				while (tokenizer.hasMoreElements()) {
					System.out.println(tokenizer.nextElement());
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void printFileSplit(String percorso) {
		
		FileReader reader = null;
		
		try {
			reader = new FileReader(percorso);
			
			BufferedReader bufferedReader = new BufferedReader(reader);
			String row;
			while (bufferedReader.ready()) {
				row = bufferedReader.readLine();
				String[] r = row.split("\t");
				System.out.println("COGNOME : " + r[0]);
				System.out.println("NOME : " + r[1]);
				System.out.println("TELEFONO : " + r[2]);
			    System.out.println("EMAIL : " + r[3]);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
