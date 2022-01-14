package xmlparser4.document;

public class Nodo extends Elemento{
    String text;

    public Nodo(String tagName ){
        super(tagName);
    }

    public void setText(String nodeText) {
        this.text = nodeText;
    }
    
    public String getTextContent() {
    	return this.text;
    }
}
