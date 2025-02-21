package com.example.userBorrowBook.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.List;
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

    @ManyToMany (mappedBy = "books", fetch = FetchType.EAGER)
    private List<UserApp> userApps;
}
