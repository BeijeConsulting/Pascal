package it.beije.pascal.xml;

import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import it.beije.pascal.rubrica.Contatto;

public class ManagerXML {

	public static void main(String[] args) throws Exception {
		
		
		
		

	}
	private static void scrivi(List<Contatto> list) throws Exception {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		
		Document doc = documentBuilder.newDocument();
		
		// Crea il tag contatti ma non lo scrive
		Element contatti = doc.createElement("contatti");
		// Scrive il tag contatti
		doc.appendChild(contatti);
		
		// Creazione elementi
		Element contatto = doc.createElement("contatto");
		Element cognome = doc.createElement("cognome");
		Element nome = doc.createElement("nome");
		Element telefono = doc.createElement("telefono");
		Element email = doc.createElement("email");
		Element note = doc.createElement("note");
		
		// Valorizzazione degli elementi creati
		cognome.setTextContent("Corona");
		nome.setTextContent("Emanuele");
		telefono.setTextContent("3335877155");
		email.setTextContent("emacorona77@gmail.com");
		note.setTextContent("nota1");
		
		// Scrittura dei dati all'interno del file xml
		contatto.appendChild(contatto);
		contatto.appendChild(cognome);
		contatto.appendChild(note);
		contatto.appendChild(telefono);
		contatto.appendChild(email);
		contatto.appendChild(note);
		
	
		
	}
	
	
	private static void test() throws Exception{
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		// Il parse interpreta il documento
		Document document = documentBuilder.parse("percorso stringa");
		Element root = document.getDocumentElement();
		System.out.println("root: " + root.getTagName());

		// Controlliamo gli elementi figli (ma non i figli dei figli),
		// ovvero i sottotag (in questo caso <contatto>)
		NodeList contatti = root.getElementsByTagName("contatto");

		for (int i = 0; i < contatti.getLength(); i++) {
			Element contatto = (Element) contatti.item(i);
			System.out.println("Contatto " + i);
		}
		
		NodeList nomi = root.getElementsByTagName("nome");
		System.out.println(nomi.getLength());
		
		NodeList childNodes = root.getChildNodes();
		System.out.println(childNodes.getLength());
		
		for (int i = 0; i < childNodes.getLength(); i++) {
			Node node = childNodes.item(i);
			System.out.println("Contatto " + i);
		}
	}

}
