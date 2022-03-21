package co.com.sofka.utilidades;

public enum EnumJson {
    NOMBRE("name"),
    TRABAJO("job");

    private final String value;

    EnumJson(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
