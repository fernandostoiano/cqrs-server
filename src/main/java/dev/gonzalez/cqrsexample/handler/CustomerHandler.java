package dev.gonzalez.cqrsexample.handler;

import dev.gonzalez.cqrsexample.service.CustomerService;
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
public class CustomerHandler {

    private final CustomerService customerService;

    public Mono<ServerResponse> list(ServerRequest request) {
        return customerService.list()
                .collect(Collectors.toList())
                .flatMap(customers -> {
                    return ServerResponse.ok().bodyValue(customers);
                });
    }

    public Mono<ServerResponse> findById(ServerRequest  request) {
        Integer customerId = Integer.valueOf(request.pathVariable("customerId"));

        return customerService.getById(customerId)
                .flatMap(customer -> {
                   return ServerResponse.ok().bodyValue(customer);
                }).switchIfEmpty(ServerResponse.notFound().build());
    }

}
