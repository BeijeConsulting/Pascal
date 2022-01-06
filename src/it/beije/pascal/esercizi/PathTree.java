package it.beije.pascal.esercizi;

import java.io.File;
import java.io.IOException;

public class PathTree {

	public static void main(String[] args) {
		
		try {
			alberoDirectory("C:\\Users\\Padawan01\\OneDrive\\Desktop\\ciao");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void alberoDirectory(String directoryPath) throws IOException{
		File file = new File(directoryPath);
		if(file.isDirectory()) {
			File[] a = file.listFiles();
			
			for(File f : a) {
				if(f.isFile()) {
					System.out.println(f.getName());
				}
				else if(f.isDirectory()){
					System.out.println("\t" + f.getName());
					File[] d = f.listFiles();
					for(File sotto : d) {
						if(sotto.isFile()) {
							System.out.println("\t\t" + sotto.getName());
						}
						else if(sotto.isDirectory()){
							System.out.println("\t\t\t\t" + sotto.getName());
						}
					}
				}
			}
		}
	}
	
}
