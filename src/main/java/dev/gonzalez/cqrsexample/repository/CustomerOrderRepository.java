package dev.gonzalez.cqrsexample.repository;

import dev.gonzalez.cqrsexample.model.CustomerOrder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CustomerOrderRepository {

    private final R2dbcEntityTemplate template;

    private final DatabaseClient client;


    public Mono<CustomerOrder> find(Integer customerId, Integer orderId) {

//        Query query = query(where("customerId").is(customerId));;
//        template.select(Order.class).matching(query).all();

        DatabaseClient.GenericExecuteSpec sql = client.sql("Select * from orders o inner join customer c on c.id = o.id where o.customer_id = :orderId");

        return null;
    }

}
