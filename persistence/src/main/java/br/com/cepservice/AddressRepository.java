package br.com.cepservice;

import br.com.cepservice.builder.AddressBuilder;
import br.com.cepservice.model.Address;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AddressRepository implements AddressRepositoryAdapter {

    static List<AddressEntity> addressList = new ArrayList<>();
    static {
        addressList.add(new AddressEntity("12345600", "Rua 00", "Bairro 00", "Cidade 00", "Estado 00"));
        addressList.add(new AddressEntity("12345601", "Rua 01", "Bairro 01", "Cidade 01", "Estado 01"));
        addressList.add(new AddressEntity("12345602", "Rua 02", "Bairro 02", "Cidade 02", "Estado 02"));
        addressList.add(new AddressEntity("12345603", "Rua 03", "Bairro 03", "Cidade 03", "Estado 03"));
        addressList.add(new AddressEntity("12345604", "Rua 04", "Bairro 04", "Cidade 04", "Estado 04"));
        addressList.add(new AddressEntity("12345605", "Rua 05", "Bairro 05", "Cidade 05", "Estado 05"));
    }

    @Override
    public Optional<Address> findAddressByCep(String cep) {
        return addressList.stream()
                .filter((AddressEntity addressEntity) -> addressEntity.getCep().equals(cep))
                .findFirst()
                .map(AddressBuilder::build);
    }
}
