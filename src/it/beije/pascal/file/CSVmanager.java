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
		
		File file = new File("\\Users\\Padawan01\\OneDrive\\Desktop\\nuovo.txt");	//questa � la classe File e serve per gestire i file
		System.out.println("esiste ? " + file.exists());
		System.out.println("isDirectory ? " + file.isDirectory());
		
//		ManipolaFile mf = new ManipolaFile();
//		mf.leggiFile(file);
		
//		List<String> rows = new ArrayList<>();
//		rows = mf.estrapolaFile(file);
		
//		mf.scriviFile(file);
		

		FileReader reader = null;	//FileReader a differenza di File � una classe fatta per leggere i file e nel costruttore vuole un File f
		FileWriter writer = null;	//FileWriter � fatta per scrivere i file e anche lei vuole nel costruttore un File f
		List<String> rows = new ArrayList<String>();

		try {	//metto dentro il try perch� File throws una checked exception e sono obbligato a gestirla o rilanciarla
			reader = new FileReader(file);	//"/temp/rubrica.txt"
			
//			while (reader.ready()) {
//				System.out.print((char)reader.read());
//			}
//			
			BufferedReader bufferedReader = new BufferedReader(reader);
			String row;
			while (bufferedReader.ready()) {
				row = bufferedReader.readLine();
				rows.add(row);
//				//System.out.println(row);
				
//				StringTokenizer tokenizer = new StringTokenizer(row, "\t");
//				System.out.println(tokenizer.countTokens());
//				while (tokenizer.hasMoreElements()) {
//					System.out.println(tokenizer.nextElement());
//				}
//				
//				String[] r = row.split("\t");
//				System.out.println("COGNOME : " + r[0]);
//				System.out.println("NOME : " + r[1]);
//				System.out.println("TELEFONO : " + r[2]);
//				System.out.println("EMAIL : " + r[3]);
			}

			System.out.println("rows size : " + rows.size());
			
//			File newFile = new File("/temp/rubrica2.csv");
			System.out.println("esiste ? " + file.exists());
			
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
