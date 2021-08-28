package dev.gonzalez.cqrsexample.service;

import dev.gonzalez.cqrsexample.model.Order;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrderService {

    Flux<Order> listByCustomer(Integer customerId);

    Mono<Order> findById(Integer orderId);
}
