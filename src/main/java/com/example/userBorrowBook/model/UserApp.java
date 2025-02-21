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
public class UserApp {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    private String userAppName;
    private String email;
    private String password;
    private int age;
    private String address;
    private boolean isArchived;
    private LocalDate dob;

    // let s relation userApp and Book many to many
    // with join table userApp_Book, unidirectional
    // with the owner of the relation is UserApp

    @ManyToMany( fetch = FetchType.EAGER)
    @JoinTable( name = "USERAPP_BOOK_JOINTABLE",
            joinColumns = @JoinColumn(name = "USERAPP_ID_FK"),
            inverseJoinColumns = @JoinColumn(name = "BOOK_ID_FK"))
    private List<Book> books;

    public List<Book> addBook(Book book) {
        if (this.books == null) { return null;}
        this.books.add(book);
        return this.books;
    }
}
