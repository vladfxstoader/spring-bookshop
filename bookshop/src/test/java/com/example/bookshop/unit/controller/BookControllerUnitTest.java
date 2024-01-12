package com.example.bookshop.unit.controller;

import com.example.bookshop.controller.BookController;
import com.example.bookshop.dto.BookDto;
import com.example.bookshop.exception.EntityNotFoundException;
import com.example.bookshop.model.Book;
import com.example.bookshop.service.BookService;
import com.fasterxml.jackson.core.JsonProcessingException;
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

public class BookControllerUnitTest {
    private MockMvc mockMvc;
    @Mock
    private BookService bookService;
    @InjectMocks
    private BookController bookController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }

    @Test
    public void testGetByGenre_success() throws Exception {
        String genre = "Fictiune";
        List<BookDto> books = getDummyBookDtos();
        Mockito.when(bookService.findByGenre(Mockito.any()))
                .thenReturn(books);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/book/genre/{genre}", genre))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(new ObjectMapper().writeValueAsString(books)));
    }

    @Test
    public void testGetByGenre_exception() throws Exception {
        String genre = "NonexistentGenre";

        Mockito.when(bookService.findByGenre(genre))
                .thenThrow(new EntityNotFoundException("No books found in database with genre " + genre));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/genre/{genre}", genre))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    private Book getDummyBookOne() {
        Book book = new Book();
        book.setId(5);
        book.setTitle("Ion");
        book.setGenre("Fictiune");
        book.setPrice(19.5F);
        book.setQuantity(15);
        return book;
    }

    private Book getDummyBookTwo() {
        Book book = new Book();
        book.setId(5);
        book.setTitle("Moara cu noroc");
        book.setGenre("Fictiune");
        book.setPrice(15F);
        book.setQuantity(10);
        return book;
    }

    private BookDto getDummyBookDtoOne() {
        BookDto bookDto = new BookDto();
        bookDto.setId(5);
        bookDto.setTitle("Ion");
        bookDto.setGenre("Fictiune");
        bookDto.setPrice(19.5F);
        bookDto.setQuantity(15);
        return bookDto;
    }

    private BookDto getDummyBookDtoTwo() {
        BookDto bookDto = new BookDto();
        bookDto.setId(5);
        bookDto.setTitle("Moara cu noroc");
        bookDto.setGenre("Fictiune");
        bookDto.setPrice(15F);
        bookDto.setQuantity(10);
        return bookDto;
    }

    private List<Book> getDummyBooks() {
        return new ArrayList<>(Arrays.asList(getDummyBookOne(), getDummyBookTwo()));
    }

    private List<BookDto> getDummyBookDtos() {
        return new ArrayList<>(Arrays.asList(getDummyBookDtoOne(), getDummyBookDtoTwo()));
    }
}
