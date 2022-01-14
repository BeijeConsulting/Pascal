package it.beije.pascal.XMLParser2;

import java.util.List;

public class DocumentElement {
	
	//cosa deve avere un elemento?
	//1. un nome (tagname) tipo <contatto> è un elemento, e il suo nome è contatto
	//2. un valore testuale al suo interno: <contatto>nome del contatto</contatto>, in questo caso il valore è "nome del contatto"
	//3. EVENTUALMENTE, una lista di nodi figli
	
	private String testoInterno;
	private String nome;
	private String textValue;
	private List<DocumentElement> nodi;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String gettextValue() {
		return textValue;
	}
	public void settextValue(String textValue) {
		this.textValue = textValue;
	}
	public List<DocumentElement> getNodi() {
		return nodi;
	}
	public void setNodi(List<DocumentElement> nodi) {
		this.nodi = nodi;
	}
	public String getTestoInterno() {
		return testoInterno;
	}
	public void setTestoInterno(String testoInterno) {
		this.testoInterno = testoInterno;
	}
	
	public void parseAncora(String testoInterno, int indicatore) throws Exception {
		
		if(testoInterno.contains("<")) {
		XMLParser2 xmlp = new XMLParser2();
		xmlp.parse(testoInterno);
		} else {
			return;
		}
	}
	
}
