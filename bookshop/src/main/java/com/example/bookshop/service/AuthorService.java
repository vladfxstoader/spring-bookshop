package com.example.bookshop.service;

import com.example.bookshop.dto.AuthorDto;
import com.example.bookshop.exception.EntityAlreadyExistsException;
import com.example.bookshop.exception.EntityNotFoundException;
import com.example.bookshop.mapper.AuthorMapper;
import com.example.bookshop.model.Author;
import com.example.bookshop.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    private AuthorRepository authorRepository;
    private AuthorMapper authorMapper;

    public AuthorService(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorMapper = authorMapper;
        this.authorRepository = authorRepository;
    }

    public List<AuthorDto> getAll() {
        List<Author> allAuthors = authorRepository.findAll();
        return authorMapper.mapListToAuthorDto(allAuthors);
    }

    public AuthorDto save(AuthorDto authorDto) {
        Optional<Author> optionalAuthor = authorRepository.findByFirstNameAndLastName(authorDto.getFirstName(), authorDto.getLastName());
        if(!optionalAuthor.isPresent()) {
            Author dbAuthor = authorRepository.save(authorMapper.map(authorDto));
            return authorMapper.map(dbAuthor);
        }
        else {
            throw new EntityAlreadyExistsException("Author named " + authorDto.getFirstName() + " " + authorDto.getLastName() + " already exists in the database.");
        }
    }

    public void delete(Integer id) {
        authorRepository.deleteById(id);
    }

    public AuthorDto getByName(String firstName, String lastName) {
        Optional<Author> optionalAuthor = authorRepository.findByFirstNameAndLastName(firstName, lastName);
        if(!optionalAuthor.isPresent()) {
            throw new EntityNotFoundException("Author named " + firstName + " " + lastName + " does not exist in the database.");
        }
        return authorMapper.map(optionalAuthor.get());
    }

}
