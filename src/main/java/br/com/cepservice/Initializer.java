package br.com.cepservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "br.com.cepservice")
public class Initializer {

    private static Logger LOGGER = LoggerFactory.getLogger(Initializer.class);

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Initializer.class);
        EndpointRegister endpointRegister = context.getBean(EndpointRegister.class);
        endpointRegister.execute();
        LOGGER.info("Application running");
    }
}
