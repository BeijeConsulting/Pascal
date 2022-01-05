package it.beije.pascal.rubrica;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class RubricaXML {
	public static void main(String[] args) {
		List<Contatto> contatti= loadRubricaFromXML("./rubrica.xml");
		writeRubricaXML(contatti, "./nuova_rubrica.xml");

	}

	public static List<Contatto> loadRubricaFromXML(String pathFile) {
		List<Contatto> contattiList = new ArrayList<>();
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(pathFile);

			Element root = document.getDocumentElement();
			System.out.println("root : " + root.getTagName());

			NodeList contattiNodes = root.getElementsByTagName("contatto");

			for(int i =0; i<contattiNodes.getLength(); i++) {
				Node contatto = contattiNodes.item(i);
				if(contatto instanceof Element) {
					Contatto c = new Contatto();
					c.setNome(extractCampoFromNode(contatto, "nome"));
					c.setCognome(extractCampoFromNode(contatto, "cognome"));
					c.setEmail(extractCampoFromNode(contatto, "email"));
					c.setTelefono(extractCampoFromNode(contatto, "telefono"));
					c.setNote(extractCampoFromNode(contatto, "note"));
					contattiList.add(c);
				}
			}

		} catch (DOMException | ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		return contattiList;
	}


	private static void writeRubricaXML(List<Contatto> contatti, String pathFile) {
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

			Document doc = documentBuilder.newDocument();

			Element rubricaElement = doc.createElement("rubrica");
			doc.appendChild(rubricaElement);

			for(Contatto c: contatti) {

				Element contattoElement = doc.createElement("contatto");

				Element cognome = doc.createElement("cognome");
				cognome.setTextContent(c.getCognome());
				contattoElement.appendChild(cognome);

				Element nome = doc.createElement("nome");
				nome.setTextContent(c.getNome());
				contattoElement.appendChild(nome);

				Element telefono = doc.createElement("telefono");
				telefono.setTextContent(c.getTelefono());
				contattoElement.appendChild(telefono);

				Element email = doc.createElement("email");
				email.setTextContent(c.getEmail());
				contattoElement.appendChild(email);

				Element note = doc.createElement("note");
				note.setTextContent(c.getNote());
				contattoElement.appendChild(note);

				rubricaElement.appendChild(contattoElement);
			}


			 // create the xml file
            //transform the DOM Object to an XML File
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(doc);
            StreamResult streamResult = new StreamResult(new File(pathFile));
            StreamResult streamOut = new StreamResult(System.out);
 
            transformer.transform(domSource, streamResult);

            transformer.transform(domSource, streamOut);
 
		}catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}


	private static String extractCampoFromNode(Node contatto, String name) throws DOMException {
		Node campoNode;
		try {
			campoNode = ((Element) contatto).getElementsByTagName(name).item(0);
			return campoNode.getFirstChild().getNodeValue();
		} catch (NullPointerException e) {
		}
		return null;
	}


	public static List<Element> getChildElements(Element element) {
		List<Element> childElements = new ArrayList<>();
		NodeList nodeList = element.getChildNodes();
		for (int n = 0; n < nodeList.getLength(); n++) {
			if (nodeList.item(n) instanceof Element) childElements.add((Element)nodeList.item(n));
		}

		return childElements;
	}




}
