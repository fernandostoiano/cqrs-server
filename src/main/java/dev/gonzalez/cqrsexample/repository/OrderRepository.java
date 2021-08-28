package dev.gonzalez.cqrsexample.repository;

import dev.gonzalez.cqrsexample.model.Order;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface OrderRepository extends ReactiveCrudRepository<Order, Integer> {

    Flux<Order> findByCustomerId(Integer customerId);


    //@Query("SELECT firstname, lastname FROM person WHERE lastname = $1")
    //@Query("SELECT * FROM customer c INNER JOIN orders o ON o.customer_id = c.id WHERE c.id = :customerId")
    //Mono<CustomerOrder> findOrder(Integer customerId);
}
