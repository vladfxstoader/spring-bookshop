package com.example.bookshop.repository;

import com.example.bookshop.exception.AuthorAlreadyExistsException;
import com.example.bookshop.exception.AuthorNotFoundException;
import com.example.bookshop.model.Author;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        if (authors.stream().filter(elem -> elem.getFirstName().equals(author.getFirstName())
            && elem.getLastName().equals(author.getLastName())).toList().size() > 0) {
            throw new AuthorAlreadyExistsException("Author named " + author.getFirstName()
                + " " + author.getLastName() + " already exists in the database.");
        }
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

    public Author getByName(String firstName, String lastName) {
        List<Author> author = authors.stream().filter(elem -> elem.getFirstName().equals(firstName) && elem.getLastName().equals(lastName)).collect(Collectors.toList());
        if (author.size() == 0) {
            throw new AuthorNotFoundException("No author named " + firstName + " " + lastName + ".");
        }
        return author.get(0);
    }
}
