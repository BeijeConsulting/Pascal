package it.beije.pascal.rubrica;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RubricaCSV {

	public static void main(String[] args) throws IOException {
		readContatti("rubrica.csv", "\t");
	}

	public static List<Contatto> readContatti(String path, String sep) throws IOException {
		List<Contatto> rows = new ArrayList<Contatto>();
		FileReader reader = null;
		BufferedReader bufferedReader = null;
		
		try {
			reader = new FileReader(path);
			bufferedReader = new BufferedReader(reader);
			
			String row;
			Contatto contatto;
			String[] r;
			boolean virgolette;	//
			while (bufferedReader.ready()) {
				row = bufferedReader.readLine();
			
				r = row.split(sep);
				
				if(r[0].trim().charAt(0) == '"') {
					virgolette = true;
				}
				else {
					virgolette = false;
				}
				
				contatto = new Contatto();
				
				if(r[0].equalsIgnoreCase("nome")) {
					contatto.setNome(r[0]);
				}
				else if(r[0].equalsIgnoreCase("nome"))
				
				contatto.setCognome(r[0]);
				contatto.setNome(r[1]);
				contatto.setTelefono(r[2]);
				contatto.setEmail(r[3]);
				
				System.out.println(contatto);
				
				rows.add(contatto);
			}
			
		} catch (IOException ioEx) {
			ioEx.printStackTrace();
			throw ioEx;
		} finally {
			try {
				if (bufferedReader != null) {
					bufferedReader.close();
				}
				if (reader != null) {
					reader.close();
				}
			} catch (Exception fEx) {
				fEx.printStackTrace();
			}
		}
		
		return rows;
	}
}
