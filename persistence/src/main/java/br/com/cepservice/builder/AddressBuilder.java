package br.com.cepservice.builder;

import br.com.cepservice.AddressEntity;
import br.com.cepservice.model.Address;

public class AddressBuilder {

    public static Address build(AddressEntity addressEntity) {
        return new Address(addressEntity.getCep(), addressEntity.getStreet(), addressEntity.getNeighborhood(),addressEntity.getCity(),addressEntity.getState());
    }
}
