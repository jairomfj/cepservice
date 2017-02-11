package br.com.cepservice.model;

public class CepInput {

    private String cep;

    public CepInput(String cep) {
        this.cep = normalizeValue(cep);
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public boolean isValid() {
        return this.cep != null && this.cep.length() == 8;
    }

    private String normalizeValue(String value) {
        return value.replace("-", "").replace(".", "");
    }

    public void normalizeValue() {
        this.cep = this.cep.replace("-", "").replace(".", "");
    }
}
