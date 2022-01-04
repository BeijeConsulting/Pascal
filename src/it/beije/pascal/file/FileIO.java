package it.beije.pascal.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileIO {

	//METODO CHE LEGGE DA FILE E RITORNA UN'ARRAY DI STRINGHE;
	FileReader fileReader;
	StringBuilder sb;
	ArrayList<String> res = new ArrayList<>();
	
	public String[] readFromFile(File f) {
		
		try {
		sb = new StringBuilder();
		fileReader = new FileReader(f);
		
			while(fileReader.ready()) {
				char i = (char)fileReader.read(); 
				sb.append(i);
				if(i == 32) {
					res.add(sb.toString());
					sb.delete(0, sb.length());
				}
			}
			
			res.add(sb.toString());
			
		} catch(IOException e) 
		{
			e.printStackTrace();
		}
		
		String[] trueRes = new String[res.size()];
		trueRes = res.toArray(trueRes);
		
		return trueRes;
	}
	
	//un metodo che descriva un file in tutte le sue caratteristiche
	public static void describeFile(File file) {
		System.out.println("Il file esiste? " + file.exists());
		System.out.println("Nome: " + file.getName());
		System.out.println("Path: " + file.getPath());
		System.out.println("Path assoluto: " + file.getAbsolutePath());
	}
	
	
	
	public static void main(String[] args) throws IOException {
		//prima di tutto in una classe che lavora con file si dichiara il file
		File file = new File("/javaFiles/FileOne.txt");
//		System.out.println(file.exists()); //controllo che legga il file come esistente nel sistema
//		System.out.println(file.canExecute());
//		System.out.println(file.canRead());
//		System.out.println(file.canWrite());
//		System.out.println(file.getFreeSpace());
//		System.out.println(file.isDirectory());
//		System.out.println(file.isFile());
//		System.out.println(file.toURI());
		
		
		//FileIO.describeFile(file);
		
		File newFile = new File("/javaFiles/FileTwo.txt"); //secondo file
		//newFile.createNewFile();
//		newFile.delete();
		
//		FileWriter fw = new FileWriter(newFile);
		//fw.write("Hello");
		
		//possiamo fare la stessa cosa che facciamo con bufferedReader anche con bufferedWriter: inserire all'interno un
		//altro reader/writer
//		BufferedWriter bw = new BufferedWriter(fw);
//		String s = "scrittura nel file";
//		bw.write(s);
//		System.out.println(newFile.exists());
		
		
//		bw.close();
		
		//primo filereader
		FileReader fr = new FileReader(file); //passo al suo interno il file
		
		//secondo filereader, che è bufferedreader
		BufferedReader br = new BufferedReader(fr); //passo al suo interno il primo reader
		
		//stampo la prima riga del file di testo
		//System.out.println(br.readLine());
		
		br.close();
	
		FileIO fio = new FileIO();
		String[] arr;
		arr = fio.readFromFile(newFile);
		for(String str : arr) {
			System.out.println(str);
		}
	
	
	}
	
	//IMPORTANTE, POSSIAMO LISTARE I CONTENUTI DI UNA DIRECTORY ATTRAVERSO IL METODO LIST(), questo è utile per un esercizio
}
