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
        setRoot(figlio);    
    }

//     //TODO getRootElement() //torna l'elemento root
// getChildNodes() //torna tutti i nodi "figli" interni all'elemento su cui viene eseguito
// getChildElements() //torna i soli elementi figli dell'elemento su cui viene eseguito
// getElementsByTagName(String tagName) //torna TUTTI gli elementi con quello specifico nome

    
}
