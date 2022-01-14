package xmlparser4.document;

public class Documento {
    private Elemento root;

    public Elemento getRoot() {
        return root;
    }

    public void setRoot(Elemento root) {
        this.root = root;
    }

    public Documento(){}

    public void addFiglio(Elemento figlio) {
        setRoot(figlio);    
    }

//     //TODO getRootElement() //torna l'elemento root
// getChildNodes() //torna tutti i nodi "figli" interni all'elemento su cui viene eseguito
// getChildElements() //torna i soli elementi figli dell'elemento su cui viene eseguito


    
}
