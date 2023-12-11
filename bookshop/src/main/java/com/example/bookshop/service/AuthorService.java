package com.example.bookshop.service;

import com.example.bookshop.exception.AuthorAlreadyExistsException;
import com.example.bookshop.exception.AuthorNotFoundException;
import com.example.bookshop.model.Author;
import com.example.bookshop.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    private AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    public void save(Author author) {
        Optional<Author> optionalAuthor = authorRepository.findByFirstNameAndLastName(author.getFirstName(), author.getLastName());
        if(!optionalAuthor.isPresent()) {
            authorRepository.save(author);
        }
        else {
            throw new AuthorAlreadyExistsException("Author named " + author.getFirstName() + " " + author.getLastName() + " already exists in the database.");
        }
    }

//    public void updateWithPut(Integer id, Author author) {
//        authorRepository.updateWithPut(id, author);
//    }
//
//    public void updateWithPatch(Integer id, Author author) {
//        authorRepository.updateWithPatch(id, author);
//    }

    public void delete(Integer id) {
        authorRepository.deleteById(id);
    }

    public Author getByName(String firstName, String lastName) {
        Optional<Author> optionalAuthor = authorRepository.findByFirstNameAndLastName(firstName, lastName);
        if(!optionalAuthor.isPresent()) {
            throw new AuthorNotFoundException("Author named " + firstName + " " + lastName + " does not exist in the database.");
        }
        return optionalAuthor.get();
    }

}
