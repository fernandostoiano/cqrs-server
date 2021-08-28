package dev.gonzalez.cqrsexample.service;

import dev.gonzalez.cqrsexample.model.Customer;
import dev.gonzalez.cqrsexample.model.CustomerOrder;
import dev.gonzalez.cqrsexample.model.Order;
import dev.gonzalez.cqrsexample.model.OrderDto;
import dev.gonzalez.cqrsexample.repository.mapper.CustomerOrderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerOrderServiceImpl implements CustomerOrderService {

    private final DatabaseClient databaseClient;

    private final CustomerOrderMapper customerOrderMapper;

    private final R2dbcEntityTemplate template;


    @Override
    public Mono<CustomerOrder> find(Integer customerId, Integer orderId) {

//        Criteria criteria1 = Criteria.where("customerId").is(customerId);
//
//        Query query = Query.query(criteria1);
//
//
//        Flux<Order> all = template.select(Order.class).matching(query).all();


        Flux<OrderDto> ordersDTO = databaseClient
                .sql(createOrdersQuery(customerId, orderId))
                .bind("customerId", customerId)
                .map(customerOrderMapper::apply).all();

        return ordersDTO.collectSortedList().flatMap(orders -> {

            List<Order> orderList = new ArrayList<>();

            Customer customer = orders.stream().findFirst().get().getCustomer();

            orders.forEach(orderDto -> {
                orderList.add(Order.builder()
                        .id(orderDto.getOrderId())
                        .customerId(orderDto.getCustomer().getId())
                        .price(orderDto.getPrice())
                        .build());
            });

            return Mono.just(CustomerOrder.builder()
                    .customer(customer)
                    .orders(orderList)
                    .build());
        });
    }

    private String createOrdersQuery(Integer customerId, Integer orderId) {

        String query = "SELECT * FROM orders o INNER JOIN customer c ON c.id = o.customer_id WHERE c.id = :customerId";

        return query;
    }

}
