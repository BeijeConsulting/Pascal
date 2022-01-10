package it.beije.pascal.esercizi;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import it.beije.pascal.rubrica.Contatto;

public class IOBeanUtil {
	
	public List<Contatto> loadRubricaFromXML(String pathFile) throws ParserConfigurationException, SAXException, IOException{
		List<Contatto>contatti = new ArrayList<Contatto>();
		Contatto contatto = new Contatto();
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document = documentBuilder.parse("rubrica.xml");
		NodeList nList = document.getElementsByTagName("contatto");
		for(int i=0; i<nList.getLength(); i++) {
			Node nNode = nList.item(i);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				contatto.setNome(eElement.getElementsByTagName("nome").item(0).getTextContent());
				contatto.setCognome(eElement.getElementsByTagName("cognome").item(0).getTextContent());
				contatto.setTelefono(eElement.getElementsByTagName("telefono").item(0).getTextContent());
				contatto.setEmail(eElement.getElementsByTagName("email").item(0).getTextContent());
				System.out.println(contatto);
			}
		}
		
		return contatti;
	}
	
	public List<Contatto> loadRubricaFromCSV(String pathFile, String separator){
		BufferedReader inputStream = null;
		List<Contatto>contatti = new ArrayList<Contatto>();
		try {
			inputStream = new BufferedReader(new FileReader(pathFile));
		} catch (FileNotFoundException e) {
			System.out.println("Problemi nell'apertura del file " + pathFile);
		}
		try {
			String riga = inputStream.readLine();
			String[]el = riga.split(separator);
			int nome = 0;
			int cognome = 0;
			int telefono = 0;
			int email = 0;
			for(int i=0; i<el.length; i++) {
				if(el[i].equalsIgnoreCase("nome")) {
					nome = i;
				}
				if(el[i].equalsIgnoreCase("cognome")) {
					cognome = i;
				}
				if(el[i].equalsIgnoreCase("telefono")) {
					telefono = i;
				}
				if(el[i].equalsIgnoreCase("email")) {
					email = i;
				}
			}
			while(riga != null) {
				riga = inputStream.readLine();
				el = riga.split(separator);
				Contatto contatto = new Contatto();
				contatto.setNome(el[nome]);
				contatto.setCognome(el[cognome]);
				contatto.setEmail(el[email]);
				contatto.setTelefono(el[telefono]);
//				System.out.println(contatto);
				contatti.add(contatto);
			}
		} catch (IOException e) {
			System.out.println("Errore nella lettura del file");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return contatti;
	}

}
