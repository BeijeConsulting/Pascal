package xmlparser4.document;

import java.util.List;

public class Documento implements Nodo {
    private Elemento root;

    public Elemento getRoot() {
        return root;
    }

    public void setRoot(Elemento root) {
        this.root = root;
    }

    public Documento(){}

    

    public String toString() {
    	return root.getTagName();
    }
    
    @Override
    public List<Nodo> getChildNodes() {
    	List<Nodo> figliRoot = this.getRoot().getChildNodes();
        return figliRoot;
    }

    
}
