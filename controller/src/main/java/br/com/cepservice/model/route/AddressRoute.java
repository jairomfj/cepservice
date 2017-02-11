package br.com.cepservice.model.route;

import br.com.cepservice.FindAddressUsecase;
import br.com.cepservice.model.Address;
import br.com.cepservice.model.AddressOutput;
import br.com.cepservice.model.CepInput;
import br.com.cepservice.model.exception.CEPNotFoundException;
import br.com.cepservice.model.exception.InvalidCEPException;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spark.Request;
import spark.Response;
import spark.Route;

@Component
public class AddressRoute implements Route {

    private final FindAddressUsecase findAddressUsecase;

    @Autowired
    public AddressRoute(FindAddressUsecase findAddressUsecase) {
        this.findAddressUsecase = findAddressUsecase;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        AddressOutput addressOutput;
        try {
            CepInput cepInput = getParameter(request);
            Address address = findAddressUsecase.execute(cepInput);
            addressOutput = AddressOutput.buildDefaulSuccess(address);
        } catch (InvalidCEPException | CEPNotFoundException e) {
            addressOutput = AddressOutput.buildDefaulInvalid();
            response.status(400);
        }

        return new Gson().toJson(addressOutput);
    }

    public CepInput getParameter(Request request) {
        String body = request.body();
        CepInput cepInput = new Gson().fromJson(body, CepInput.class);
        cepInput.normalizeValue();
        return cepInput;
    }
}
