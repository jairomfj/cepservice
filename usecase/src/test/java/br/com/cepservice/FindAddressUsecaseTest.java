package br.com.cepservice;

import br.com.cepservice.model.exception.CEPNotFoundException;
import br.com.cepservice.model.exception.InvalidCEPException;
import br.com.cepservice.model.Address;
import br.com.cepservice.model.CepInput;
import org.junit.Test;

import java.util.Optional;

public class FindAddressUsecaseTest {

    @Test(expected = InvalidCEPException.class)
    public void testNullCep() throws InvalidCEPException, CEPNotFoundException {
        FindAddressUsecase findAddress = new FindAddressUsecase(buildPersistenceAdapter(""));
        findAddress.execute(null);
    }

    @Test(expected = InvalidCEPException.class)
    public void testInvalidCep() throws InvalidCEPException, CEPNotFoundException {
        CepInput cepInput = new CepInput("12678");
        FindAddressUsecase findAddress = new FindAddressUsecase(buildPersistenceAdapter(cepInput.getCep()));
        findAddress.execute(cepInput);
    }

    @Test
    public void testValidAndPersistedCepWithSeparators() throws InvalidCEPException, CEPNotFoundException {
        CepInput cepInput = new CepInput("12.345-678");
        FindAddressUsecase findAddress = new FindAddressUsecase(buildPersistenceAdapter(cepInput.getCep()));
        Address account = findAddress.execute(cepInput);
        assert cepInput.getCep().equals(account.getCep());
    }

    @Test
    public void testValidAndPersistedCepWithoutSeparators() throws InvalidCEPException, CEPNotFoundException {
        CepInput cepInput = new CepInput("12345674");
        FindAddressUsecase findAddress = new FindAddressUsecase(buildPersistenceAdapter(cepInput.getCep()));
        Address account = findAddress.execute(cepInput);
        assert cepInput.getCep().equals(account.getCep());
    }

    @Test(expected = CEPNotFoundException.class)
    public void testValidButNotPersistedCep() throws InvalidCEPException, CEPNotFoundException {
        CepInput cepInput = new CepInput("12.345-678");
        FindAddressUsecase findAddress = new FindAddressUsecase(buildNotFoundPersistenceAdapter());
        findAddress.execute(cepInput);
    }

    private AddressRepositoryAdapter buildNotFoundPersistenceAdapter() {
        return cep1 -> Optional.empty();
    }

    private AddressRepositoryAdapter buildPersistenceAdapter(String cep) {
        return cep1 -> Optional.of(new Address(cep, "street", "neighborhood", "city", "state"));
    }
}
