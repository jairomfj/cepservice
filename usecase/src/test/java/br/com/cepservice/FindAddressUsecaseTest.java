package br.com.cepservice;

import br.com.cepservice.model.Address;
import br.com.cepservice.model.CepInput;
import br.com.cepservice.model.AddressOutput;
import org.junit.Test;

import java.util.Optional;

public class FindAddressUsecaseTest {

    @Test(expected = IllegalArgumentException.class)
    public void testNullCep() {
        FindAddressUsecase findAddress = new FindAddressUsecase(buildPersistenceAdapter(""));
        findAddress.execute(null);
    }

    @Test
    public void testInvalidCep() {
        CepInput cepInput = new CepInput("12678");
        FindAddressUsecase findAddress = new FindAddressUsecase(buildPersistenceAdapter(cepInput.getValue()));
        AddressOutput cepOutput = findAddress.execute(cepInput);
        assert "invalid".equals(cepOutput.getMessage());
    }

    @Test
    public void testValidAndPersistedCepWithSeparators() {
        CepInput cepInput = new CepInput("12.345-678");
        FindAddressUsecase findAddress = new FindAddressUsecase(buildPersistenceAdapter(cepInput.getValue()));
        AddressOutput cepOutput = findAddress.execute(cepInput);
        assert cepInput.getValue().equals(cepOutput.getAddress().getCep());
        assert "success".equals(cepOutput.getMessage());
    }

    @Test
    public void testValidAndPersistedCepWithoutSeparators() {
        CepInput cepInput = new CepInput("12345674");
        FindAddressUsecase findAddress = new FindAddressUsecase(buildPersistenceAdapter(cepInput.getValue()));
        AddressOutput cepOutput = findAddress.execute(cepInput);
        assert cepInput.getValue().equals(cepOutput.getAddress().getCep());
        assert "success".equals(cepOutput.getMessage());
    }

    @Test
    public void testValidButNotPersistedCep() {
        CepInput cepInput = new CepInput("12.345-678");
        FindAddressUsecase findAddress = new FindAddressUsecase(buildNotFoundPersistenceAdapter());
        AddressOutput cepOutput = findAddress.execute(cepInput);
        assert null == cepOutput.getAddress();
        assert "not found".equals(cepOutput.getMessage());
    }

    private PersistenceAdapter buildNotFoundPersistenceAdapter() {
        return cep1 -> Optional.empty();
    }

    private PersistenceAdapter buildPersistenceAdapter(String cep) {
        return cep1 -> Optional.of(new Address(cep, "street", "neighborhood", "city", "state"));
    }
}
