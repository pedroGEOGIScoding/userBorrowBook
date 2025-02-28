# JPA Spring Boot Many-to-Many Relationship: Implementation Options

## Introduction

In JPA (Java Persistence API) with Spring Boot, many-to-many relationships between entities can be implemented in **two main ways**:

1. **Using `@ManyToMany` with `@JoinTable`:**
   - This approach can be configured as either **unidirectional** or **bidirectional**.
   - It relies on a **join table** managed by JPA through the `@JoinTable` annotation.
   - Example:
     ```java
     @ManyToMany
     @JoinTable(
         name = "student_course",
         joinColumns = @JoinColumn(name = "student_id"),
         inverseJoinColumns = @JoinColumn(name = "course_id")
     )
     private Set<Course> courses;
     ```
   - **Use case:** Ideal for simple relationships where the join table does not require additional attributes.

2. **Using an Intermediate Entity with `@OneToMany` and `@ManyToOne`:**
   - This approach involves creating an **intermediate entity** to represent the relationship explicitly.
   - The intermediate entity acts as an **assignment table** and connects the two main entities using `@OneToMany` and `@ManyToOne`.
   - Example:
     ```java
     @Entity
     public class StudentCourse {
         @Id
         @GeneratedValue(strategy = GenerationType.IDENTITY)
         private Long id;

         @ManyToOne
         @JoinColumn(name = "student_id")
         private Student student;

         @ManyToOne
         @JoinColumn(name = "course_id")
         private Course course;

         // Additional attributes (e.g., enrollment date, grade)
         private LocalDate enrolledDate;
     }
     ```
   - **Use case:** Necessary when the relationship requires additional attributes or when more control over the join table is needed.

## Branches diagram

This diagram explores both approaches, highlighting their differences, use cases, and configurations, such as:
- **Ownership** (owning side vs. inverse side)
- **Unidirectional vs. bidirectional mappings**
- **Choice between a join table or an explicit entity table**

![userAppBorrowBook - Copy](https://github.com/user-attachments/assets/1ae8f89b-24c8-4c44-8b5a-8b9b0e72e5d5)

## Data Base

![image](https://github.com/user-attachments/assets/85b810a0-4ca7-4421-8840-48b6429140a4)

We're working with six different H2 databases to explore various JPA relationship patterns:

1. **Join Table - Bidirectional** (`userBorrowBook-jointable-bidirectinal.db`)
   - This database stores entities with a bidirectional many-to-many relationship using a join table
   - For example, Users and Books connected through a join table (not an entity)
   - Both entities would have collections of each other and references pointing back and forth
   - Join tables are created automatically by JPA with `@ManyToMany` annotations

2. **Join Table - Unidirectional** (`userBorrowBook-jointable-unidirectinal.db`)
   - Similar to the above, but the relationship only exists in one direction
   - Only one entity would have the collection of the other (e.g., User has a list of Books, but Book doesn't reference User)
   - The relationship isn't navigable in both directions

3. **Join Entity - Bidirectional** (`userBorrowBook-joinenty-birectinal.db`)
   - This is what we implemented first - using Borrow as a join entity between User and Book
   - Bidirectional relationships exist between User-Borrow and Book-Borrow
   - Each entity is aware of its related entities (User knows its Borrows, Borrows know their User and Book, Book knows its Borrows)

4. **Join Entity - Unidirectional** (`userBorrowBook-joinentiy-unidirectinal.db`)
   - This is the second implementation we did, with Borrow as the join entity
   - Only Borrow knows about Users and Books (unidirectional relationship)
   - Users and Books don't have references to Borrows

5. **One-to-One - Bidirectional** (`userBorrowBook-onetoone-birectinal.db`)
   - Our third implementation with a one-to-one relationship between User and Book
   - Bidirectional means each entity references the other
   - User has a Book property, Book has a User property
   - Only one foreign key (in the owner side, User in our case)

6. **userBorrowBook Database** (`userBorrowBook.db.mv`)
   - This is the default database with no relationship, just 3 entities no related yet.



