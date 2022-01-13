package xmlparser4.document;

public class Documento implements Gerarchico{
    private Elemento root;

    public Elemento getRoot() {
        return root;
    }

    public void setRoot(Elemento root) {
        this.root = root;
    }

    public Documento(){}

    @Override
    public void addFiglio(Elemento figlio) {
        // setRoot(figlio);   TODO     
    }

    
}
