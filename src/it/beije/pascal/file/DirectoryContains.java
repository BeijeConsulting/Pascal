package it.beije.pascal.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DirectoryContains {
	public static void getDirectoryFiles(File dir, StringBuilder contenuto, int level) {
		try {
		        File[] files = dir.listFiles();
		        
		        for (File file : files) {
		            
		        	if (file.isDirectory())
		        	{
		        		for(int i = 0; i<level; i++) {
		            		contenuto.append("\t");
		            	}
		        		contenuto.append("cartella:" + file.getCanonicalPath() + "\n");
		                getDirectoryFiles(file.getCanonicalFile(), contenuto, level + 1);
		            } else {
		            	for(int i = 0; i<level; i++) {
		            		contenuto.append("\t");
		            	}
		            	contenuto.append("file:" + file.getCanonicalPath() + "\n");
		            }
		        }
		        
		        
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		}
	
	public static void writeFile(String percorso, File file) {
		FileWriter writer = null;
		
		File newFile = new File(percorso);
		
		StringBuilder contenuto = new StringBuilder();
		getDirectoryFiles(file, contenuto, 0);
		
		try {
			 writer = new FileWriter(newFile);
			 writer.write(contenuto.toString());
			 writer.flush();
		}catch (IOException e) {
	        e.printStackTrace();
	    }finally {
	    	try {
				if (writer != null) {
					writer.close();
				}
				
			}catch (Exception fEx) {
				fEx.printStackTrace();
			}
	    }
	}
	
	public static void creaFile(String percorso, File file) { 
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
		
		writeFile(nomeFile.toString(), file);
	}
	
	public static String percorsoFile() { 
	//Metodo chre prende in input un percorso
		Scanner in = new Scanner(System.in);
		System.out.print("Inserire percorso Directory: ");
		String percorso = in.nextLine();
		in.close();
		return percorso;
	}
	

	public static void main(String[] args) {
		
		String percorso = percorsoFile();
		File file = new File(percorso);
		
		creaFile(percorso, file);
		

	}

}
