package it.beije.pascal.xmlparser1;

public class Tag {

	private String name;
	private String value;
	private Tag son;
	
	public String getValue() {
		
		return value;
	}
	
	public void setValue(String value) {
		
		this.value=value;
		
	}
	
	public String getName() {
		
		return name;
		
	}
	
	public void setName(String name) {
		
		this.name=name;
		
	}
	
	public Tag getSon() {
		
		return son;
		
	}
	
	public void setSon(Tag son) {
		
		this.son=son;
		
	}
	
	
}
