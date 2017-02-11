package br.com.cepservice.model.vo;

import br.com.cepservice.model.Address;

public class Endereco {

    private String cep;
    private String rua;
    private String bairo;
    private String cidade;
    private String estado;

    public Endereco(Address address) {
        this.cep = address.getCep();
        this.rua = address.getStreet();
        this.bairo = address.getNeighborhood();
        this.cidade = address.getCity();
        this.estado = address.getState();
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairo() {
        return bairo;
    }

    public void setBairo(String bairo) {
        this.bairo = bairo;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
