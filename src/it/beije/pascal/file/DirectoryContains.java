package it.beije.pascal.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DirectoryContains {
	public static void getDirectoryFiles(String percorso) {
		File dir = new File(percorso);
		
		FileWriter writer = null;
		File newFile = new File(creaFile(percorso));
		
		try {
			    writer = new FileWriter(newFile);
		        File[] files = dir.listFiles();
		        
		        for (File file : files) {
		            
		        	if (file.isDirectory())
		        	{
		                writer.write("cartella:" + file.getCanonicalPath() + "\n   ");
		                getDirectoryFiles(file.getCanonicalPath());
		                
		            } else {
		            	writer.write("file:" + file.getCanonicalPath() + "\n");
		            }
		        }
		        writer.flush();
		        
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		}
	
	public static String creaFile(String percorso) { 
	//Metodo che estrapola, dal percorso, il nome della cartella e crea un file con quest'ultimo
		StringBuilder nomeFile = new StringBuilder();
		int i = percorso.length() - 1;
		while(percorso.charAt(i) != '\\' ){
			nomeFile.append(percorso.charAt(i));
			i--;
		}
		nomeFile.reverse();
		nomeFile.append(".txt");
		
		File nuovoFile = new File(nomeFile.toString());
		try {
			nuovoFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return nomeFile.toString();
	}
	
	public static String percorsoFile() { 
	//Metodo chre prende in input un percorso
		Scanner in = new Scanner(System.in);
		System.out.print("Inserire percorso Directory: ");
		String percorso = in.nextLine();
		return percorso;
	}
	

	public static void main(String[] args) {
		
		String percorso = percorsoFile();
		
		getDirectoryFiles(percorso);

	}

}
