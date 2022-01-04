package it.beije.pascal.rubrica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class RubricaCSV {

	public static void main(String[] args) throws IOException {
		
		List<Contatto> rubrica;
		
		rubrica = readContatti("/temp/rubrica.csv", "\t");
		
		stampaRubricaContatto(rubrica);
		
		boolean virgolette = virgolettePresenti(rubrica);
		
		System.out.println("Le virgolette sono presenti ? "+ virgolette);
		
		letturaContattiOrdine("/temp/rubrica.csv", "\t");
		
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
			
			while (bufferedReader.ready()) {
				row = bufferedReader.readLine();
			
				r = row.split(sep);
				contatto = new Contatto();
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
	
	
	public static List<Contatto> letturaContattiOrdine(String path, String sep)  throws IOException {
		
		List<Contatto> rows = new ArrayList<Contatto>();
		
		FileReader reader = null;
		BufferedReader bufferedReader = null;
		
		//contatori posizione campi
		int pCognome = 0;
		int pNome = 0;
		int pTelefono = 0;
		int pEmail = 0;
		int pNote = 0;
		
		try {
			
			reader = new FileReader(path);
			bufferedReader = new BufferedReader(reader);
			
			String row;
			Contatto contatto;
			String[] r;
			
			//lettura e analisi prima riga
			row = bufferedReader.readLine();
			
			r = row.split(sep);
			
			// posizione COGNOME
			if (r[0].equals("COGNOME"))
				pCognome = 0;
			else if (r[1].equals("COGNOME"))
				pCognome = 1;
			else if (r[2].equals("COGNOME"))
				pCognome = 2;
			else if (r[3].equals("COGNOME"))
				pCognome = 3;
			else
				pCognome = -1;
			
			// posizione NOME
			if (r[0].equals("NOME"))
				pNome = 0;
			else if (r[1].equals("NOME"))
				pNome = 1;
			else if (r[2].equals("NOME"))
				pNome = 2;
			else if (r[3].equals("NOME"))
				pNome = 3;
			else
				pNome = -1;
			
			// posizione TELEFONO
			if (r[0].equals("TELEFONO"))
				pTelefono = 0;
			else if (r[1].equals("TELEFONO"))
				pTelefono = 1;
			else if (r[2].equals("TELEFONO"))
				pTelefono = 2;
			else if (r[3].equals("TELEFONO"))
				pTelefono = 3;
			else
				pTelefono = -1;	
			
			// posizione EMAIL
			if (r[0].equals("EMAIL"))
				pEmail = 0;
			else if (r[1].equals("EMAIL"))
				pEmail = 1;
			else if (r[2].equals("EMAIL"))
				pEmail = 2;
			else if (r[3].equals("EMAIL"))
				pEmail = 3;
			else
				pEmail = -1;
			
			// posizione NOTE
			if (r[0].equals("NOTE"))
				pNote = 0;
			else if (r[1].equals("NOTE"))
				pNote = 1;
			else if (r[2].equals("NOTE"))
				pNote = 2;
			else if (r[3].equals("NOTE"))
				pNote = 3;
			else
				pNote = -1;
			
			
			while (bufferedReader.ready()) {
				row = bufferedReader.readLine();
				
				r = row.split(sep);
				
				
				contatto = new Contatto();
				if (pCognome != -1)
					contatto.setCognome(r[pCognome]);
				if (pNome != -1)
					contatto.setNome(r[pNome]);

				if (pTelefono != -1)
					contatto.setTelefono(r[pTelefono]);

				if (pEmail != -1)
					contatto.setEmail(r[pEmail]);

				if (pNote != -1)
					contatto.setNote(r[pNote]);
				
				System.out.println(contatto);
				
				rows.add(contatto);
			}
			
			
			
		}catch (IOException ioEx) {
			
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
		
		
		return null;
		
	}
	
	public static void stampaRubricaContatto(List<Contatto> contatti)
	{
		
		FileWriter writer = null;
		String stampa;
		
		try {
			
			File newFile = new File("/temp/rubricaContatto.csv");
			System.out.println("esiste ? " + newFile.exists());
			writer = new FileWriter(newFile);
			
			stampa = contatti.toString();
			
			writer.write(stampa);
			
			writer.flush();
			
		}catch (IOException ioEx) {
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
		
	public static boolean virgolettePresenti(List<Contatto> contatti)
	{
		
		
		for (Contatto c : contatti) {
			
			if (c.getNome().charAt(0) != '"' && c.getNome().charAt(c.getNome().length() - 1) != '"')
				return false;
			else if (c.getCognome().charAt(0) != '"' && c.getCognome().charAt(c.getCognome().length() - 1) != '"')
				return false;
			else if (c.getTelefono().charAt(0) != '"' && c.getTelefono().charAt(c.getTelefono().length() - 1) != '"')
				return false;
			else if (c.getEmail().charAt(0) != '"' && c.getEmail().charAt(c.getEmail().length() - 1) != '"')
				return false;
			else if (c.getNote().charAt(0) != '"' && c.getNote().charAt(c.getNote().length() - 1) != '"')
				return false;								
		}
		
		return true;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
