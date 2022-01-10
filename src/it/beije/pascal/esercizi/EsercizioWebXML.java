package it.beije.pascal.esercizi;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class EsercizioWebXML {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		File file = new File("rubrica.xml");
		//usiamo DocumentBuilder per usare il metodo parse() su un file XML
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document document = db.parse(file);
		document.getDocumentElement().normalize();
        System.out.println("Root Element :" + document.getDocumentElement().getNodeName());
        //il metodo getElementByTagName() recupera ogni nodo dell'XML e li mette in una NodeList
        NodeList nList = document.getElementsByTagName("contatto");
        System.out.println("----------------------------");
        //tramite un ciclo scorriamo ogni nodo
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            System.out.println("\nCurrent Element :" + nNode.getNodeName());
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
            	//ogni nodo va assegnato a un Element per usare i metodi per recuperare i dati
                Element eElement = (Element) nNode;
                System.out.println("Contatto età : " + eElement.getAttribute("età"));
                System.out.println("Cognome : " + eElement.getElementsByTagName("cognome").item(0).getTextContent());
                System.out.println("Nome : " + eElement.getElementsByTagName("nome").item(0).getTextContent());
                System.out.println("Indirizzo : " + eElement.getElementsByTagName("indirizzo").item(0).getTextContent());
                System.out.println("Telefono : " + eElement.getElementsByTagName("telefono").item(0).getTextContent());
                System.out.println("Email : " + eElement.getElementsByTagName("email").item(0).getTextContent());
            }
        }
	}

}
