package dev.gonzalez.cqrsexample.handler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class OrderRouterConfig {

    public static final String ORDER_URL = "/api/v1/order";
    public static final String ORDER_URL_ID = "/api/v1/order/{orderId}";
    public static final String ORDER_URL_CUSTOMER_ID = "/api/v1/order/customer/{customerId}";

    public static final String CUSTOMER_ORDERS_URL = "/api/v1/customer/orders/{customerId}";

    @Bean
    public RouterFunction<ServerResponse> orderRouter(OrderHandler handler) {
        return route()
                .GET(ORDER_URL_CUSTOMER_ID, accept(APPLICATION_JSON), handler::listByCustomer)
                .GET(ORDER_URL_ID, accept(APPLICATION_JSON), handler::findById)
                .GET(CUSTOMER_ORDERS_URL, accept(APPLICATION_JSON), handler::findOrdersCustomer)
                .build();
    }

}
