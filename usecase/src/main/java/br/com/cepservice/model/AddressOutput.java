package br.com.cepservice.model;

public class AddressOutput {

    private static final String SUCCESS_MESSAGE = "success";
    private static final String NOT_FOUND = "not found";
    private static final String INVALID_MESSAGE = "invalid";

    private String message;
    private Address address;

    public AddressOutput(String message) {
        this.message = message;
    }

    public AddressOutput(Address address, String message) {
        this.address = address;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public static AddressOutput buildDefaulInvalid() {
        return new AddressOutput(INVALID_MESSAGE);
    }

    public static AddressOutput buildDefaulSuccess(Address cep) {
        return new AddressOutput(cep, SUCCESS_MESSAGE);
    }

    public static AddressOutput buildDefaulNotFound() {
        return new AddressOutput(NOT_FOUND);
    }
}
