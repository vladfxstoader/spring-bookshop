package com.example.bookshop.controller;

import com.example.bookshop.dto.AuthorDto;
import com.example.bookshop.model.Author;
import com.example.bookshop.service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Author", description = "Author management APIs")
@RequestMapping("author")
@RestController
public class AuthorController {
    private AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Operation(summary = "Get all authors")
    @GetMapping
    public List<AuthorDto> getAll() {
        return authorService.getAll();
    }

    @Operation(summary = "Add a new author")
    @PostMapping
    public AuthorDto save(@RequestBody AuthorDto authorDto) {
        return authorService.save(authorDto);
    }

//    @PutMapping("/{id}")
//    public void updateWithPut(@PathVariable Integer id, @RequestBody Author author) {
//        authorService.updateWithPut(id, author);
//    }
//
//    @PatchMapping("/{id}")
//    public void updateWithPatch(@PathVariable Integer id, @RequestBody Author author) {
//        authorService.updateWithPatch(id, author);
//    }

    @Operation(summary = "Delete an author by id")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        authorService.delete(id);
    }

    @Operation(summary = "Get author profile by name")
    @GetMapping("/name/{firstName}-{lastName}")
    public AuthorDto getByName(@PathVariable String firstName, @PathVariable String lastName) {
        return authorService.getByName(firstName, lastName);
    }
}
