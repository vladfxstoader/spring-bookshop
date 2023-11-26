package com.example.bookshop.controller;

import com.example.bookshop.model.Author;
import com.example.bookshop.service.AuthorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("author")
@RestController
public class AuthorController {
    private AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<Author> getAll() {
        return authorService.getAll();
    }

    @PostMapping
    public void add(@RequestBody Author author) {
        authorService.add(author);
    }

    @PutMapping("/{id}")
    public void updateWithPut(@PathVariable Integer id, @RequestBody Author author) {
        authorService.updateWithPut(id, author);
    }

    @PatchMapping("/{id}")
    public void updateWithPatch(@PathVariable Integer id, @RequestBody Author author) {
        authorService.updateWithPatch(id, author);
    }

    @DeleteMapping("/{firstName}-{lastName}")
    public void delete(@PathVariable String firstName, @PathVariable String lastName) {
        authorService.delete(firstName, lastName);
    }
}
