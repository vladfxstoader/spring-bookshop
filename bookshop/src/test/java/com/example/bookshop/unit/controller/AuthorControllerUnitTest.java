package com.example.bookshop.unit.controller;

import com.example.bookshop.controller.AuthorController;
import com.example.bookshop.dto.AuthorDto;
import com.example.bookshop.model.Author;
import com.example.bookshop.service.AuthorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AuthorControllerUnitTest {
    private MockMvc mockMvc;
    @Mock
    private AuthorService authorService;
    @InjectMocks
    private AuthorController authorController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(authorController).build();
    }

    @Test
    public void testGetAll_success() throws Exception {
        List<AuthorDto> authors = getDummyAuthorDtos();
        Mockito.when(authorService.getAll())
                .thenReturn(authors);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/author"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(new ObjectMapper().writeValueAsString(authors)));
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
