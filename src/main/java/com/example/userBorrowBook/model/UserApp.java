package com.example.userBorrowBook.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable ( name = "USERAPP_BOOK_JOINTABLE" ,
            joinColumns = @JoinColumn ( name = "USERAPP_ID_FK" ) ,
            inverseJoinColumns = @JoinColumn ( name = "BOOK_ID_FK" ) )
    private List<Book> books;
}
