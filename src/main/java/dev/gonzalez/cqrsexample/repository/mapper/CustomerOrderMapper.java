package dev.gonzalez.cqrsexample.repository.mapper;

import dev.gonzalez.cqrsexample.model.Customer;
import dev.gonzalez.cqrsexample.model.OrderDto;
import io.r2dbc.spi.Row;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.function.Function;

@Component
public class CustomerOrderMapper implements Function<Row, OrderDto> {

    @Override
    public OrderDto apply(Row row) {

        Customer customer = Customer.builder()
                .id(row.get("customer_id", Integer.class))
                .firstName(row.get("first_name", String.class))
                .lastName(row.get("last_name", String.class))
                .fullName(row.get("full_name", String.class))
                .document(row.get("document", String.class))
                .build();

        return OrderDto.builder()
                .orderId(row.get("id", Integer.class))
                .price(row.get("price", BigDecimal.class))
                .customer(customer)
                .build();
    }

}
