package dev.gonzalez.cqrsexample.service;

import dev.gonzalez.cqrsexample.model.Customer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService {

    Flux<Customer> list();

    Mono<Customer> getById(Integer id);
}
