package com.example.bookshop.controller;

import com.example.bookshop.dto.BookDto;
import com.example.bookshop.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Book", description = "Book management APIs")
@RequestMapping("book")
@RestController
public class BookController {
    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @Operation(summary = "Get all books")
    @GetMapping
    public List<BookDto> getAll() {
        return bookService.getAll();
    }

    @Operation(summary = "Add a new book")
    @PostMapping
    public BookDto save(@RequestBody BookDto bookDto) {
        return bookService.save(bookDto);
    }

    @Operation(summary = "Delete a book by id")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        bookService.delete(id);
    }

    @Operation(summary = "Get all books by genre")
    @GetMapping("/genre/{genre}")
    public List<BookDto> getByGenre(@PathVariable String genre) {
        return bookService.findByGenre(genre);
    }

    @Operation(summary = "Get all books by title")
    @GetMapping("/title")
    public List<BookDto> getAllBooksByTitle(@RequestBody String title) {
        return bookService.getByTitle(title);
    }

    @Operation(summary = "Get all books by author")
    @GetMapping("/author")
    public List<BookDto> getAllBooksByAuthor(@RequestBody String name) {
        String[] names = name.split(" ");
        return bookService.getByAuthor(names[0], names[1]);
    }

    @Operation(summary = "Get books with critical quantity")
    @GetMapping("/quantity/{quantity}")
    public List<BookDto> getBooksWithQuantityBelow(@PathVariable Integer quantity) {
        return bookService.getBooksWithQuantityBelow(quantity);
    }
}
