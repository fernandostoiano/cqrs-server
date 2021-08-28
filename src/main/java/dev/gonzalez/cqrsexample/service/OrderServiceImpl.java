package dev.gonzalez.cqrsexample.service;

import dev.gonzalez.cqrsexample.model.Order;
import dev.gonzalez.cqrsexample.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public Flux<Order> listByCustomer(Integer customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    @Override
    public Mono<Order> findById(Integer orderId) {
        return orderRepository.findById(orderId);
    }

}
