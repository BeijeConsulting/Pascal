package it.beije.pascal.esercizi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class CSVutil {
	
	public static void leggiFileReader(File f) throws IOException{

		FileReader lettore = new FileReader(f);

		while(lettore.ready()) {
			System.out.print((char)lettore.read());	
		}

	}

	public static List<String> ottieniRighe(File f) throws IOException{

		FileReader lettore = new FileReader(f);
		BufferedReader lettoreRighe = new BufferedReader(lettore);
		List<String> rows = new ArrayList<String>();

		while(lettoreRighe.ready()) {
			rows.add(lettoreRighe.readLine());
		}

		return rows;
	}

	public static List<String> ottieniCampiTokenizer(File f) throws IOException{
		List<String> campo = new ArrayList<String>();
		FileReader lettore = new FileReader(f);
		BufferedReader lettoreRighe = new BufferedReader(lettore);

		while(lettoreRighe.ready()) {
			StringTokenizer tokenizer = new StringTokenizer(lettoreRighe.readLine(), "\t");
			//			System.out.println(tokenizer.countTokens());
			while (tokenizer.hasMoreElements()) {
				campo.add(tokenizer.nextElement().toString());
			}
		}

		return campo;
	}

	public static List<String> ottieniCampiStringSplit(File f) throws IOException{
		List<String> campo = new ArrayList<String>();
		FileReader lettore = new FileReader(f);
		BufferedReader lettoreRighe = new BufferedReader(lettore);
		String row;

		while(lettoreRighe.ready()) {
			row = lettoreRighe.readLine();
			String[] r = row.split("\t");
			for(String s : r) {
				campo.add(s);
			}
		}

		return campo;
	}

	public static void scriviFile(File f) throws IOException{
		FileWriter writer = new FileWriter(f);
		FileReader reader = new FileReader(f);
		BufferedReader buffered = new BufferedReader(reader);
		List<String> rows = new ArrayList<String>();
		
		while(buffered.ready()) {
			rows.add(buffered.readLine());
		}
		
		for (String r : rows) {
			String[] c = r.split("\t");

			StringBuilder newRow = new StringBuilder(c[0]).append(';')
					.append(c[1]).append(';')
					.append(c[2]).append(';')
					.append(c[3]).append('\n');

			writer.write(newRow.toString());
		}
		writer.flush();
		System.out.println("File sovrascritto :(");
	}

}
