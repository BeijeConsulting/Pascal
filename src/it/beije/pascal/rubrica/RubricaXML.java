package it.beije.pascal.rubrica;

import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import it.beije.pascal.rubrica.model.Contatto;
import it.beije.pascal.rubrica.util.RubricaXmlUtil;

public class RubricaXML {
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws Exception {
		boolean keepGoing = true;
		while (keepGoing) {
			writeMenu();
			int scelta = leggiScelta();
			switch (scelta) {
			case 0:
				keepGoing = false;
				break;
			case 1:
				writeContacts();
				break;
			case 3:
				findContact();
				break;
			case 4: 
				insertContact();
				break;
			}
		}

	}

	private static void writeMenu() {
		System.out.println("\n==== MENU ====");
		System.out.println("0: esci");
		System.out.println("1: stampa lista contatti");
		System.out.println("1: stampa lista contatti (cognome decrescente)");
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

	private static void writeContacts() {
		List<Contatto> contatti = RubricaXmlUtil.getContactList();
		for (Contatto contatto : contatti) {
			System.out.println(contatto);
		}
	}

	private static void findContact() {
		List<Contatto> contatti = RubricaXmlUtil.getContactList();
		Contatto contatto = new Contatto("Emanuele", "Corona", "3335877155", "emacorona@gmail.com", "descrizione");
		for (int i = 0; i < contatti.size(); i++) {
			// Overload metodo equals nella classe contatto
			if (contatto.equals(contatti.get(i))) {
				System.out.println("\nContatto: " + contatti.get(i));
				break;
			} else {
				System.out.println("\nContatto non trovato");
			}
		}
	}
	
	private static void insertContact() {
		Contatto contatto = new Contatto("Nuovo nome","Nuovo cognome","Nuovo telefono","Nuova email","Nuova nota");
		List<Contatto> contatti = RubricaXmlUtil.getContactList();
		contatti.add(contatto);		
	
		try {
			RubricaXmlUtil.insertContacts(contatti);
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
	
	private static void modificaContatto() {
		
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
