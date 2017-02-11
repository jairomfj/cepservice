package br.com.cepservice;


import br.com.cepservice.model.Address;
import br.com.cepservice.model.CepInput;
import br.com.cepservice.model.AddressOutput;

import java.util.Optional;

public class FindAddressUsecase {

    private final PersistenceAdapter persistenceAdapter;

    public FindAddressUsecase(PersistenceAdapter persistenceAdapter) {
        this.persistenceAdapter = persistenceAdapter;
    }

    public AddressOutput execute(CepInput cepInput) {
        if(cepInput == null) {
            throw new IllegalArgumentException("Cep cannot be null");
        }

        AddressOutput addressOutput = AddressOutput.buildDefaulInvalid();
        if(cepInput.isValid()) {
            addressOutput = AddressOutput.buildDefaulNotFound();
            Optional<Address> cepOptional = findAddressBy(cepInput.getValue());
            if(cepOptional.isPresent()) {
                addressOutput = AddressOutput.buildDefaulSuccess(cepOptional.get());
            }
        }

        return addressOutput;
    }

    private Optional<Address> findAddressBy(String cep) {
        Optional<Address> cepOptional = this.persistenceAdapter.findAddressByCep(cep);
        if (cepOptional.isPresent() || cep.equals("00000000")) {
          return cepOptional;
        }

        return findAddressBy(modifyValueWithZero(cep));
    }

    private String modifyValueWithZero(String value) {
        StringBuilder stringBuilder = new StringBuilder(value);
        for(int i = stringBuilder.length() - 1; i >= 0 ; i--) {
            if(stringBuilder.charAt(i) != '0') {
                stringBuilder.setCharAt(i, '0');
                break;
            }
        }

        return stringBuilder.toString();
    }
}
