package it.beije.pascal.rubrica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import it.beije.pascal.file.FileUtils;


public class RubricaCSV {

	final static String PATH = "./data/rubrica/rubrica.csv";
	final static String PATH_NEW = "./data/rubrica/nuova_rubrica.csv";
	public static final String STANDARD_SEPARATOR = "\t";
	
	public static void main(String[] args) throws IOException {
		
		List<Contatto> contatti = loadRubricaFromCSV(PATH, "\t");
		writeContatti(contatti, PATH_NEW, "\t");
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
				row = contattoToRow(sep, c);
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

	private static StringBuilder contattoToRow(String sep, Contatto c) {
		StringBuilder row;
		row = new StringBuilder()
				.append(c.getCognome() == null ? "" : c.getCognome()).append(sep)
				.append(c.getNome() == null ? "" : c.getNome()).append(sep)
				.append(c.getTelefono() == null ? "" : c.getTelefono()).append(sep)
				.append(c.getEmail() == null ? "" : c.getEmail()).append(sep)
				.append(c.getNote() == null ? "" : c.getNote()).append('\n');
		return row;
	}
	
	public static void addContatto(Contatto contatto) {
		File file = new File(PATH);
		List<String> oldFile = FileUtils.extractRows(file);
		FileWriter fw = null;
		try {
			String toAdd = contattoToRow(STANDARD_SEPARATOR, contatto).toString();
			fw = new FileWriter(file);
			for(String row : oldFile) {
				fw.write(row);
			}
			fw.write(toAdd);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(fw!=null)
				try {
					fw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
	}

	//for standard operations
	public static List<Contatto> loadRubricaFromCSV() throws IOException {
		return loadRubricaFromCSV(PATH, STANDARD_SEPARATOR);
		
	}

	public static void writeContatti(List<Contatto> rubrica) throws IOException {
		writeContatti(rubrica, PATH, STANDARD_SEPARATOR);
		
	}

	public static void removeContatto(Contatto contatto) {
		
		
	}
}	
