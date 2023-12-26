package com.example.bookshop.controller;

import com.example.bookshop.dto.BookDto;
import com.example.bookshop.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("book")
@RestController
public class BookController {
    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookDto> getAll() {
        return bookService.getAll();
    }

    @PostMapping
    public BookDto save(@RequestBody BookDto bookDto) {
        return bookService.save(bookDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        bookService.delete(id);
    }
}
