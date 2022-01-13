package it.beije.pascal.XMLParser2;

public class Document {
	//cosa deve avere un documento?
	//1. versione xml (nel tag di intestazione)
	//2. encoding (tipo UTC-8, sempre nell'instazione)
	//3. un testo
	//4. un elemento root
	
	private double versione;
	private String encoding;
	private String testo;
	private DocumentElement root;
	
	public double getVersione() {
		return versione;
	}
	public void setVersione(double versione) {
		this.versione = versione;
	}
	public String getEncoding() {
		return encoding;
	}
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
	public String getTesto() {
		return testo;
	}
	public void setTesto(String testo) {
		this.testo = testo;
	}
	public DocumentElement getRoot() {
		return root;
	}
	public void setRoot(DocumentElement root) {
		this.root = root;
	}
}
