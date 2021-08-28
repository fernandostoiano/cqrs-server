package dev.gonzalez.cqrsexample.handler;

import dev.gonzalez.cqrsexample.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.netty.http.client.HttpClient;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static dev.gonzalez.cqrsexample.handler.CustomerRouterConfig.CUSTOMER_URL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class CustomerHandlerTest {

    public static final String BASE_URL = "http://localhost:8080";

    private WebClient webClient;

    @BeforeEach
    void setUp() {
        webClient = WebClient.builder()
                .baseUrl(BASE_URL)
                .clientConnector(new ReactorClientHttpConnector(HttpClient.create().wiretap(true)))
                .build();
    }

    @Test
    void testCustomerList() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        Flux<Customer> customerFlux = webClient.get().uri(CUSTOMER_URL)
                .accept(APPLICATION_JSON)
                .retrieve().bodyToFlux(Customer.class);

        customerFlux.publishOn(Schedulers.parallel()).subscribe(customer -> {
            assertThat(customer).isNotNull();
            countDownLatch.countDown();
        });

        countDownLatch.await(2000, TimeUnit.MILLISECONDS);
        assertThat(countDownLatch.getCount()).isEqualTo(0);
    }

    @Test
    void testFindCustomerById() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        Mono<Customer> customerMono = webClient.get().uri(CUSTOMER_URL + "/" + 1)
                .accept(APPLICATION_JSON)
                .retrieve().bodyToMono(Customer.class);

        customerMono.subscribe(customer -> {
            assertThat(customer).isNotNull();
            assertThat(customer.getFullName()).isNotNull();

           countDownLatch.countDown();
        });

        countDownLatch.await(2000, TimeUnit.MILLISECONDS);
        assertThat(countDownLatch.getCount()).isEqualTo(0);
    }

    @Test
    void testFindCustomerByIdNotFound() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        Mono<Customer> customerMono = webClient.get().uri(CUSTOMER_URL + "/" + 10000)
                .accept(APPLICATION_JSON)
                .retrieve().bodyToMono(Customer.class);

        customerMono.subscribe(customer -> {

        }, throwable -> {
            countDownLatch.countDown();
        });

        countDownLatch.await(2000, TimeUnit.MILLISECONDS);
        assertThat(countDownLatch.getCount()).isEqualTo(0);
    }

}