package dev.gonzalez.cqrsexample.bootstrap;

import dev.gonzalez.cqrsexample.model.Customer;
import dev.gonzalez.cqrsexample.model.Order;
import dev.gonzalez.cqrsexample.repository.CustomerRepository;
import dev.gonzalez.cqrsexample.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@Component
public class ApplicationLoader implements CommandLineRunner {

    private final OrderRepository orderRepository;

    private final CustomerRepository customerRepository;


    @Override
    public void run(String... args) throws Exception {
//        Customer customer = loadCustomer();
//        loadOrder(customer);

        loader();
    }

    private void loader() {

        if (customerRepository.count().block() == 0) {

            customerRepository.save(Customer.builder()
                    .firstName("Fernando")
                    .lastName("Stoiano Gonzalez")
                    .fullName("Fernando Stoiano Gonzalez")
                    .document("31002461871")
                    .createdDate(LocalDateTime.now())
                    .lastModifiedDate(LocalDateTime.now())
                    .build()).flatMap(customer -> {
                return orderRepository.save(Order.builder()
                        .price(new BigDecimal("8.88"))
                        .customerId(customer.getId())
                        .createdDate(LocalDateTime.now())
                        .lastModifiedDate(LocalDateTime.now())
                        .build());
            }).block();

            orderRepository.save(Order.builder()
                    .price(new BigDecimal("9.88"))
                    .customerId(1)
                    .createdDate(LocalDateTime.now())
                    .lastModifiedDate(LocalDateTime.now())
                    .build()).block();

            customerRepository.save(Customer.builder()
                    .firstName("Fernando")
                    .lastName("Stoiano Gonzalez")
                    .fullName("Fernando Stoiano Gonzalez")
                    .document("31002461871")
                    .createdDate(LocalDateTime.now())
                    .lastModifiedDate(LocalDateTime.now())
                    .build()).flatMap(customer -> {
                return orderRepository.save(Order.builder()
                        .price(new BigDecimal("8.88"))
                        .customerId(customer.getId())
                        .createdDate(LocalDateTime.now())
                        .lastModifiedDate(LocalDateTime.now())
                        .build());
            }).block();

            customerRepository.save(Customer.builder()
                    .firstName("Fernando")
                    .lastName("Stoiano Gonzalez")
                    .fullName("Fernando Stoiano Gonzalez")
                    .document("31002461871")
                    .createdDate(LocalDateTime.now())
                    .lastModifiedDate(LocalDateTime.now())
                    .build()).flatMap(customer -> {
                return orderRepository.save(Order.builder()
                        .price(new BigDecimal("8.88"))
                        .customerId(customer.getId())
                        .createdDate(LocalDateTime.now())
                        .lastModifiedDate(LocalDateTime.now())
                        .build());
            }).block();
        }
    }



    private Customer loadCustomer() {

        log.info("Loading initial data. Count customerRepository is: {}", customerRepository.count().block());

        Customer savedCustomer = null;

        if (customerRepository.count().block() == 0) {

            savedCustomer = customerRepository.save(Customer.builder()
                    .firstName("Fernando")
                    .lastName("Stoiano Gonzalez")
                    .fullName("Fernando Stoiano Gonzalez")
                    .document("31002461871")
                    .createdDate(LocalDateTime.now())
                    .lastModifiedDate(LocalDateTime.now())
                    .build()).block();

            log.info("Loading initial data. Count customerRepository is: {}", customerRepository.count().block());
        }
        return savedCustomer;
    }

    private void loadOrder(Customer savedCustomer) {

        log.info("Loading initial data. Count orderRepository is: {}", orderRepository.count().block());

        if (orderRepository.count().block() == 0) {
            orderRepository.save(Order.builder()
                    .price(new BigDecimal("8.88"))
                    .customerId(savedCustomer.getId())
                    .createdDate(LocalDateTime.now())
                    .lastModifiedDate(LocalDateTime.now())
                    .build()).block();

            log.info("Loading initial data. Count orderRepository is: {}", orderRepository.count().block());
        }
    }

}
