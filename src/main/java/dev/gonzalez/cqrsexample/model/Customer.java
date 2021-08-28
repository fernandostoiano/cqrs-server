package dev.gonzalez.cqrsexample.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    private Integer id;

    private String firstName;

    private String lastName;

    private String fullName;

    private String document;

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;

}
