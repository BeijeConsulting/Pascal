package it.beije.pascal.rubrica;

import java.util.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class RubricaUtils {
	
	public List<Contatto> loadRubricaFromCSV(String pathFile, String separator) throws IOException {
		
		//a partire da un file CSV, leggere e immettere in una lista tutti i contatti trovati all'interno del file rubrica.csv
		//per ogni contatto trovato, è necessario costruire un oggetto Contatto, passandogli tutti i valori all'interno del file
		//dobbiamo anche indicare il separatore tra i dati (ovviamente glielo passeremo a split)
		
		//MODIFICHE DA FARE: non dare per scontato l'ordine, non dare per scontato il separatore
		
		List<Contatto> contatti = new ArrayList<Contatto>();
		
		File file = new File(pathFile);
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		
		String riga;
		Contatto contatto;
		String[] r;
		
		while(br.ready()) {
			riga = br.readLine();
			r = riga.split(separator);
			
			contatto = new Contatto();
			contatto.setCognome(r[0]);
			contatto.setNome(r[1]);
			contatto.setEmail(r[2]);
			contatto.setTelefono(r[3]);
			contatto.setNote("");
			contatti.add(contatto);
		}
		
		fr.close();
		br.close();
		
		contatti.remove(0);
		
		return contatti;
	}
	
	public static List<Element> getChildElements(Element element) {
		List<Element> childElements = new ArrayList<Element>();
		NodeList nodeList = element.getChildNodes();
		for (int n = 0; n < nodeList.getLength(); n++) {
			if (nodeList.item(n) instanceof Element) childElements.add((Element)nodeList.item(n));
		}
		
		return childElements;
	}
	
	public List<Contatto> loadRubricaFromXML(String pathFile) throws Exception {
		
		//l'obiettivo è il medesimo del metodo precedente, ma al posto di un file csv usiamo un file xml
		
		List<Contatto> contatti = new ArrayList<Contatto>();
		
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		
		Document document = documentBuilder.parse("/javaFiles/rubrica.xml");
		
		Element root = document.getDocumentElement();
		
		NodeList childNodes = root.getChildNodes();
		List<Element> values = null;
		for(int i = 0; i < childNodes.getLength(); i++) {
			Node node = childNodes.item(i);
			
			if(node instanceof Element) {
				Element el = (Element)node; //rappresenta il tag "contatto"
				
				values = getChildElements(el);
				
				Contatto contatto = new Contatto();
				
				contatto.setNome(values.get(0).getTextContent());
				contatto.setCognome(values.get(1).getTextContent());
				contatto.setTelefono(values.get(2).getTextContent());
				contatto.setEmail(values.get(3).getTextContent());
				
				if(values.size() == 4) {
					contatto.setNote("null");
				} else {
					contatto.setNote(values.get(4).getTextContent());
				}
				contatti.add(contatto);
			}
		}
		return contatti;
	}
	
	public void writeRubricaCSV(List<Contatto> contatti, String pathFile, String separator) throws Exception {
		
		//metodo che prende in input una lista di contatti, il percorso di un file, e un separatore
		//scrive nel file tutti i contatti che abbiamo nella lista
		
		File file = new File(pathFile);
		FileWriter fw = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(fw);
		
		for(int i = 0; i < contatti.size(); i++) {
			StringBuilder sb = new StringBuilder();
			Contatto cont = contatti.get(i);
			
			sb.append(cont.getNome())
				.append(separator)
				.append(cont.getCognome())
				.append(separator)
				.append(cont.getEmail())
				.append(separator)
				.append(cont.getTelefono())
				.append(separator)
				.append(cont.getNote())
				.append("\n");
			
			System.out.println(sb.toString());
		
			bw.write(sb.toString());	
		}
		bw.close();
	}
	
	public void writeRubricaXML(List<Contatto> contatti, String pathFile) throws Exception {
		
		//medesimo obiettivo, ma invece di scrivere un file csv scriviamo un file xml
		File file = new File(pathFile);
		FileWriter fw = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(fw);
		
		String first = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		String openRootTag = "<rubrica>";
		String closeRootTag = "<rubrica/>";
		bw.write(first);
		bw.write(openRootTag);
		
		for(int i = 0; i < contatti.size(); i++) {
			StringBuilder sb = new StringBuilder();
			Contatto cont = contatti.get(i);
			
			sb.append("<contatto>\n")
				.append("<nome>")
				.append(cont.getNome())
				.append("<nome/>\n")
				.append("<cognome>")
				.append(cont.getCognome())
				.append("<cognome/>\n")
				.append("<email>")
				.append(cont.getEmail())
				.append("<email/>\n")
				.append(cont.getEmail())
				.append("<email/>\n")
				.append("<telefono>")
				.append(cont.getTelefono())
				.append("<telefono/>\n")
				.append("<note>")
				.append(cont.getNote())
				.append("<note/>\n");
			
			System.out.println(sb.toString());
		
			bw.write(sb.toString());	
		}
		bw.write(closeRootTag);
		bw.close();
	}
	
	public static void main(String[] args) throws Exception {
		RubricaUtils ru = new RubricaUtils();
//		prova = ru.loadRubricaFromCSV("/javaFiles/rubrica.csv", "\t");
		
		List<Contatto> cont = ru.loadRubricaFromXML("/javaFiles/rubrica.xml");
		
//		ru.writeRubricaCSV(cont, "/javaFiles/FileOne.txt", ";");
	}
}
