package com.example.capstone2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pubId;
    @NotNull(message = "name cannot be null")
    @Size(min = 5,message = "enter name more than 4 letters")
    @Pattern(regexp = "^[\\p{Alpha} ]*$",message = "only characters")
    @Column(columnDefinition = "varchar(20) not null CHECK ((LENGTH(name) >= 5) AND (name REGEXP '^[A-Za-z]*$'))")
    private String name;
    @NotNull(message = "address cannot be null")
    @Column(columnDefinition = "varchar(20) not null")
    private String address;
}
