package it.beije.pascal.ripasso;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CSVscritturaEsercizio {

	public static void main(String[] args) throws IOException {
		
		File file = new File("C:\\Users\\Padawan01\\OneDrive\\Desktop\\ciao\\Animali\\pesci\\delfino.txt");
		System.out.println("esiste ? " + file.exists());
		System.out.println("è un file ? " + file.isFile());
		System.out.println("è una directory ? " + file.isDirectory());
		
		FileWriter fw = new FileWriter(file);
		
		fw.write("Ciao amico mio, come stai?");
		fw.flush();

	}

}
