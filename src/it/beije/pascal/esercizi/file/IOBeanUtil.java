package it.beije.pascal.esercizi.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

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
	
	public void writeRubricaCSV(List<Contatto> contatti, String pathFile, String separator) {
		PrintWriter outputStream = null;
		try {
			outputStream = new PrintWriter(pathFile);
		}
		catch(FileNotFoundException e) {
			System.out.println("Errore nell'apertura del file " + pathFile);
			System.exit(0);
		}
		for(Contatto c : contatti) {
			outputStream.print(c.getId() + separator);
			outputStream.print(c.getCognome() + separator);
			outputStream.print(c.getNome() + separator);
			outputStream.print(c.getTelefono() + separator);
			outputStream.print(c.getEmail() + separator);
			outputStream.print(c.getNote());
			outputStream.println();
		}
		outputStream.close();
		System.out.println("Le righe sono state scritte su : " + pathFile);
	}
	
	public void writeRubricaXML(List<Contatto> contatti, String pathFile) throws Exception {
		DocumentBuilderFactory dBF = DocumentBuilderFactory.newInstance();
		DocumentBuilder dB = dBF.newDocumentBuilder();
		Document doc = dB.newDocument();
		Element contacts = doc.createElement("contatti");
		doc.appendChild(contacts);
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(pathFile));
		for(Contatto c : contatti) {
			Element contact = doc.createElement("contatto");
			Element cognome = doc.createElement("cognome");
			cognome.setTextContent(c.getCognome());
			contact.appendChild(cognome);
			Element nome = doc.createElement("nome");
			nome.setTextContent(c.getNome());
			contact.appendChild(nome);
			Element telefono = doc.createElement("telefono");
			telefono.setTextContent(c.getTelefono());
			contact.appendChild(telefono);
			Element email = doc.createElement("email");
			email.setTextContent(c.getEmail());
			contact.appendChild(email);
			contacts.appendChild(contact);
			transformer.transform(source, result);
		}
		System.out.println("XML creato");
	}

}
