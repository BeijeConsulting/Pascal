package it.beije.pascal.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class CSVmanager {

	public static void main(String[] args) {
		
		File file = new File("/javaFiles/rubrica.csv");
		System.out.println("esiste ? " + file.exists());
		System.out.println("isDirectory ? " + file.isDirectory());

		FileReader reader = null;
		FileWriter writer = null;
		List<String> rows = new ArrayList<String>();

		try {
			reader = new FileReader(file);//"/temp/rubrica.txt"
			
//			while (reader.ready()) {
//				System.out.print((char)reader.read());
//			}
			
			BufferedReader bufferedReader = new BufferedReader(reader);
			String row;
			while (bufferedReader.ready()) {
				row = bufferedReader.readLine();
				rows.add(row);
				//System.out.println(row);
				
//				StringTokenizer tokenizer = new StringTokenizer(row, "\t");
//				System.out.println(tokenizer.countTokens());
//				while (tokenizer.hasMoreElements()) {
//					System.out.println(tokenizer.nextElement());
//				}
				
//				String[] r = row.split("\t");
//				System.out.println("COGNOME : " + r[0]);
//				System.out.println("NOME : " + r[1]);
//				System.out.println("TELEFONO : " + r[2]);
//				System.out.println("EMAIL : " + r[3]);
			}

			
			System.out.println("rows size : " + rows.size());
			
			File newFile = new File("/temp/rubrica2.csv");
			System.out.println("esiste ? " + newFile.exists());
			
			writer = new FileWriter(newFile);
			
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
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
				if (reader != null) {
					reader.close();
				}
			} catch (Exception fEx) {
				fEx.printStackTrace();
			}
		}
		
	}

}
