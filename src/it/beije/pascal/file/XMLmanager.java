package it.beije.pascal.file;

import java.io.File;
import java.util.ArrayList;
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

import it.beije.pascal.rubrica.Contatto;

public class XMLmanager {

	public static List<Element> getChildElements(Element element) {
		List<Element> childElements = new ArrayList<Element>();
		NodeList nodeList = element.getChildNodes();
		for (int n = 0; n < nodeList.getLength(); n++) {
			if (nodeList.item(n) instanceof Element)
				childElements.add((Element) nodeList.item(n));
		}

		return childElements;
	}

	public static List<Contatto> loadRubricaFromXML(String pathFile) throws Exception {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

		Document document = documentBuilder.parse(pathFile);

		List<Contatto> allContact = new ArrayList<Contatto>();

		Element root = document.getDocumentElement();
		NodeList contatti = root.getElementsByTagName("contatto");
		NodeList childNodes = root.getChildNodes();

		for (int i = 0; i < childNodes.getLength(); i++) {
			Node node = childNodes.item(i);
			Contatto contatto = new Contatto();
			if (node instanceof Element) {
				Element el = (Element) node;
				List<Element> values = getChildElements(el);
				for (Element value : values) {
					contatto.setCognome(getTextValue(el, "cognome"));
					contatto.setNome(getTextValue(el, "nome"));
					contatto.setTelefono(getTextValue(el, "telefono"));
					contatto.setEmail(getTextValue(el, "email"));
					contatto.setNote(getTextValue(el, "note"));

				}
				allContact.add(contatto);
			}
		}

		return allContact;
	}

	public static void writeRubricaXML(List<Contatto> contatti, String pathFile) throws Exception {

		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

		Document doc = documentBuilder.newDocument();

		Element rubrica = doc.createElement("contatti");
		doc.appendChild(rubrica);

		for (Contatto c : contatti) {
			Element contatto = doc.createElement("contatto");

			Element cognome = doc.createElement("cognome");
			cognome.setTextContent(c.getCognome());
			contatto.appendChild(cognome);

			Element nome = doc.createElement("nome");
			nome.setTextContent(c.getNome());
			contatto.appendChild(nome);

			Element telefono = doc.createElement("telefono");
			telefono.setTextContent(c.getTelefono());
			contatto.appendChild(telefono);

			Element email = doc.createElement("email");
			email.setTextContent(c.getEmail());
			contatto.appendChild(email);

			Element note = doc.createElement("note");
			note.setTextContent(c.getNote());
			contatto.appendChild(note);

			rubrica.appendChild(contatto);
		}

		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);

		StreamResult result = new StreamResult(new File(pathFile));

		// Output to console for testing
		//StreamResult syso = new StreamResult(System.out);

		transformer.transform(source, result);
		//transformer.transform(source, syso);
	}


	private static String getTextValue(Element doc, String tag) {
		String value = "";
		NodeList nl;
		nl = doc.getElementsByTagName(tag);
		if (nl.getLength() > 0 && nl.item(0).hasChildNodes()) {
			value = nl.item(0).getFirstChild().getNodeValue();
		}
		return value;
	}

	
	
	
	
	public static void main(String[] args) throws Exception {

		System.out.println("print contatti :");
		List<Contatto> contatti = loadRubricaFromXML("./rubrica.xml");

		for (Contatto contatto : contatti) {
			System.out.println(contatto);
		}

		writeRubricaXML(contatti, "./rubrica2.xml");

	}

}
