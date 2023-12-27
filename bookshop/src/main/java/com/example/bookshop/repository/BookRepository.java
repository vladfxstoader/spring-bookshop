package com.example.bookshop.repository;

import com.example.bookshop.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    Optional<Book> findByTitle(String title);
    List<Book> findAllByGenre(String genre);
    List<Book> findAllByAuthors_FirstNameAndAuthors_LastName(String firstName, String LastName);
    @Query(" from Book where upper(title) like %:title%")
    List<Book> findAllBooksByTitle(String title);
}
