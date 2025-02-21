package com.example.userBorrowBook.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.util.UUID;

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

    // In this class since this is a unidirectional relationship
    // we do not need to add the foreign key in the Book class
    // neither the object UerApp
}
