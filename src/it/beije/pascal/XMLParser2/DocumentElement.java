package it.beije.pascal.XMLParser2;

import java.util.List;

public class DocumentElement extends DocumentNode {
	
	//cosa deve avere un elemento?
	//1. un nome (tagname) tipo <contatto> è un elemento, e il suo nome è contatto
	//2. un valore testuale al suo interno: <contatto>nome del contatto</contatto>, in questo caso il valore è "nome del contatto"
	//3. EVENTUALMENTE, una lista di nodi figli
	
	private String nome;
	private String valoreTestuale;
	private List<DocumentNode> nodi;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getValore() {
		return valoreTestuale;
	}
	public void setValore(String valore) {
		this.valoreTestuale = valore;
	}
	public List<DocumentNode> getNodi() {
		return nodi;
	}
	public void setNodi(List<DocumentNode> nodi) {
		this.nodi = nodi;
	}
}
