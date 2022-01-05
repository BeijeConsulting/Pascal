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
		List<Contatto> contatti = loadRubricaFromCSV("./data/rubrica/rubrica.csv", "\t");
		writeContatti(contatti, "./data/rubrica/nuova_rubrica.csv", "\t");
	}

	public static List<Contatto> loadRubricaFromCSV(String path, String sep) throws IOException {
		List<Contatto> rows = new ArrayList<Contatto>();
		
		FileReader reader = null;
		BufferedReader bufferedReader = null;
		
		try {
			reader = new FileReader(path);
			bufferedReader = new BufferedReader(reader);
			
			String row;
			Contatto contatto;
			String[] r;
			
			//First cycle: read order of fields
			int posCognome =-1, posNome =-1, posTel =-1, posEmail =-1, posNote =-1;
			if(bufferedReader.ready()) {
				row = bufferedReader.readLine();
				r = row.split(sep);
				for (int i = 0; i < r.length; i++) {
					if (r[i].equals("COGNOME")) posCognome = i; 
					else if (r[i].equals("NOME")) posNome = i; 
					else if (r[i].equals("TELEFONO")) posTel = i; 
					else if (r[i].equals("EMAIL")) posEmail = i; 
					else if (r[i].equals("NOTE")) posNote = i; 
				}
			}
			
			//read all occurrences
			while (bufferedReader.ready()) {
				row = bufferedReader.readLine();
				
				r = row.split(sep);
				contatto = new Contatto();
				if(posCognome != -1) contatto.setCognome(r[posCognome]);
				if(posNome != -1) 	contatto.setNome(r[posNome]);
				if(posTel != -1) 	contatto.setTelefono(r[posTel]);
				if(posEmail != -1) 	contatto.setEmail(r[posEmail]);
				if(posNote != -1) 	contatto.setNote(r[posNote]);
				
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
	
	public static void writeContatti(List<Contatto> contatti, String path, String sep) throws IOException {
		File file;
		FileWriter fileWriter = null;
		StringBuilder row;
		
		try {
			file = new File(path);
			fileWriter = new FileWriter(file);
			
			//Header row
			row = new StringBuilder()
					.append("COGNOME").append(sep)
					.append("NOME").append(sep)
					.append("TELEFONO").append(sep)
					.append("EMAIL").append(sep)
					.append("NOTE").append('\n');
			fileWriter.append(row.toString());
			
			//read rows
			for(Contatto c: contatti) {
				row = new StringBuilder()
						.append(c.getCognome() == null ? "" : c.getCognome()).append(sep)
						.append(c.getNome() == null ? "" : c.getNome()).append(sep)
						.append(c.getTelefono() == null ? "" : c.getTelefono()).append(sep)
						.append(c.getEmail() == null ? "" : c.getEmail()).append(sep)
						.append(c.getNote() == null ? "" : c.getNote()).append('\n');
				fileWriter.append(row.toString());
			}
		}catch (IOException IOEX) {
			IOEX.printStackTrace();
			throw IOEX;
		} finally {
			try {
				if(fileWriter != null) fileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw e;
			}
		}
	}
	
	public static void writeContattiLambda(List<Contatto> contatti, String path, String sep) throws IOException {
		File file;
		FileWriter fileWriter = null;
		StringBuilder row;
		
		
		
		try {
			file = new File(path);
			fileWriter = new FileWriter(file);
			
			//Header row
			row = new StringBuilder()
					.append("COGNOME").append(sep)
					.append("NOME").append(sep)
					.append("TELEFONO").append(sep)
					.append("EMAIL").append(sep)
					.append("NOTE").append('\n');
			fileWriter.append(row.toString());
			
			//read rows
			for(Contatto c: contatti) {
				row = new StringBuilder()
						.append(c.getCognome() == null ? "" : c.getCognome()).append(sep)
						.append(c.getNome() == null ? "" : c.getNome()).append(sep)
						.append(c.getTelefono() == null ? "" : c.getTelefono()).append(sep)
						.append(c.getEmail() == null ? "" : c.getEmail()).append(sep)
						.append(c.getNote() == null ? "" : c.getNote()).append('\n');
				fileWriter.append(row.toString());
			}
		}catch (IOException IOEX) {
			IOEX.printStackTrace();
			throw IOEX;
		} finally {
			try {
				if(fileWriter != null) fileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw e;
			}
		}
	}
}	
