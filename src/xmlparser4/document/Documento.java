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

    public String getTreeString(){
        //TODO
        return null;
    }

    public String toString() {
    	return root.getTagName();
    }
    
    @Override
    public List<Nodo> getChildNodes() {
    	List<Nodo> figliRoot = this.getChildNodes();
        return figliRoot;
    }

    
}
