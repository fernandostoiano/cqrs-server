package dev.gonzalez.cqrsexample.service;

import dev.gonzalez.cqrsexample.model.CustomerOrder;
import reactor.core.publisher.Mono;

public interface CustomerOrderService {

    Mono<CustomerOrder> find(Integer customerId, Integer orderId);
}
