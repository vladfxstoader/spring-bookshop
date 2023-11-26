package com.example.bookshop.repository;

import com.example.bookshop.model.Author;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AuthorRepository {
    private List<Author> authors = new ArrayList<>();

    @PostConstruct
    private void setUp() {
        authors.add(new Author(1, "Jules", "Verne", "best author"));
        authors.add(new Author(2, "J.K.", "Rowling", "a scris harry potter"));
    }

    public List<Author> getAll() {
        return authors;
    }

    public void add(Author author) {
        Integer newId = authors.size() + 1;
        author.setId(newId);
        authors.add(author);
    }

    public void updateWithPut(Integer id, Author newAuthor) {
        authors.forEach(author -> {
            if(author.getId() == id) {
                author.setFirstName(newAuthor.getFirstName());
                author.setLastName(newAuthor.getLastName());
                author.setBiography(newAuthor.getBiography());
            }
        });
    }

    public void updateWithPatch(Integer id, Author newAuthor) {
        authors.forEach(author -> {
            if(author.getId() == id) {
                if (newAuthor.getFirstName() != null) {
                    author.setFirstName(newAuthor.getFirstName());
                }
                if (newAuthor.getLastName() != null) {
                    author.setLastName(newAuthor.getLastName());
                }
                if (newAuthor.getBiography() != null) {
                    author.setBiography(newAuthor.getBiography());
                }
            }
        });
    }

    public void delete(String firstName, String lastName) {
        List<Author> newAuthorList = new ArrayList<>();
        authors.forEach(author -> {
            if(!author.getFirstName().equals(firstName) && !author.getLastName().equals(lastName)) {
                newAuthorList.add(author);
            }
        });
        authors = newAuthorList;
    }


}
