package com.example.capstone2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;
    @NotNull(message = "customer id cannot be null")
    @Column(columnDefinition = "int not null")
    private Integer customerId;
    @NotNull(message = "book id cannot be null")
    @Column(columnDefinition = "int not null")
    private Integer bookId;
    @Positive(message = "number of days cannot be 0")
    @NotNull(message = "number of days cannot be null")
    @Column(columnDefinition = "int not null")
    private Integer numDays;
    @Column(columnDefinition = "double")
    private double totalPrice=0;
    @Column(columnDefinition = "varchar(20) check(status='not returned' or status='returned')")
    private String status;
}
