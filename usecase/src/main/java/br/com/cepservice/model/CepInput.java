package br.com.cepservice.model;

public class CepInput {

    private String value;

    public CepInput(String value) {
        this.value = normalizeValue(value);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isValid() {
        return this.value != null && this.value.length() == 8;
    }

    private String normalizeValue(String value) {
        return value.replace("-", "").replace(".", "");
    }
}
