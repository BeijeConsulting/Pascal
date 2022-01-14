package xmlparser4.document;

import java.util.ArrayList;
import java.util.List;

public class Testo implements Nodo {

    private String text;

    public Testo(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public List<Nodo> getChildNodes() {
        return new ArrayList<>();
    }

    @Override
    public String toString() {
        return text;
    }
    
}
