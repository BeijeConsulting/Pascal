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
        attributi = new ArrayList<>();
    }
    
    private Elemento padre;
    private String tagName;
    List<Elemento> figliList;
    List<Attributo> attributi;

	public Elemento getPadre() {
        return padre;
    }

    public void setPadre(Elemento padre) {
        this.padre = padre;
    }

    public String getTagName() {
        return tagName;
    }

    @Override
    public String toString() {
        return "Elemento [padre=" + padre.getTagName() + ", tagName=" + tagName + "figli: " +"]";
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }


    public void setAttributi(List<Attributo> attributi) {
        this.attributi = attributi;
    }

    public List<Elemento> getFigliList() {
		return this.figliList;
	}

	public List<Attributo> getAttributi() {
		return this.attributi;
	}

	public void addFiglio(Elemento figlio) {
        figliList.add(figlio);
        figlio.setPadre(this);
    }
	
	public String getAttribute(String attribute) {
		for(Attributo a : attributi) {
			if(a.getName().equalsIgnoreCase(attribute)) {
				return a.getValue();
			} 
		}
		return "Attributo non trovato";
	}

	// getElementsByTagName(String tagName) //torna TUTTI gli elementi con quello specifico nome
	
	public List<Elemento> getElementsByTagName(String tagName) {
        List<Elemento> risultato = new ArrayList<>();
        if(this.getTagName().equalsIgnoreCase(tagName) && this !=null)
            risultato.add(this);
		for(Elemento e: figliList) {
            List<Elemento> parziale = e.getElementsByTagName(tagName);
            if(!parziale.isEmpty())
				risultato.addAll(parziale);
			}
		return risultato;
	}
    
}
