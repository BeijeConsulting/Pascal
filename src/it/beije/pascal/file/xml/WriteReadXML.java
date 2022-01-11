package it.beije.pascal.file.xml;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

public class WriteReadXML {

	public static void main(String[] args) throws Exception {
		
		// Scrittura
		Contatto contatto1 = new Contatto("Emanuele", "Corona", "3335877155", "emacorona@gmail.com", "descrizione");
		Contatto contatto2 = new Contatto("Mario", "Rossi", "333344455", "mariorossi@gmail.com", "il solito mario");		
		List<Contatto> contatti = new ArrayList<Contatto>();
		contatti.add(contatto1);
		contatti.add(contatto2);		
		writeXML(contatti);
	}

	private static void writeXML(List<Contatto> contatti) throws Exception {

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
		System.out.println(file.exists());
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(document);
		StreamResult result = new StreamResult(file);

		// Output to console for testing
		StreamResult syso = new StreamResult(System.out);
		transformer.transform(source, result);
		// Opzionale(Stampa a schermo)
		transformer.transform(source, syso);

	}
	private static void getListFromXML() {

		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse("/Users/ema29/javafile/jdbc/xml/rubrica.xml");

			Element rubrica = document.getDocumentElement();
			System.out.println("root : " + rubrica.getTagName());

			// NodeList contatti = root.getElementsByTagName("contatto");

			NodeList contatti = rubrica.getChildNodes();

			for (int i = 0; i < contatti.getLength(); i++) {
				Node contatto = contatti.item(i);
				if (contatto instanceof Element) { // provare a rimuovere
					Element element = (Element) contatto;

					List<Element> contactList = getChildElements(element);
					for (Element contact : contactList) {
						System.out.println(contact.getTagName() + " : " + contact.getTextContent());
					}
				}

			}

		} catch (ParserConfigurationException e) {
			e.printStackTrace();

		} catch (SAXException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static List<Element> getChildElements(Element element) {
		List<Element> childElements = new ArrayList<Element>();
		NodeList childNode = element.getChildNodes();
		for (int n = 0; n < childNode.getLength(); n++) {
			if (childNode.item(n) instanceof Element)
				childElements.add((Element) childNode.item(n));
		}
		return childElements;
	}

}
