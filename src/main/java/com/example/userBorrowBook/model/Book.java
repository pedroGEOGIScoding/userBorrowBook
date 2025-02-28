package com.example.userBorrowBook.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    private String title;
    private String author;
    private String isbn;
    private int pagesQty;
    private boolean available;
    private LocalDate publicationDate;

    // Bidirectional One-to-One relationship with UserApp
    // Using JsonIgnore to prevent recursion
    @OneToOne(mappedBy = "book")
    @JsonIgnore
    private UserApp user;
}