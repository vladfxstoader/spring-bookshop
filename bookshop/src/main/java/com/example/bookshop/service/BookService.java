package com.example.bookshop.service;

import com.example.bookshop.dto.BookDto;
import com.example.bookshop.exception.EntityAlreadyExistsException;
import com.example.bookshop.exception.EntityNotFoundException;
import com.example.bookshop.mapper.BookMapper;
import com.example.bookshop.model.Book;
import com.example.bookshop.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private BookRepository bookRepository;
    private BookMapper bookMapper;

    public BookService(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    public List<BookDto> getAll() {
        List<Book> allBooks = bookRepository.findAll();
        return bookMapper.mapListToBookDto(allBooks);
    }

    public BookDto save(BookDto bookDto) {
        Optional<Book> optionalBook = bookRepository.findByTitle(bookDto.getTitle());
        if(!optionalBook.isPresent()) {
            Book dbBook = bookRepository.save(bookMapper.map(bookDto));
            return bookMapper.map(dbBook);
        }
        else {
            throw new EntityAlreadyExistsException("Book titled" + bookDto.getTitle() + " already exists in the database.");
        }
    }

    public BookDto updateQuantityPlusOne(BookDto bookDto) {
        Book book = bookMapper.map(bookDto);
        book.setQuantity(book.getQuantity() + 1);
        bookRepository.save(book);
        return bookMapper.map(book);
    }

    public BookDto updateQuantityMinusOne(BookDto bookDto) {
        Book book = bookMapper.map(bookDto);
        book.setQuantity(book.getQuantity() - 1);
        bookRepository.save(book);
        return bookMapper.map(book);
    }

    public void delete(Integer id) {
        bookRepository.deleteById(id);
    }

    public List<BookDto> findByGenre(String genre) {
        List<Book> books = bookRepository.findAllByGenre(genre);
        if(books.size() == 0) {
            throw new EntityNotFoundException("No books found in database with genre " + genre);
        }
        else {
            return bookMapper.mapListToBookDto(books);
        }
    }

    public List<BookDto> getByTitle(String title) {
        List<Book> books = bookRepository.findAllBooksByTitle(title);
        if(books.size() == 0) {
            throw new EntityNotFoundException("No books found in database with title " + title);
        }
        else {
            return bookMapper.mapListToBookDto(books);
        }
    }

    public List<BookDto> getByAuthor(String firstName, String lastName) {
        List<Book> books = bookRepository.findAllByAuthors_FirstNameAndAuthors_LastName(firstName, lastName);
        if(books.size() == 0) {
            throw new EntityNotFoundException("No books found in database written by " + firstName + " " + lastName);
        }
        else {
            return bookMapper.mapListToBookDto(books);
        }
    }

    public List<BookDto> getBooksWithQuantityBelow(Integer quantity) {
        List<Book> books = bookRepository.findAllBooksWithQuantityBelow(quantity);
        if(books.size() == 0) {
            throw new EntityNotFoundException("There are no books in the database with the quantity under " + quantity + " pieces.");
        }
        else {
            return bookMapper.mapListToBookDto(books);
        }
    }
}
