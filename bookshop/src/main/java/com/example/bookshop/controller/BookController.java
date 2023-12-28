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

    @GetMapping("/genre/{genre}")
    public List<BookDto> getByGenre(@PathVariable String genre) {
        return bookService.findByGenre(genre);
    }

    @GetMapping("/title")
    public List<BookDto> getAllBooksByTitle(@RequestBody String title) {
        return bookService.getByTitle(title);
    }

    @GetMapping("/author")
    public List<BookDto> getAllBooksByAuthor(@RequestBody String name) {
        String[] names = name.split(" ");
        return bookService.getByAuthor(names[0], names[1]);
    }

    @GetMapping("/quantity/{quantity}")
    public List<BookDto> getBooksWithQuantityBelow(@PathVariable Integer quantity) {
        return bookService.getBooksWithQuantityBelow(quantity);
    }
}
