package br.com.cepservice;


import br.com.cepservice.model.CepInput;
import br.com.cepservice.model.CepOutput;

public class FindCep {
    public CepOutput execute(CepInput cepInput) {
        if(cepInput == null) {
            throw new IllegalArgumentException("Cep cannot be null");
        }

        CepOutput cepOutput = CepOutput.buildDefaulFailure();
        if(cepInput.isValid()) {

        }

        return cepOutput;
    }
}
