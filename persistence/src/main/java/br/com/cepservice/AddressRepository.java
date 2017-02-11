package br.com.cepservice;

import br.com.cepservice.model.Address;

import java.util.Optional;

public class AddressRepository implements PersistenceAdapter {
    @Override
    public Optional<Address> findAddressByCep(String cep) {
        return Optional.of(new Address(cep, "street", "neighborhood", "city", "state"));
    }
}
