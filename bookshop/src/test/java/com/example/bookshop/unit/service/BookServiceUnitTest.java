package com.example.bookshop.unit.service;

import com.example.bookshop.dto.AuthorDto;
import com.example.bookshop.dto.BookDto;
import com.example.bookshop.exception.EntityNotFoundException;
import com.example.bookshop.mapper.BookMapper;
import com.example.bookshop.model.Book;
import com.example.bookshop.repository.BookRepository;
import com.example.bookshop.service.BookService;
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

public class BookServiceUnitTest {
    @Mock
    private BookRepository bookRepository;
    @Mock
    private BookMapper bookMapper;
    @InjectMocks
    private BookService bookService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindByGenre_success() {
        List<Book> books = getDummyBooks();
        List<BookDto> bookDtos = getDummyBookDtos();
        Mockito.when(bookRepository.findAllByGenre(Mockito.any()))
                .thenReturn(books);
        Mockito.when(bookMapper.mapListToBookDto(books))
                .thenReturn(bookDtos);

        List<BookDto> returnedBooks = bookService.findByGenre("Fictiune");

        Assertions.assertNotNull(returnedBooks, "Returned list should not be null");
        Assertions.assertEquals(bookDtos.size(), returnedBooks.size(), "Returned list size should match");

        for (int i = 0; i < returnedBooks.size(); i++) {
            BookDto expectedDto = bookDtos.get(i);
            BookDto returnedDto = returnedBooks.get(i);

            Assertions.assertEquals(expectedDto.getId(), returnedDto.getId(), "Book ID should match");
            Assertions.assertEquals(expectedDto.getTitle(), returnedDto.getTitle(), "Book title should match");
            Assertions.assertEquals(expectedDto.getGenre(), returnedDto.getGenre(), "Book genre should match");
            Assertions.assertEquals(expectedDto.getPrice(), returnedDto.getPrice(), "Book price should match");
            Assertions.assertEquals(expectedDto.getQuantity(), returnedDto.getQuantity(), "Book quantity should match");
        }
    }

    @Test
    public void testGetBooksWithQuantityBelow_exception() {
        List<Book> books = new ArrayList<>();
        Mockito.when(bookRepository.findAllBooksWithQuantityBelow(Mockito.any()))
                .thenReturn(books);
        Assertions.assertThrows(EntityNotFoundException.class, () -> bookService.getBooksWithQuantityBelow(5));
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
