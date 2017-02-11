package br.com.cepservice;

import br.com.cepservice.model.Address;

import java.util.Optional;

public interface PersistenceAdapter {
    Optional<Address> findAddressByCep(String cep);
}
