package XMLParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.security.acl.LastOwnerException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class XMLParser {

	private List<Element> document = new ArrayList<Element>();
	private List<String> documentString = new ArrayList<String>();

	public void parse(String path) throws IOException {
		File file = new File(path);
		FileReader reader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(reader);

		if (!controllo(bufferedReader))
			System.out.println("non si può continuare");

		addElement();

		for (Element element : document) {
			System.out.println(element);
		}
	}

	private void addElement() {
		int inizio, fine = 0;
		boolean root = true;
		for (int i = 0; i < documentString.size(); i++) {
			if (root) {
				String first = documentString.get(i);
				first.replace("<", "").replace(">", "");
				Element element = new Element(first);
				document.add(element);
				root = false;
				continue;
			}
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
