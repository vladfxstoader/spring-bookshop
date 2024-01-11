package com.example.bookshop.unit.service;

import com.example.bookshop.dto.AuthorDto;
import com.example.bookshop.exception.EntityNotFoundException;
import com.example.bookshop.mapper.AuthorMapper;
import com.example.bookshop.model.Author;
import com.example.bookshop.repository.AuthorRepository;
import com.example.bookshop.service.AuthorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class AuthorServiceUnitTest {
    @Mock
    private AuthorRepository authorRepository;
    @Mock
    private AuthorMapper authorMapper;
    @InjectMocks
    private AuthorService authorService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    public void testGetAll_success() {
        List<AuthorDto> authorDtos = getDummyAuthorDtos();
        List<Author> authors = getDummyAuthors();
        Mockito.when(authorRepository.findAll())
                .thenReturn(authors);
        Mockito.when(authorMapper.mapListToAuthorDto(authors))
                .thenReturn(authorDtos);

        List<AuthorDto> returnedAuthors = authorService.getAll();

        Assertions.assertNotNull(returnedAuthors, "Returned list should not be null");
        Assertions.assertEquals(authorDtos.size(), returnedAuthors.size(), "Returned list size should match");

        for (int i = 0; i < returnedAuthors.size(); i++) {
            AuthorDto expectedDto = authorDtos.get(i);
            AuthorDto returnedDto = returnedAuthors.get(i);

            Assertions.assertEquals(expectedDto.getId(), returnedDto.getId(), "Author ID should match");
            Assertions.assertEquals(expectedDto.getFirstName(), returnedDto.getFirstName(), "Author first name should match");
            Assertions.assertEquals(expectedDto.getLastName(), returnedDto.getLastName(), "Author last name should match");
            Assertions.assertEquals(expectedDto.getBiography(), returnedDto.getBiography(), "Author biography should match");
        }
    }

    @Test
    public void testGetByName_success() {
        Author author = getDummyAuthorOne();
        AuthorDto authorDto = getDummyAuthorDtoOne();
        Mockito.when(authorRepository.findByFirstNameAndLastName(Mockito.any(), Mockito.any()))
                .thenReturn(Optional.of(author));

        Mockito.when(authorMapper.map(author))
                .thenReturn(authorDto);

        AuthorDto returnedAuthor = authorService.getByName("Jules", "Verne");

        Assertions.assertEquals(authorDto.getId(), returnedAuthor.getId(), "Author ID should match");
        Assertions.assertEquals(authorDto.getFirstName(), returnedAuthor.getFirstName(), "Author first name should match");
        Assertions.assertEquals(authorDto.getLastName(), returnedAuthor.getLastName(), "Author last name should match");
        Assertions.assertEquals(authorDto.getBiography(), returnedAuthor.getBiography(), "Author biography should match");
    }

    @Test
    public void testGetByName_exception() {
        Mockito.when(authorRepository.findByFirstNameAndLastName(Mockito.any(), Mockito.any()))
                .thenReturn(Optional.empty());
        Assertions.assertThrows(EntityNotFoundException.class, () -> authorService.getByName("Jules", "Verne"));
    }
    
    private Author getDummyAuthorOne() {
        Author author = new Author();
        author.setId(10);
        author.setFirstName("Jules");
        author.setLastName("Verne");
        author.setBiography("author of my childhood");
        return author;
    }

    private Author getDummyAuthorTwo() {
        Author author = new Author();
        author.setId(10);
        author.setFirstName("Mihai");
        author.setLastName("Eminescu");
        author.setBiography("biggest Romanian poet");
        return author;
    }
    
    private AuthorDto getDummyAuthorDtoOne() {
        AuthorDto authorDto = new AuthorDto();
        authorDto.setId(10);
        authorDto.setFirstName("Jules");
        authorDto.setLastName("Verne");
        authorDto.setBiography("author of my childhood");
        return authorDto;
    }

    private AuthorDto getDummyAuthorDtoTwo() {
        AuthorDto authorDto = new AuthorDto();
        authorDto.setId(10);
        authorDto.setFirstName("Mihai");
        authorDto.setLastName("Eminescu");
        authorDto.setBiography("biggest Romanian poet");
        return authorDto;
    }

    private List<AuthorDto> getDummyAuthorDtos() {
        return new ArrayList<>(Arrays.asList(getDummyAuthorDtoOne(), getDummyAuthorDtoTwo()));
    }

    private List<Author> getDummyAuthors() {
        return new ArrayList<>(Arrays.asList(getDummyAuthorOne(), getDummyAuthorTwo()));
    }


}
