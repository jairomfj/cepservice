package br.com.cepservice;


import br.com.cepservice.model.exception.CEPNotFoundException;
import br.com.cepservice.model.exception.InvalidCEPException;
import br.com.cepservice.model.Address;
import br.com.cepservice.model.CepInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FindAddressUsecase {

    private final AddressRepositoryAdapter addressRepositoryAdapter;

    @Autowired
    public FindAddressUsecase(AddressRepositoryAdapter addressRepositoryAdapter) {
        this.addressRepositoryAdapter = addressRepositoryAdapter;
    }

    public Address execute(CepInput cepInput) throws InvalidCEPException, CEPNotFoundException {
        if(cepInput == null || !cepInput.isValid()) {
            throw new InvalidCEPException("Cep is invalid");
        }

        Optional<Address> addressOptional = findAddressBy(cepInput.getCep());
        if (addressOptional.isPresent()) {
            return addressOptional.get();
        }

        throw new CEPNotFoundException("Could not find cep: " + cepInput.getCep());
    }

    private Optional<Address> findAddressBy(String cep) {
        Optional<Address> cepOptional = this.addressRepositoryAdapter.findAddressByCep(cep);
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
