package it.beije.pascal.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

public class ManipolaFile {
	
	public void leggiFile(File f) {
		
		FileReader reader = null;
		
		try {
			reader = new FileReader(f);
			
			while (reader.ready()) {
				System.out.print((char)reader.read());
			}
			
		} catch (IOException ioEx) {
			ioEx.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (Exception fEx) {
				fEx.printStackTrace();
			}
		}
		
	}
	
public ArrayList<String> estrapolaFile(File f) {
		
		FileReader reader = null;
		ArrayList<String> arr = new ArrayList<>();
		
		try {
			reader = new FileReader(f);
			
			BufferedReader bufferedReader = new BufferedReader(reader);
			String row;
			
			while(bufferedReader.ready()) {
				row = bufferedReader.readLine();
				arr.add(row);
//				System.out.println(row);
			}
			
		} catch (IOException ioEx) {
			ioEx.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (Exception fEx) {
				fEx.printStackTrace();
			}
		}
		
		return arr;
		
	}

public void scriviFile(File f) {
	
	FileReader reader = null;
	ArrayList<String> arr = new ArrayList<>();
	FileWriter writer = null;
	
	try {
		reader = new FileReader(f);
		writer = new FileWriter(f);
		
		BufferedReader bufferedReader = new BufferedReader(reader);
		String row;
		while(bufferedReader.ready()) {
			row = bufferedReader.readLine();
			arr.add(row);
		}
		for(String r : arr) {
			String[]c = r.split("\t");	//con il metodo split separiamo la stringa in più stringhe nei punti in cui trovare il carattere tab!
			
			StringBuilder newRow = new StringBuilder(c[0]).append(';')
					.append(c[1]).append(';')
					.append(c[2]).append(';')
					.append(c[3]).append("\n");	//prima abbiamo quindi spacchettato le Stringhe in modo da togliere il separatore tab
												//ora andiamo a creare uno StringBuilder introducendo come separatore il ;
			
			writer.write(newRow.toString());	//in questo modoandiamo a sovrascrivere il vecchio file con questi comandi!!!
		}
		writer.flush();	//questo comando ci serve per salvare le modifiche
		
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
