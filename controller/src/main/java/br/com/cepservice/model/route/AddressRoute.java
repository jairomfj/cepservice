package br.com.cepservice.model.route;

import br.com.cepservice.FindAddressUsecase;
import br.com.cepservice.model.Address;
import br.com.cepservice.model.AddressOutput;
import br.com.cepservice.model.CepInput;
import br.com.cepservice.model.exception.CEPNotFoundException;
import br.com.cepservice.model.exception.InvalidCEPException;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spark.Request;
import spark.Response;
import spark.Route;

@Component
public class AddressRoute implements Route {

    private static Logger LOGGER = LoggerFactory.getLogger(AddressRoute.class);

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
            LOGGER.error("Invalid passed data", e);
            addressOutput = AddressOutput.buildDefaulInvalid();
            response.status(400);
        } catch (Exception e) {
            LOGGER.error("An error has occurred", e);
            throw e;
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
