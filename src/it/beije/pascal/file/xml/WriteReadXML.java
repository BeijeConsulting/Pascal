package it.beije.pascal.file.xml;

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

import it.beije.pascal.rubrica.model.Contatto;

public class WriteReadXML {

	public static void main(String[] args) throws Exception {
		
		// Scrittura
		Contatto contatto1 = new Contatto("Emanuele", "Corona", "3335877155", "emacorona@gmail.com", "descrizione");
		Contatto contatto2 = new Contatto("Mario", "Rossi", "333344455", "mariorossi@gmail.com", "il solito mario");		
		List<Contatto> contatti = new ArrayList<>();
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

}
