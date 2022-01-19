package it.beije.pascal.file.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
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

import it.beije.pascal.bean.Contatto;

public class RubricaService {

	private RubricaService() {

	}

	public static List<Contatto> getContactList() {
		List<Contatto> contatti = new ArrayList<>();
		try {
			// Creazione oggetto per lavorare con file xml
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse("/Users/ema29/javafile/xml/rubrica.xml");
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

	public static Contatto findContact(Contatto contatto, List<Contatto> contatti) {
		for (int i = 0; i < contatti.size(); i++) {
			// Overload metodo equals della classe Contatto
			if (contatto.equals(contatti.get(i))) {
				return contatti.get(i);
			}
		}
		return null;
	}
	

	public static void contactOrder(List<Contatto> contatti) {
		contatti.sort(new Comparator<Contatto>() {
			public int compare(Contatto c1, Contatto c2) {
				return c1.getNome().compareTo(c2.getNome());
			}

		});
	}
  
	public static void insertContacts(List<Contatto> contatti) {

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
		File file = new File("/Users/ema29/javafile/xml/rubrica.xml");
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
	

	public static void updateContact(Contatto contatto) {	
		Contatto newContatto = new Contatto("modificato", "modificato", "modificato", "modificato", "modificato");
		List<Contatto> contatti = RubricaService.getContactList();
		for (int i = 0; i < contatti.size(); i++) {
			if (contatto.equals(contatti.get(i))) {
				contatti.set(i, newContatto);
			}
		}
		RubricaService.insertContacts(contatti);
	}
	
	public static void deleteContact(Contatto contatto) {		
		List<Contatto> contatti = RubricaService.getContactList();
		int tmp = 0;
		for (int i = 0; i < contatti.size(); i++) {
			// Overload metodo equals della classe Contatto
			if (contatto.equals(contatti.get(i - tmp))) {
				contatti.remove(i - tmp);
				tmp++;
			}
		}
		RubricaService.insertContacts(contatti);
	}

	public static List<Contatto> findDuplicates(List<Contatto> contatti) {		
		List<Contatto> contattiDuplicati = new ArrayList<Contatto>();
		contactOrder(contatti);
		int tmp = 0;
		for (int i = 0; i < contatti.size(); i++) {
			for (int j = i + 1; j < contatti.size(); j++) {
				// Overload metodo equals della classe contatto
				if (contatti.get(i).equals(contatti.get(j))) {
					tmp = j;
				}
			}
			if (tmp > 0) {
				contattiDuplicati.add(contatti.get(tmp));
				i = tmp + 1;
				tmp = 0;
			}
		}
		return contattiDuplicati;
	}
	
	public static void deleteDuplicates(List<Contatto> contatti) {
		List<Contatto> contattiDuplicati = RubricaService.findDuplicates(contatti);
		int tmp = 0;
		for (int i = 0; i < contattiDuplicati.size(); i++) {
			for (int j = 0; j < contatti.size(); j++) {
				if(contatti.get(j).equals(contattiDuplicati.get(i))) {
					contatti.remove(j - tmp);
					tmp++;
				}
			}
			tmp = 0;
		}
		RubricaService.insertContacts(contattiDuplicati);		
	}
}
