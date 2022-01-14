package XMLParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class XMLParser {

	public StringBuilder xml;
	BufferedReader bufferedReader;
	FileReader reader;
	Element root = null;
	private List<String> documentString = new ArrayList<String>();

	public void parse(String path) throws IOException {
		File file = new File(path);
		reader = new FileReader(file);
		bufferedReader = new BufferedReader(reader);

		if (!controllo(bufferedReader))
			System.out.println("non si può continuare");

		reader = new FileReader(file);
		bufferedReader = new BufferedReader(reader);

		root = new Element("rubrica");
		// System.out.println(root.getTagName());
		root = RecursivePrint(documentString, 1, 0, root, root);

		for (Element element : root.getChildNodes()) {
			System.out.println(element);
			if (element.getTagName().equals("contatto")) {
				for (Element e : element.getChildNodes()) {
					System.out.println("\t " + e);
					System.out.println("\t\t " + e.getTextContent());
				}
			}
		}
		System.out.println("##########################");
		List<Element> elements = getElementsByTagName("cognome");

		for (Element element : elements) {
			System.out.println(element.getTagName());
			System.out.println(element.getTextContent());
		}

	}

	public Element RecursivePrint(List<String> arr, int index, int level, Element parent, Element root) {
		if (index == arr.size() || index == arr.size() - 1) {
			return root;
		}

		if (arr.get(index).contains("</")) {
			if (!arr.get(index).contains(parent.getTagName())) {
				Element el = new Element(nomeTag(arr.get(index).trim()));
				parent.addChild(el);
				el.setTextContent(context(arr.get(index).trim(), el));
				RecursivePrint(arr, ++index, level, parent, root);
			} else {
				RecursivePrint(arr, ++index, level - 1, root, parent);
			}
		} else {
			Element el = new Element(nomeTag(arr.get(index).trim()));
			parent.addChild(el);
			RecursivePrint(arr, ++index, level + 1, el, root);
		}

		return root;
	}

	private String nomeTag(String tag) {
		return tag.replace("<", " ").replace(">", " ").replace("</", " ").split(" ")[1];
	}

	private String context(String tag, Element el) {
		return tag.replace("<", "").replace(">", "").replace("<", "").replace("/", "").replace(el.getTagName(), "");

	}

	private boolean controllo(BufferedReader bufferedReader) throws IOException {
		bufferedReader.readLine();

		List<String> listElement = new ArrayList<String>();

		while (bufferedReader.ready()) {
			String row = bufferedReader.readLine();
			documentString.add(row);
			row = cancellaAngolari(row);
			splitString(listElement, row);
		}
		return controlloChiusura(listElement);
	}

	private String cancellaAngolari(String row) {
		row = row.trim();
		return row.replace("<", " ").replace(">", " ");
	}

	private void splitString(List<String> listElement, String row) {
		String[] arr = row.split(" ");
		boolean contains = false;
		boolean chiusura = true;
		if (arr.length > 2) {
			chiusura = false;
			for (String string : arr) {
				if (string.contains("/")) {
					contains = true;
				}
			}
		}
		if (contains) {
			listElement.add(arr[1]);
			listElement.add(arr[arr.length - 1].substring(1));
		} else {
			if (chiusura && arr[1].contains("/"))
				listElement.add(arr[1].substring(1));
			else
				listElement.add(arr[1]);
		}

	}

	private boolean controlloChiusura(List<String> list) {
		for (String string : list) {
			if (!(Collections.frequency(list, string) % 2 == 0)) {
				System.out.println("errore sul seguente tag --> " + string);
				return false;
			}
		}
		return true;
	}

	// torna TUTTI gli elementi con quello specifico nome
	public List<Element> getElementsByTagName(String tagName) {
		List<Element> elements = new ArrayList<Element>();
		System.out.println(root.getTagName());
		test(root, tagName, elements);
		return elements;
	}

	private void test(Element e, String s, List<Element> elements) {
		System.out.println("sndna --> " + e.getTagName());
		for (Element el : e.getChildNodes()) {
			System.out.println(e.getTagName() + "  -  " + s + "  -  " + el.getTagName().equals(s));
			if (el.getTagName().equals(s)) {
				elements.add(e);
			}
			test(el, s, elements);
		}

	}

}
