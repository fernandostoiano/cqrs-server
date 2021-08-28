package dev.gonzalez.cqrsexample.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto implements Comparable<OrderDto> {

    private Integer orderId;

    private BigDecimal price;

    private Customer customer;

    @Override
    public int compareTo(OrderDto obj) {
        if(Objects.equals(this, obj)) {
            return 1;
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDto orderDto = (OrderDto) o;
        return Objects.equals(orderId, orderDto.orderId) && Objects.equals(price, orderDto.price) && Objects.equals(customer, orderDto.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, price, customer);
    }

}
