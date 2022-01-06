package it.beije.pascal.ripasso;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLesercizio {

	public static void main(String[] args) throws Exception {
		
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		
		Document document = documentBuilder.parse("rubrica.xml");
		
		Element root = document.getDocumentElement();
		System.out.println("il Element root è " + root.getTagName());
		
		NodeList childNodes = root.getChildNodes();
		System.out.println(childNodes.getLength());
		
		for(int i=0; i<childNodes.getLength(); i++) {
			Node node = childNodes.item(i);
			if (node instanceof Element) {
				Element el = (Element)node;
				System.out.println("node " + i + " : " + el.getAttribute("età"));
				System.out.println("node " + i + " : " + el.getTextContent());
				
				NodeList nomi = el.getElementsByTagName("nome");
				System.out.println(nomi.getLength());
				System.out.println(nomi.item(i).getTextContent());
				
				NodeList cognomi = el.getElementsByTagName("cognome");
				System.out.println(cognomi.item(i).getTextContent());
				
				List<Element> values = getChildElements(el);
				for (Element value : values) {
					System.out.println(value.getTagName() + " : " + value.getTextContent());
				}
			}
		}
		
		
		
		

	}
	
	public static List<Element> getChildElements(Element element) {
		List<Element> childElements = new ArrayList<Element>();
		NodeList nodeList = element.getChildNodes();
		for (int n = 0; n < nodeList.getLength(); n++) {
			if (nodeList.item(n) instanceof Element) childElements.add((Element)nodeList.item(n));
		}
		
		return childElements;
	}

}
