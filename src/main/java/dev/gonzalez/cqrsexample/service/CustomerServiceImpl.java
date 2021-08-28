package dev.gonzalez.cqrsexample.service;

import dev.gonzalez.cqrsexample.model.Customer;
import dev.gonzalez.cqrsexample.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Flux<Customer> list() {
        return customerRepository.findAll();
    }

    @Override
    public Mono<Customer> getById(Integer id) {
        return customerRepository.findById(id);
    }

}
