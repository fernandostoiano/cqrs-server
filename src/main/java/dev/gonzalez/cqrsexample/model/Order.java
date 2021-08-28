package dev.gonzalez.cqrsexample.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("orders")
public class Order {

    @Id
    private Integer id;

    private BigDecimal price;

    private Integer customerId;

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;

    @Transient
    private Customer customer;
}
