package br.com.cepservice.model;

import br.com.cepservice.model.vo.Endereco;

public class AddressOutput {

    private static final String SUCCESS_MESSAGE = "CEP Encontrado";
    private static final String INVALID_MESSAGE = "CEP Inválido";

    private String mensagem;
    private Endereco endereco;

    public AddressOutput(String mensagem) {
        this.mensagem = mensagem;
    }

    public AddressOutput(Address address, String mensagem) {
        this.endereco = new Endereco(address);
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setAddress(Endereco endereco) {
        this.endereco = endereco;
    }

    public static AddressOutput buildDefaulInvalid() {
        return new AddressOutput(INVALID_MESSAGE);
    }

    public static AddressOutput buildDefaulSuccess(Address cep) {
        return new AddressOutput(cep, SUCCESS_MESSAGE);
    }
}
