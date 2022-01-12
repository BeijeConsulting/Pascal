package it.beije.pascal.rubrica;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class RubricaCSV {

	public static void main(String[] args) throws IOException {
		List<Contatto> prova = readContatti("rubrica.csv", "\t"); // /temp/rubrica.csv
		System.out.print(prova.toString());
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
			
			int posNome = -1;
			int posCognome = -1;
			int posTelefono = -1;
			int posEmail = -1;
			int posNote = -1;
			
			row = bufferedReader.readLine();
			r = row.split(sep);
			
			for (int i = 0; i < r.length; i++) {
				switch(r[i]) {
				case "COGNOME":
					posCognome = i;
					break;
				case "NOME":
					posNome = i;
					break;
				case "TELEFONO":
					posTelefono = i;
					break;
				case "EMAIL":
					posEmail = i;
					break;
				case "NOTE":
					posEmail = i;
					break;
				} 
			}
			
			while (bufferedReader.ready()) {
				row = bufferedReader.readLine();
			
				r = row.split(sep);
				
				contatto = new Contatto();
				if(posNome != -1)
					contatto.setNome(r[posNome]);
				if(posCognome != -1)
					contatto.setCognome(r[posCognome]);
				if(posTelefono != -1)
					contatto.setTelefono(r[posTelefono]);
				if(posEmail != -1)
					contatto.setEmail(r[posEmail]);
				if(posNote != -1)
					contatto.setEmail(r[posNote]);
				
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
