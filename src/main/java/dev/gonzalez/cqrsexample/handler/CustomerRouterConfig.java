package dev.gonzalez.cqrsexample.handler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class CustomerRouterConfig {

    public static final String CUSTOMER_URL = "/api/v1/customer";
    public static final String CUSTOMER_URL_ID = "/api/v1/customer/{customerId}";

    @Bean
    public RouterFunction<ServerResponse> customerRouter(CustomerHandler handler) {
        return route()
                .GET(CUSTOMER_URL, accept(APPLICATION_JSON), handler::list)
                .GET(CUSTOMER_URL_ID, accept(APPLICATION_JSON), handler::findById)
                .build();
    }

}
