package it.beije.pascal.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Reader {
	
	//metodo che legge file e ritorna array di stringhe (SENZA BUFFEREDREADER)
	public String[] readFromFile(File f) throws IOException {
		
		String[] res = null;
		StringBuilder sb = new StringBuilder();
		ArrayList<String> strArr = new ArrayList<String>();
		FileReader fr = null;
		
		try {
			
			fr = new FileReader(f);
			while(fr.ready()) {
				char c = (char)fr.read();
				sb.append(c);
				if(c == 32) { //ogni stringa è separata da uno spazio
					strArr.add(sb.toString());
					sb.delete(0, sb.length());
				}
			}
			
			strArr.add(sb.toString()); //aggiunge anche ultima stringa (che non ha alla fine uno spazio)
			
			res = new String[strArr.size()];
			res = strArr.toArray(res);
			
		} catch(IOException ioEx) {
			
			ioEx.printStackTrace();
			
		} finally {
			
			fr.close();
			
		}
		
		return res;
	}
	
	//lettura da file con BufferedReader
	public String[] readFromFile(File f, boolean buffer) throws IOException { 
		
		String[] res = null;
		FileReader fr = null;
		BufferedReader br = null;
		List<String> strList = new ArrayList<>();
		StringBuilder sb;
		
		try {
			fr = new FileReader(f);
			br = new BufferedReader(fr);
			
			while(br.ready()) {
				String temp = br.readLine();
				sb = new StringBuilder(temp);
				
				strList.add(temp);
				
				
//				String[] splitted = temp.split("\t");
//				for(String str : splitted) {
//					strList.add(str);
//				}
//			}
			}
			
			res = new String[strList.size()];
			res = strList.toArray(res);
			
		} finally {
			fr.close();
			br.close();
		}
		
		return res;
		
	}
	
	//metodo per la scrittura del file
	public void writeToFile(File f, String toWrite) throws IOException {
		BufferedWriter bw = null;
		try {
			
			//prima va letto tutto quello che c'era già dentro, salvato in un array e poi 
			
			FileWriter fw = new FileWriter(f);
			bw = new BufferedWriter(fw);
			
			bw.write(toWrite);
			
		} catch(Exception e) {
			
		} finally {
			bw.close();
		}
	}
	
	//metodo di modifica di un file
	//partiamo da un file che ha caratteri divisi da tabulazione e li trasformiamo in file csv veri e propri (con apici e punto e virgola)
	public void makeFileCSV(File f) {
		
		FileReader reader = null;
		BufferedReader buffered = null;
		
		
		
		try {
			
		} catch(Exception e) {
			
		} finally {
			
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		
		File file = new File("/javaFiles/rubrica.csv");
		
		Reader r = new Reader();
		String[] arr = r.readFromFile(file, true);
		for(String s : arr) {
			System.out.println(s);
		}
		
		//System.out.println(Arrays.toString(arr));
		
//		r.writeToFile(file, "nuova scrittura");
			
	}
}
