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


public class XMLmanager {

	public static List<Element> getChildElements(Element element) {
		List<Element> childElements = new ArrayList<Element>();
		NodeList nodeList = element.getChildNodes();
		for (int n = 0; n < nodeList.getLength(); n++) {
			if (nodeList.item(n) instanceof Element) childElements.add((Element)nodeList.item(n));
		}
		
		return childElements;
	}

	
	public static void readXML() throws Exception {
		
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		
		Document document = documentBuilder.parse("/temp/rubrica.xml");
		
		Element root = document.getDocumentElement();
		System.out.println("root : " + root.getTagName());
		
		NodeList contatti = root.getElementsByTagName("contatto");
		
		for (int i = 0; i < contatti.getLength(); i++) {
			Element contatto = (Element)contatti.item(i);
			System.out.println("contatto " + i + " : " + contatto.getAttribute("eta"));
		}
		
//		NodeList nomi = root.getElementsByTagName("nome");
//		System.out.println(nomi.getLength());
		
		NodeList childNodes = root.getChildNodes();
		System.out.println(childNodes.getLength());
		for (int i = 0; i < childNodes.getLength(); i++) {
			Node node = childNodes.item(i);
			if (node instanceof Element) {
				Element el = (Element)node;
				System.out.println("node " + i + " : " + el.getAttribute("eta"));
				//System.out.println("node " + i + " : " + el.getTextContent());
				
//				NodeList nomi = el.getElementsByTagName("nome");
//				System.out.println(nomi.getLength());
//				System.out.println(nomi.item(0).getTextContent());
				
				List<Element> values = getChildElements(el);
				for (Element value : values) {
					System.out.println(value.getTagName() + " : " + value.getTextContent());
				}
				
			}
		}
		

	}

	public static void main(String[] args) throws Exception {
		
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		
		Document doc = documentBuilder.newDocument();
		
		Element contatti = doc.createElement("contatti");
		doc.appendChild(contatti);
		
		Element contatto = doc.createElement("contatto");
		contatto.setAttribute("eta", "35");
		
		Element cognome = doc.createElement("cognome");
		cognome.setTextContent("Marrone");
		contatto.appendChild(cognome);

		Element nome = doc.createElement("nome");
		nome.setTextContent("Emma");
		contatto.appendChild(nome);

		Element telefono = doc.createElement("telefono");
		telefono.setTextContent("432423");
		contatto.appendChild(telefono);

		Element email = doc.createElement("email");
		email.setTextContent("emma@marrone.it");
		contatto.appendChild(email);

		Element note = doc.createElement("note");
		note.setTextContent("la nota cantante");
		contatto.appendChild(note);
		
		contatti.appendChild(contatto);
		
		
		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		
		StreamResult result = new StreamResult(new File("/temp/new_rubrica.xml"));

		// Output to console for testing
		StreamResult syso = new StreamResult(System.out);

		transformer.transform(source, result);
		transformer.transform(source, syso);

		//System.out.println("File saved!");		
		
	}
	
}

/*
<?xml version="1.0" encoding="UTF-8"?>
<rubrica>
	<contatto eta="30">
		<nome>Mario</nome>
		<cognome>Rossi</cognome>
		<telefono>3337658390</telefono>
		<email>mario.rossi@tim.it</email>
		<note>compagno di squadra</note>
	</contatto>
	<contatto eta="35">
		<nome>Claudio</nome>
		<cognome>Bianchi</cognome>
		<telefono>3352672537</telefono>
		<email>claudio.white@virgilio.it</email>
	</contatto>
</rubrica>
*/