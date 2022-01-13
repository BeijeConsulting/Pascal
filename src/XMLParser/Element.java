package XMLParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Element {

	private String tagName;
	private String textContent;
	private HashMap<String, String> attributes = new HashMap<String, String>();
	private List<Element> childs = new ArrayList<>();

	public Element() {
		super();
	}

	public Element(String elementName) {
		super();
		this.tagName = elementName;
	}
	
	// torna il nome del tag
	public String getTagName() {
		return this.tagName;
	}

	// torna il contenuto del tag
	public String getTextContent() {
		return this.textContent;
	}

	public void setTextContent(String textContent) {
		this.textContent = textContent;
	}

	// torna l'elenco degli attributi dell'elemento
	public HashMap<String, String> getAttributes() {
		return attributes;
	}

	public void setAttributes(String key, String value) {
		this.attributes.put(key, value);
	}
	
	// torna il valore dell'attributo specificato
	public void getAttribute(String attribute) {
	}


	



}
