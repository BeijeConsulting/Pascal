package it.beije.pascal.file.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import it.beije.pascal.rubrica.model.Contatto;

public class MenuUtil {

	private MenuUtil() {

	}

	public static List<Contatto> getContactList() {
		List<Contatto> contatti = new ArrayList<>();
		try {
			// Creazione oggetto per lavorare con file xml
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse("/Users/ema29/javafile/xml/scrittura.xml");
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

	public static void insertContacts(List<Contatto> contatti) throws Exception {

		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document = documentBuilder.newDocument();

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
		
		// write the content into xml file
		File file = new File("/Users/ema29/javafile/xml/scrittura.xml");
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(document);
		StreamResult result = new StreamResult(file);
		transformer.transform(source, result);
		
	}

	private static void ordinaContatti(List<Contatto> contatti) {
		contatti.sort(new Comparator<Contatto>() {
			public int compare(Contatto c1, Contatto c2) {
				return c1.getNome().compareTo(c2.getNome());
			}

		});
	}

}
