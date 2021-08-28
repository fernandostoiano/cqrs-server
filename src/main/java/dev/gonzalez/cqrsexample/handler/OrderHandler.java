package dev.gonzalez.cqrsexample.handler;

import dev.gonzalez.cqrsexample.service.CustomerOrderService;
import dev.gonzalez.cqrsexample.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderHandler {

    private final OrderService orderService;

    private final CustomerOrderService customerOrderService;

    public Mono<ServerResponse> listByCustomer(ServerRequest request) {
        Integer customerId = Integer.valueOf(request.pathVariable("customerId"));

        return orderService.listByCustomer(customerId)
                .collect(Collectors.toList())
                .flatMap(orders -> {
                    return ServerResponse.ok().bodyValue(orders);
                });
    }

    public Mono<ServerResponse> findById(ServerRequest request) {
        Integer orderId = Integer.valueOf(request.pathVariable("orderId"));

        return orderService.findById(orderId)
                .flatMap(order -> {
                    return ServerResponse.ok().bodyValue(order);
                }).switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> findOrdersCustomer(ServerRequest request) {
        Integer customerId = Integer.valueOf(request.pathVariable("customerId"));

        return customerOrderService.find(customerId, null)
                .flatMap(orders -> {
                    return ServerResponse.ok().bodyValue(orders);
                });
    }


}
