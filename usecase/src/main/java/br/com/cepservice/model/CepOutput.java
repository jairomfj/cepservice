package br.com.cepservice.model;

public class CepOutput {

    private static final String ERROR_MESSAGE = "Error";

    private String message;
    private Cep cep;

    public CepOutput(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Cep getCep() {
        return cep;
    }

    public void setCep(Cep cep) {
        this.cep = cep;
    }

    public static CepOutput buildDefaulFailure() {
        return new CepOutput(ERROR_MESSAGE);
    }
}
