package xmlparser4.document;

public class Nodo extends Elemento{
    String argumenString;

    public Nodo(String tagName ){
        super(tagName);
    }

    public void setText(String nodeText) {
        this.argumenString = nodeText;
    }
}
