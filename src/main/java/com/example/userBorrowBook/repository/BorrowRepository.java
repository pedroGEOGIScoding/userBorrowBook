package com.example.userBorrowBook.repository;

import com.example.userBorrowBook.model.Borrow;
import com.example.userBorrowBook.model.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow, String> {
    // Custom query methods

}
