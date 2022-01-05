package it.beije.pascal.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DirectoryContent {
	

	
	public static void main(String []args) {
		
		
		String path = inputRequest();
		
		File file = new File(path);
		
		List<String> content = new ArrayList<String>();
		
		System.out.println("esiste ? " + file.exists());
		System.out.println("isDirectory ? " + file.isDirectory());
		
		content = getDirectoryFiles(file);
		
		printOnFile(content);
		
		
	}
	
	public static String inputRequest() {
		
		System.out.println("Inserire il percorso di una cartella");
		Scanner scanner = new Scanner(System.in);
	    String inputString = scanner.nextLine();
		
		return inputString;
		
	}
	
	public static List<String> getDirectoryFiles(File dir) {
		
		List<String> rows = new ArrayList<String>();
		
	    try {
	    	
	        File[] files = dir.listFiles();
	        
	        
	        for (File file : files) {
	            
	        	if (file.isDirectory())
	        	{
	                System.out.println("cartella:" + file.getCanonicalPath());
	                rows.add(file.getCanonicalPath());
	                getDirectoryFiles(file.getCanonicalFile());
	                
	            } else {
	            	
	                System.out.println("     file:" + file.getCanonicalPath());
	                rows.add(file.getCanonicalPath());
	            }
	        }
	        
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    
	    return rows;
	}
	
	public static void printOnFile(List<String> print)
	{
		FileWriter writer = null;
		
		try {
			
			File newFile = new File("/temp/contenuto.txt");
			
			writer = new FileWriter(newFile);
			
			for (String r : print) {
				
				System.out.println(r);
				
				writer.write(r +" ");
			}
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
	
}
