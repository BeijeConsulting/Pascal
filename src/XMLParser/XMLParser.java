package XMLParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class XMLParser {

	public StringBuilder xml;
	BufferedReader bufferedReader;
	FileReader reader;
	private List<String> documentString = new ArrayList<String>();

	public void parse(String path) throws IOException {
		File file = new File(path);
		reader = new FileReader(file);
		bufferedReader = new BufferedReader(reader);

		if (!controllo(bufferedReader))
			System.out.println("non si può continuare");

		reader = new FileReader(file);
		bufferedReader = new BufferedReader(reader);
		Element root = new Element(bufferedReader.readLine());
		RecursivePrint(documentString, 1, 0, root);

		for (Element e : root.getChilds()) {
			System.out.println(e);
			if (e.getTagName().equals("<contatto eta=\"30\">"))
				for (Element el : e.getChilds()) {
					System.out.println("\t" + el);
				}
		}

	}

	public void RecursivePrint(List<String> arr, int index, int level, Element parent) {
		if (index == arr.size()) {
			return;
		}

		for (int i = 0; i < level; i++)
			System.out.print("\t");

		if (arr.get(index).contains("</")) {
			if (!arr.get(index).contains("</contatto")) {
				Element el = new Element(arr.get(index).trim());
				System.out.println(level + " </");
				parent.addChild(el);
				RecursivePrint(arr, ++index, level, parent);
			} else {
				System.out.println("dasd");
				RecursivePrint(arr, ++index, level - 1, parent);
			}
		} else {
			System.out.println(level + " " + arr.get(index));
			Element el = new Element(arr.get(index).trim());
			parent.addChild(el);
			RecursivePrint(arr, ++index, level + 1, el);
		}
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

	// torna tutti i nodi "figli" interni all'elemento su cui viene eseguito
	public void getChildNodes() {

	}

	public void getChildElements() {
	}// torna i soli elementi figli dell'elemento su cui viene eseguito

	// torna TUTTI gli elementi con quello specifico nome
	public void getElementsByTagName(String tagName) {

	}

}
