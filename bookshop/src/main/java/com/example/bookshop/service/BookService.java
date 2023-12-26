package com.example.bookshop.service;

import com.example.bookshop.dto.BookDto;
import com.example.bookshop.exception.EntityAlreadyExistsException;
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

    public void delete(Integer id) {
        bookRepository.deleteById(id);
    }
}
