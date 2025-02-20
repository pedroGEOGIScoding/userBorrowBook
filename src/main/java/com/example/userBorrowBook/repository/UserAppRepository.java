package com.example.userBorrowBook.repository;

import com.example.userBorrowBook.model.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAppRepository extends JpaRepository<UserApp, String> {
    // You can add custom query methods here if needed
    // For example:
    // Optional<UserApp> findByUsername(String username);
    // List<UserApp> findByEmail(String email);
}
