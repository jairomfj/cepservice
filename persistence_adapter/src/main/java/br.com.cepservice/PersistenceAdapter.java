package br.com.cepservice;

import br.com.cepservice.model.Cep;

public interface PersistenceAdapter {
    Cep findCep(String cep);
}
