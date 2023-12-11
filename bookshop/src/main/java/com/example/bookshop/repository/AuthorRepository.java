package com.example.bookshop.repository;

import com.example.bookshop.exception.AuthorAlreadyExistsException;
import com.example.bookshop.exception.AuthorNotFoundException;
import com.example.bookshop.model.Author;
import jakarta.annotation.PostConstruct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    Optional<Author> findByFirstNameAndLastName(String firstName, String lastName);

//    public void updateWithPut(Integer id, Author newAuthor) {
//        authors.forEach(author -> {
//            if(author.getId() == id) {
//                author.setFirstName(newAuthor.getFirstName());
//                author.setLastName(newAuthor.getLastName());
//                author.setBiography(newAuthor.getBiography());
//            }
//        });
//    }
//
//    public void updateWithPatch(Integer id, Author newAuthor) {
//        authors.forEach(author -> {
//            if(author.getId() == id) {
//                if (newAuthor.getFirstName() != null) {
//                    author.setFirstName(newAuthor.getFirstName());
//                }
//                if (newAuthor.getLastName() != null) {
//                    author.setLastName(newAuthor.getLastName());
//                }
//                if (newAuthor.getBiography() != null) {
//                    author.setBiography(newAuthor.getBiography());
//                }
//            }
//        });
//    }
//
//    public Author getByName(String firstName, String lastName) {
//        List<Author> author = authors.stream().filter(elem -> elem.getFirstName().equals(firstName) && elem.getLastName().equals(lastName)).collect(Collectors.toList());
//        if (author.size() == 0) {
//            throw new AuthorNotFoundException("No author named " + firstName + " " + lastName + ".");
//        }
//        return author.get(0);
//    }
}
