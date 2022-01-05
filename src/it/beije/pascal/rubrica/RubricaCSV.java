package it.beije.pascal.rubrica;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class RubricaCSV {

	public static void main(String[] args) throws IOException {
		readContatti("rubrica.csv", "\t"); // /temp/rubrica.csv
		
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
			
			row = bufferedReader.readLine();
			r = row.split(sep);
			String pos[] = search(r);
			contatto = addContatto(pos , r);
			System.out.println(contatto);
			rows.add(contatto);
			
			
			while (bufferedReader.ready()) {
				row = bufferedReader.readLine();
			
				r = row.split(sep);
				
				contatto = addContatto(pos , r);
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
	
	public static Boolean isSeparated(String[] r) {
		boolean check = false;
		for(int i=0; i<r.length; i++) {
			if((r[i].charAt(0) == '"') && (r[i].charAt(r[i].length() - 1) == '"')){
				check = true;
			}
			else {
				check = false;
				break;
			}
		}
		return check;
	}
	
	public static String[] search(String[] r) {
		String[] ind = new String[r.length];
		for(int i = 0; i<r.length; i++) {
			switch(r[i]) {
			   case "COGNOME":
				   ind[i] = ("COG" + i);
				   break;
			   case "NOME":
				   ind[i] = ("NOM" + i);
				   break;
			   case "TELEFONO":
				   ind[i] = ("TEL" + i);
				   break;
			   case "EMAIL":
				   ind[i] = ("EMA" + i);
				   break;
			   case "NOTE":
				   ind[i] = ("NOT" + i);
				   break;
				}
			
		}
		return ind;
	}
	
	public static Contatto addContatto(String[] pos, String[] r) {
		Contatto c = new Contatto();
		int ind;
		for(int i = 0; i<pos.length; i++) {
			switch(pos[i].toString().substring(0, 3)) {
			case "COG":
				ind =  Integer.valueOf(pos[i].toString().substring(3));
				c.setCognome(r[ind]);
				break;
			case "NOM":
				ind =  Integer.valueOf(pos[i].toString().substring(3));
				c.setNome(r[ind]);
				break;
			case "TEL":
				ind =  Integer.valueOf(pos[i].toString().substring(3));
				c.setTelefono(r[ind]);
				break;
			case "EMA":
				ind =  Integer.valueOf(pos[i].toString().substring(3));
				c.setEmail(r[ind]);
				break;
			case "NOT":
				ind =  Integer.valueOf(pos[i].toString().substring(3));
				c.setNote(r[ind]);
				break;
			}
		}
		return c;
		
	}
	
}
