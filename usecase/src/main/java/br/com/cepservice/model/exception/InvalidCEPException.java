package br.com.cepservice.model.exception;

public class InvalidCEPException extends Exception {

    public InvalidCEPException(String message) {
        super(message);
    }
}
