package com.example.capstone2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;
    @NotNull(message = "publication id cannot be null!")
    @Positive(message = "enter correct id")
//    @Column(columnDefinition = "int not null")
    private Integer pubId;
    @NotNull(message = "title cannot be null")
//    @Column(columnDefinition = "varchar(20) not null")
    private String title;
    @NotNull(message = "price cannot be null")
    @Positive(message = "enter correct price")
//    @Column(columnDefinition = "double not null")
    private double priceForOneDay;
    @NotNull(message = "quantity cannot be null")
    @PositiveOrZero(message = "enter correct quantity")
//    @Column(columnDefinition = "int not null")
    private Integer quantity;
    @NotNull(message = "is available cannot be null")
//    @Column(columnDefinition = "Boolean not null")
    private Boolean isAvailable;
    @NotNull(message = "category should be not null")
    @Pattern(regexp = "^(Health|Education|Biographies|Fiction|Action|Classics|Historical|Horror|Mystery|Romance|Science|Memoirs|Narrative|Reference)$")
//    @Column(columnDefinition = "varchar(20) not null check(category='Health' or category='Education' or category='Biographies' or category='Fiction' or category='Action' or category='Classics' or category='Historical' or category='Horror' or category='Mystery' or category='Romance' or category='Science' or category='Memoirs' or category='Narrative' or category='Reference')")
    private String category;
    @NotNull(message = "life stage should be not null")
    @Pattern(regexp = "^(Childhood|Adolescence|Adulthood)$")
//    @Column(name = "life_stage", columnDefinition = "varchar(20) not null check(life_stage='Childhood' or life_stage='Adolescence' or life_stage='Adulthood')")
    private String lifeStage;
    @NotNull(message = "language cannot be null")
//    @Column(columnDefinition = "varchar(20) not null")
    private String language;
    @NotNull(message = "publish date cannot be null")
//    @Column(columnDefinition = "date not null ")
    private LocalDate publishDate;
    @PositiveOrZero(message = "rating must be from 0 to 5")
    @Max(value = 5,message = "max rating is 5!")
//    @Column(columnDefinition = "double not null")
    private double rating;
//    @Column(columnDefinition = "int")
    private Integer rentedTimes=0;
//    @Column(columnDefinition = "Boolean")
    private Boolean onDiscount=false;
}
