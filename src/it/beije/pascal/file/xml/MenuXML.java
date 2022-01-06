package it.beije.pascal.file.xml;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import it.beije.pascal.rubrica.model.Contatto;

public class MenuXML {
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws Exception {
		boolean continua = true;
		while (continua) {
			stampaMenu();
			int scelta = leggiScelta();
			switch (scelta) {
			case 0:
				continua = false;
				break;
			case 2:
				stampaContatti();
				break;
			}
		}

	}

	private static void stampaMenu() {
		System.out.println("==== MENU ====");
		System.out.println("0: esci");
		System.out.println("1: scrivi lista contatti (cognome decrescente)");
		System.out.println("2: stampa lista contatti");
		System.out.println("3: cerca contatto");
		System.out.println("4: inserisci contatto");
		System.out.println("5: modifica contatto");
		System.out.println("6: cancella contatto");
		System.out.println("7: trova contatti duplicati");
		System.out.println("8: unisci contatti duplicati\n");
	}

	private static int leggiScelta() {
		System.out.print("Inserisci scelta: ");
		int scelta = scanner.nextInt();
		while (scelta < 0 || scelta > 8) {
			System.out.print("\nScelta errata,reinserire: ");
			scelta = scanner.nextInt();		
		}
		return scelta;
	}

	private static void stampaContatti() {
		try {
			// Creazione oggetto per lavorare con file xml
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse("C:/Users/ema29/javafile/rubrica.xml");

			// Elemento root (tag padre)
			Element root = document.getDocumentElement();
			// Estrapolazione tag figli
			NodeList childNodes = root.getChildNodes();
			Contatto contatto = new Contatto();
			List<Contatto> contatti = new ArrayList<>();
			
			for (int i = 0; i < childNodes.getLength(); i++) {
				Node node = childNodes.item(i);				
				if (node instanceof Element) {
					Element el = (Element) node;
					List<Element> values = getChildElements(el);				
					for (Element value : values) {	
						System.out.println(value.getTextContent());
						switch(value.getTagName()) {						
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
					for(Contatto c:contatti) {
						System.out.println(c);
					}
					System.out.println("-------------------------------");											
				}	
				//System.out.println(value.getTagName() + " : " + value.getTextContent());
			}
			
			ordinaContatti(contatti);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	
	private static void ordinaContatti(List<Contatto> contatti) {
		contatti.sort(new Comparator<Contatto>() {
			public int compare(Contatto c1, Contatto c2) {							
				return c1.getNome().compareTo(c2.getNome());
			}	
			
		});		
	}

	private static void appunto1() throws Exception {
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

	private static void appunto2(List<Contatto> list) throws Exception {
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
	
	
}
