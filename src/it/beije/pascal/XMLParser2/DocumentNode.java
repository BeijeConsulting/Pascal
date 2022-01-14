package it.beije.pascal.XMLParser2;

//questa classe non viene usata nel parser principale
public class DocumentNode {
	//cosa deve avere un nodo?
	//1. dobbiamo sapere se è un elemento oppure no
	//2. eventualmente può avere del contenuto testuale
	
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
