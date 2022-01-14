package xmlparser4.document;

public class Attributo {
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
}