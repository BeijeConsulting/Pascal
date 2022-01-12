package it.beije.pascal.file;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

	public static final String PATH = "./rubrica.xml";
	
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

	
	public static List<Contatto> allContacts(String categoria) throws Exception {
		List<Contatto> contatti = XMLmanager.loadRubricaFromXML(PATH);

		StringBuilder stringBuilder = new StringBuilder("get")
				.append(categoria.toLowerCase().substring(0, 1).toUpperCase() + categoria.substring(1));

		Collections.sort(contatti, new Comparator<Contatto>() {
			Class<?> c = Class.forName("it.beije.pascal.rubrica.Contatto");

			public int compare(Contatto a1, Contatto a2) {
				Method method = null;
				String a = "", b = "";
				try {
					method = c.getDeclaredMethod(stringBuilder.toString());
					a = (String) method.invoke(a1);
					b = (String) method.invoke(a2);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
						| SecurityException | NoSuchMethodException e) {
					e.printStackTrace();
				}

				return a.compareTo(b);
			}
		});

		return contatti;

	}

	public static List<Contatto> find(String s, String categoria) throws Exception {
		List<Contatto> contatti = XMLmanager.loadRubricaFromXML(PATH);
		List<Contatto> trovati = new ArrayList<Contatto>();

		Class<?> c = Class.forName("it.beije.pascal.rubrica.Contatto");

		StringBuilder stringBuilder = new StringBuilder("get")
				.append(categoria.toLowerCase().substring(0, 1).toUpperCase() + categoria.substring(1));

		for (Contatto cont : contatti) {
			Method method = c.getDeclaredMethod(stringBuilder.toString());
			String a = (String) method.invoke(cont);
			if (a.equals(s))
				trovati.add(cont);
		}
		
		return trovati;
	}

	public static void insert(Contatto c) throws Exception {
		List<Contatto> contatti = XMLmanager.loadRubricaFromXML(PATH);

		contatti.add(c);
		
		XMLmanager.writeRubricaXML(contatti, PATH);
	}

	public static void deleteContatto(Contatto c) throws Exception {
		List<Contatto> contatti = XMLmanager.loadRubricaFromXML(PATH);
		boolean contains = false;
		for (Contatto contatto : contatti) {
			if (contatto.equals(c)) {
				contains = true;
			}
		}
		if (contains) {
			contatti.remove(c);
			XMLmanager.writeRubricaXML(contatti, PATH);
		} else {
			System.out.println("nessun contatto trovato XML");
		}
	}

	public static Set<Contatto>  trovaContattiDuplicati() throws Exception {
		List<Contatto> contatti = XMLmanager.loadRubricaFromXML(PATH);

        Set<Contatto> items = new HashSet<>();
        
        return contatti.stream()
            .filter(n -> !items.add(n))
            .collect(Collectors.toSet());		
	}

	public static void unisciContatti() throws Exception {
		List<Contatto> contatti = XMLmanager.loadRubricaFromXML(PATH);

		List<Contatto> uniqueContatti = new ArrayList<Contatto>();

		if (contatti.size() == 0)
			return;

		for (Contatto contatto : contatti) {
			if (!uniqueContatti.contains(contatto))
				uniqueContatti.add(contatto);
		}

		XMLmanager.writeRubricaXML(contatti, PATH);
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
