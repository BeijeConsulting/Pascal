package it.beije.pascal.esercizi;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class GestoreRubrica {
	
	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
		
		Scanner tastiera = new Scanner(System.in);
		System.out.println("Ciao, inserisci il path del file");
		String path = tastiera.nextLine();
		System.out.println("Scrivi il numero relativo alla funzionalità che vuoi usare:");
		System.out.println("1. vedi lista contatti (con possibilità di ordinare per nome e cognome a scelta)\r\n"
				+ "2. cerca contatto\r\n"
				+ "3. inserisci nuovo contatto\r\n"
				+ "4. modifica contatto\r\n"
				+ "5. cancella contatto\r\n"
				+ "6. trova contatti duplicati\r\n"
				+ "7. unisci contatti duplicati");
		int scelta = tastiera.nextInt();
		
		if(path.endsWith(".csv")) {
			leggiContattiCSV(path);
		}
		else if(path.endsWith(".xml")) {
			
		}
		
		
		
	}
	
	public static void leggiContattiCSV(String path) throws IOException {
		BufferedReader bf = new BufferedReader(new FileReader(path));
		String row = null;
		List<String> rows = new ArrayList<String>();
		while(bf.ready()) {
			row = bf.readLine();
			rows.add(row);
			System.out.println(row);
		}
	}
	
	

}
