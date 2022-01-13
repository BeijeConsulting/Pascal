package xmlparser4.document;

import java.util.List;

/**
 * An Element is defined as everything between a tag start and a tag end
 */
public class Elemento {
    private String tagName;
    List<Attributo> attributi;
    List<Elemento> figliList;
}
