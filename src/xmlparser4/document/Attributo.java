package xmlparser4.document;

import java.util.ArrayList;
import java.util.List;

public class Attributo implements Nodo{
    private String name;
    private String value;

    public Attributo(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
    
    public String toString() {
    	return name + ":" + value;
    }

    @Override
    public List<Nodo> getChildNodes() { //ritorna vuoto
        return new ArrayList<Nodo>();
    }
}