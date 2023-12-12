package com.example.capstone2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;
    @NotNull(message = "name cannot be null")
    @Size(min = 5,message = "enter name more than 4 letters")
    @Pattern(regexp = "^[\\p{Alpha} ]*$",message = "only characters")
    @Column(columnDefinition = "varchar(20) not null CHECK ((LENGTH(name) >= 5) AND (name REGEXP '^[A-Za-z]*$'))")
    private String name;
    @NotNull(message = "address cannot be null")
    @Column(columnDefinition = "varchar(20) not null")
    private String address;
    @NotNull(message = "age cannot be null")
    @Positive(message = "enter correct age")
    @Column(columnDefinition = "int not null")
    private Integer age;
    @NotNull(message = "user name cannot be null")
    @Size(min = 4)
    @Column(columnDefinition = "varchar(20) unique not null")
    private String userName;
    @NotNull(message = "email cannot be null")
    @Email(message = "write correct email")
    @Column(columnDefinition = "varchar(20) unique not null")
    private String email;
    @NotNull(message = "password cannot be null")
    @Column(columnDefinition = "varchar(20) not null")
    private String password;
    @NotNull(message = "phone number cannot be null")
    @Positive(message = "enter correct number")
    @Column(columnDefinition = "varchar(20) not null")
    private String phoneNumber;
    @NotNull(message = "category preference cannot be null")
    @Column(columnDefinition = "varchar(20) not null")
    private String catPref;
    private Integer rentedTimes=0;
}
