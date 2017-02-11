package br.com.cepservice;

import br.com.cepservice.model.route.AddressRoute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;

@Component
public class EndpointRegister {

    private final AddressRoute addressRoute;

    @Autowired
    public EndpointRegister(AddressRoute addressRoute) {
        this.addressRoute = addressRoute;
    }

    public void execute() {
        port(8080);
        registerEndpoints();
    }

    private void registerEndpoints() {
        get("/health", (req, res) -> "Hello World");
        post("/cep", "application/json", addressRoute::handle);
    }
}
