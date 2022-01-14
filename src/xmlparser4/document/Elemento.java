package xmlparser4.document;

import java.util.ArrayList;
import java.util.List;

/**
 * An Element is defined as everything between a tag start and a tag end
 */
public class Elemento {
    public Elemento(String tagName) {
        this.tagName = tagName;
        figliList = new ArrayList<>();
    }
    //TODO ordina tutto

    private Elemento padre;

    public Elemento getPadre() {
        return padre;
    }

    public void setPadre(Elemento padre) {
        this.padre = padre;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    private String tagName;
    List<Attributo> attributi;
    List<Elemento> figliList;

    public void setAttributi(List<Attributo> attributi) {
        this.attributi = attributi;
    }

    public void addFiglio(Elemento figlio) {
        figliList.add(figlio);
        figlio.setPadre(this);
    }

    // TODO funzioni
    // getTagName() //torna il nome del tag
//     // getTextContent() //torna il contenuto del tag
//     getAttributes() //torna l'elenco degli attributi dell'elemento
// getAttribute(String attribute) //torna il valore dell'attributo specificato

    
}
