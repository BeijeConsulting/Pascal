package it.beije.pascal.rubrica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import it.beije.pascal.file.util.FileUtil;

public class Esercizio_Metodi {

	public static void main(String[] args) {

		// readXML();
		// writeXML();
		// readCSV();
		// writeCSV();

	}

	private static void readCSV() {
		String pathFileCSV = "/Users/ema29/javafile/csv/lettura.txt";
		List<Contatto> contatti = loadRubricaFromCSV(pathFileCSV, ";");
		for (Contatto contatto : contatti) {
			System.out.println(contatto);
		}
	}

	private static void writeCSV() {
		writeRubricaCSV(loadRubricaFromCSV("/Users/ema29/javafile/csv/lettura.txt", ";"),
				"/Users/ema29/javafile/csv/scrittura.txt", "");
		String pathFileCSV = "/Users/ema29/javafile/csv/scrittura.txt";
		List<Contatto> contatti = loadRubricaFromCSV(pathFileCSV, ";");
		for (Contatto contatto : contatti) {
			System.out.println(contatto);
		}

	}

	private static void readXML() {
		String pathFileXML = "/Users/ema29/javafile/xml/rubrica.xml";
		List<Contatto> contatti = loadRubricaFromXML(pathFileXML);
		for (Contatto contatto : contatti) {
			System.out.println(contatto);
		}
	}

	private static void writeXML() {
		String pathFileXML1 = "/Users/ema29/javafile/xml/rubrica.xml";
		String pathFileXML2 = "/Users/ema29/javafile/xml/scrittura.xml";
		List<Contatto> contatti = loadRubricaFromXML(pathFileXML1);
		writeRubricaXML(contatti, pathFileXML2);
		contatti = loadRubricaFromXML(pathFileXML2);
		for (Contatto contatto : contatti) {
			System.out.println(contatto);
		}
	}

	public static List<Contatto> loadRubricaFromCSV(String pathFile, String separator) {
		FileReader reader = null;
		List<Contatto> contatti = new ArrayList<Contatto>();
		try {
			reader = new FileReader(pathFile);
			BufferedReader scanner = new BufferedReader(reader);

			while (scanner.ready()) {
				Contatto contatto = new Contatto();
				String righe = scanner.readLine();

				String[] colonne = righe.split(separator);
				contatto.setNome(colonne[0]);
				contatto.setCognome(colonne[1]);
				contatto.setTelefono(colonne[2]);
				contatto.setEmail(colonne[3]);
				contatto.setNote(colonne[4]);
				contatti.add(contatto);

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			FileUtil.closeReader(reader);
		}
		return contatti;

	}

	public static void writeRubricaCSV(List<Contatto> contatti, String pathFile, String separator) {
		try {
			FileWriter writer = new FileWriter(pathFile);
			for (Contatto contatto : contatti) {

				writer.write(contatto.getNome());
				writer.write(';');

				writer.write(contatto.getCognome());
				writer.write(';');

				writer.write(contatto.getTelefono());
				writer.write(';');

				writer.write(contatto.getEmail());
				writer.write(';');

				writer.write(contatto.getNote());
				writer.write(';');
				writer.write('\n');
			}
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void writeRubricaXML(List<Contatto> contatti, String pathFile) {

		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder;
		Document document = null;
		try {
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
			document = documentBuilder.newDocument();

			Element rubrica = document.createElement("rubrica");
			document.appendChild(rubrica);

			for (Contatto contatto : contatti) {
				Element contact = document.createElement("contatto");
				rubrica.appendChild(contact);

				Element nome = document.createElement("nome");
				nome.setTextContent(contatto.getNome());
				contact.appendChild(nome);

				Element cognome = document.createElement("cognome");
				cognome.setTextContent(contatto.getCognome());
				contact.appendChild(cognome);

				Element telefono = document.createElement("telefono");
				telefono.setTextContent(contatto.getTelefono());
				contact.appendChild(telefono);

				Element email = document.createElement("email");
				email.setTextContent(contatto.getEmail());
				contact.appendChild(email);

				Element note = document.createElement("note");
				note.setTextContent(contatto.getNote());
				contact.appendChild(note);
			}

		} catch (ParserConfigurationException e1) {
			e1.printStackTrace();
		}

		// Write the content into xml file
		File file = new File(pathFile);
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer;
		try {
			transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(file);
			transformer.transform(source, result);
		} catch (TransformerException e) {
			e.printStackTrace();
		}

	}

	private static List<Contatto> loadRubricaFromXML(String pathFile) {
		List<Contatto> contatti = new ArrayList<>();
		try {
			// Creazione oggetto per lavorare con file xml
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(pathFile);
			// Elemento root (tag padre)
			Element root = document.getDocumentElement();
			// Estrapolazione tag figli
			NodeList childNodes = root.getChildNodes();

			for (int i = 0; i < childNodes.getLength(); i++) {
				Contatto contatto = new Contatto();
				Node node = childNodes.item(i);
				if (node instanceof Element) {
					Element el = (Element) node;
					List<Element> values = getChildElements(el);

					for (Element value : values) {
						switch (value.getTagName()) {
						case "nome":
							contatto.setNome(value.getTextContent());
							break;
						case "cognome":
							contatto.setCognome(value.getTextContent());
							break;
						case "telefono":
							contatto.setTelefono(value.getTextContent());
							break;
						case "email":
							contatto.setEmail(value.getTextContent());
							break;
						case "note":
							contatto.setNote(value.getTextContent());
						}
					}
					contatti.add(contatto);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return contatti;
	}

	private static List<Element> getChildElements(Element element) {
		List<Element> childElements = new ArrayList<Element>();
		NodeList nodeList = element.getChildNodes();
		for (int n = 0; n < nodeList.getLength(); n++) {
			if (nodeList.item(n) instanceof Element)
				childElements.add((Element) nodeList.item(n));
		}
		return childElements;
	}

}
