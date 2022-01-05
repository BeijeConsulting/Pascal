package it.beije.pascal.rubrica;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class RubricaCSV {

	public static void main(String[] args) throws IOException {
		loadRubricaFromCSV("C:/Users/franc/git/Pascal/rubrica.csv", "\t");
	}

	public static List<Contatto> loadRubricaFromCSV(String path, String sep) throws IOException {
		List<Contatto> rows = new ArrayList<Contatto>();
		boolean firstLine = true;
		
		FileReader reader = null;
		BufferedReader bufferedReader = null;
		
		try {
			reader = new FileReader(path);
			bufferedReader = new BufferedReader(reader);
			String row;
			Contatto contatto;
			String[] r;
			int posCog=0, posNom=0, posTel=0, posEm=0, posNot=0;
			
			if(firstLine) {
				row = bufferedReader.readLine();
				r = row.toUpperCase().split(sep);
				contatto = new Contatto();
				for( int i =0; i < r.length; i++) {
					if(r[i].equals("COGNOME")) {contatto.setCognome(r[i]); posCog = i;}
					if(r[i].equals("NOME")) {contatto.setNome(r[i]); posNom = i;}
					if(r[i].equals("TELEFONO")) {contatto.setTelefono(r[i]); posTel = i;}
					if(r[i].equals("EMAIL")) {contatto.setEmail(r[i]); posEm = i;}
					if(r[i].equals("NOTE")) {contatto.setNote(r[i]); posNot = i;}
				}
				firstLine = false;
			}
			System.out.println(posEm);
			System.out.println(posTel);
			while (bufferedReader.ready()) {
				row = bufferedReader.readLine();
			
				r = row.split(sep);
				contatto = new Contatto();
				contatto.setCognome(r[posCog]);
				contatto.setNome(r[posNom]);
				contatto.setTelefono(r[posTel]);
				contatto.setEmail(r[posEm]);
				contatto.setNote(r[posNot]);
				
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
