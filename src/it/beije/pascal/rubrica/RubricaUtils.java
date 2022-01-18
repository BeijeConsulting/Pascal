package it.beije.pascal.rubrica;

import java.util.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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

public class RubricaUtils {
	
	public static List<Contatto> loadRubricaFromCSV(String pathFile, String separator) throws IOException {
		
		List<Contatto> contatti = new ArrayList<Contatto>();
		
		if(separator == null) {
			throw new NullPointerException("Non hai inserito un separatore");
		}
		
		File file = new File(pathFile);
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		
		String riga;
		Contatto contatto;
		String[] r;
		
		while(br.ready()) {
			riga = br.readLine();
			r = riga.split(separator);
			
			contatto = new Contatto();
			contatto.setCognome(r[0]);
			contatto.setNome(r[1]);
			contatto.setEmail(r[2]);
			contatto.setTelefono(r[3]);
			
			contatti.add(contatto);
		}
		
		fr.close();
		br.close();
		
		contatti.remove(0);
		
		return contatti;
	}
	
	public static List<Element> getChildElements(Element element) {
		List<Element> childElements = new ArrayList<Element>();
		NodeList nodeList = element.getChildNodes();
		for (int n = 0; n < nodeList.getLength(); n++) {
			if (nodeList.item(n) instanceof Element) childElements.add((Element)nodeList.item(n));
		}
		
		return childElements;
	}
	
	public static List<Contatto> loadRubricaFromXML(String pathFile) throws Exception {
		
		//l'obiettivo è il medesimo del metodo precedente, ma al posto di un file csv usiamo un file xml
		
		List<Contatto> contatti = new ArrayList<Contatto>();
		
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		
		Document document = documentBuilder.parse(new File(pathFile));
		
		Element root = document.getDocumentElement(); //rubrica
		
		NodeList childNodes = root.getChildNodes(); //contatto x 2

		for (int i = 0; i < childNodes.getLength(); i++) {
			Contatto contatto = new Contatto();
			Node node = childNodes.item(i);
			if (node instanceof Element) {
				Element el = (Element)node;
				List<Element> values = getChildElements(el); //nome, cognome, email, note, telefono 
				for (Element value : values) { //quando non sappiamo quanti tag ci saranno, è meglio fare un ciclo per scorrerli 
					switch (value.getTagName()) {
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
			}
		}
		return contatti;
	}
	
	public static void writeRubricaCSV(List<Contatto> contatti, String pathFile, String separator) throws Exception {
		
		//metodo che prende in input una lista di contatti, il percorso di un file, e un separatore
		//scrive nel file tutti i contatti che abbiamo nella lista

		StringBuilder sbReader = new StringBuilder();
		FileReader fr = new FileReader(new File(pathFile));
		BufferedReader br = new BufferedReader(fr);
		
		while(br.ready()) {
			String s = br.readLine() + "\n";
			sbReader.append(s);
		}
		
		File file = new File(pathFile);
		FileWriter fw = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(fw);
		
		bw.write(sbReader.toString());
		
		for(int i = 0; i < contatti.size(); i++) {
			StringBuilder sbWriter = new StringBuilder();
			Contatto cont = contatti.get(i);
			
			sbWriter.append(cont.getNome())
				.append(separator)
				.append(cont.getCognome())
				.append(separator)
				.append(cont.getEmail())
				.append(separator)
				.append(cont.getTelefono())
				.append(separator)
				.append(cont.getNote())
				.append("\n");
			
			bw.write(sbWriter.toString());	
		}
		bw.close();
	}
	
	public static void writeRubricaXML(List<Contatto> contatti, String pathFile) throws Exception {
		
		//medesimo obiettivo, ma invece di scrivere un file csv scriviamo un file xml
		
		File file = new File(pathFile);
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		
		if(file.length() == 0 || !file.exists()) {
			file = RubricaUtils.createBasicXML(file);
		}
			
		Document document = db.parse(file);
			
		Element root = document.getDocumentElement();
			
		for(Contatto cont : contatti) {
			Element newElement = document.createElement("contatto");
			root.appendChild(newElement);
				
			Element nome = document.createElement("nome");
			nome.setTextContent(cont.getNome());
			Element cognome = document.createElement("cognome");
			cognome.setTextContent(cont.getCognome());
			Element telefono = document.createElement("telefono");
			telefono.setTextContent(cont.getTelefono());
			Element email = document.createElement("email");
			email.setTextContent(cont.getEmail());
			Element note = document.createElement("note");
			note.setTextContent(cont.getNote());
			
			newElement.appendChild(nome);
			newElement.appendChild(cognome);
			newElement.appendChild(telefono);
			newElement.appendChild(email);
			newElement.appendChild(note);
		}
		
		TransformerFactory factory = TransformerFactory.newInstance();
		Transformer transformer = factory.newTransformer();
		DOMSource dom = new DOMSource(document);
		
		StreamResult str = new StreamResult(file);
		
		transformer.transform(dom, str);
	}
	
	private static File createBasicXML(File file) throws Exception {
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		
		Document doc = db.newDocument();
		doc.setXmlVersion("1.0");
		
		Element root = doc.createElement("rubrica");
		doc.appendChild(root);
		
		TransformerFactory factory = TransformerFactory.newInstance();
		Transformer transformer = factory.newTransformer();
		DOMSource dom = new DOMSource(doc);
			
		StreamResult str = new StreamResult(file);
			
		transformer.transform(dom, str);
		
		return file;
	}
	
	public static void main(String[] args) throws Exception {
//		RubricaUtils ru = new RubricaUtils();

//		List<Contatto> contatti = ru.loadRubricaFromCSV("/javaFiles/rubrica.csv", "\t");
//		System.out.println(contatti);
//		
//		List<Contatto> contattiXML = ru.loadRubricaFromXML("/javaFiles/rubrica.xml");
//		System.out.println(contattiXML);

//		RubricaUtils.writeRubricaXML(contatti, "/javaFiles/emptyXML.xml");
	}
}
