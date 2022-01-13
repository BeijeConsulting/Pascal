package it.beije.pascal.XMLParser2;

public class DocumentNode {
	//cosa deve avere un nodo?
	//1. dobbiamo sapere se è un elemento oppure no
	//2. eventualmente può avere del contenuto testuale
	
	//tutti gli elementi sono nodi, ma non tutti i nodi sono elementi, di conseguenza ha senso che Element sia figlio di Node
	//quindi dobbiamo sapere anche se un nodo è un elemento oppure un "nodo sporco"
	
	private String contenuto;
	private boolean isElemento;

	public String getContenuto() {
		return contenuto;
	}

	public void setContenuto(String contenuto) {
		this.contenuto = contenuto;
	}

	public boolean isElemento() {
		return isElemento;
	}

	public void setElemento(boolean isElemento) {
		this.isElemento = isElemento;
	}
	
}
