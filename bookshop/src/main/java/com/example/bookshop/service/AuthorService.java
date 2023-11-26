package com.example.bookshop.service;

import com.example.bookshop.model.Author;
import com.example.bookshop.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AuthorService {
    private AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> getAll() {
        return authorRepository.getAll();
    }

    public void add(Author author) {
        authorRepository.add(author);
    }

    public void updateWithPut(Integer id, Author author) {
        authorRepository.updateWithPut(id, author);
    }

    public void updateWithPatch(Integer id, Author author) {
        authorRepository.updateWithPatch(id, author);
    }

    public void delete(String firstName, String lastName) {
        authorRepository.delete(firstName, lastName);
    }


}
