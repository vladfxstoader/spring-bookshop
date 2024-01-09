package com.example.bookshop.controller;

import com.example.bookshop.dto.BookDto;
import com.example.bookshop.dto.PublisherDto;
import com.example.bookshop.model.Publisher;
import com.example.bookshop.service.PublisherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Publisher", description = "Publisher management APIs")
@RequestMapping("publisher")
@RestController
public class PublisherController {
    private PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @Operation(summary = "Get all publishers")
    @GetMapping
    public List<PublisherDto> getAll() {
        return publisherService.getAll();
    }

    @Operation(summary = "Add a new publisher")
    @PostMapping
    public PublisherDto save (@RequestBody PublisherDto publisherDto) {
        return publisherService.save(publisherDto);
    }

//    @PutMapping("/{id}")
//    public void updateWithPut(@PathVariable Integer id, @RequestBody Publisher publisher) {
//        publisherService.updateWithPut(id, publisher);
//    }
//
//    @PatchMapping("/{id}")
//    public void updateWithPatch(@PathVariable Integer id, @RequestBody Publisher publisher) {
//        publisherService.updateWithPatch(id, publisher);
//    }

    @Operation(summary = "Delete publisher by id")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        publisherService.delete(id);
    }

    @Operation(summary = "Get all publishers from a city")
    @GetMapping("/city")
    public List<PublisherDto> getByCity(@RequestParam String city) {
        return publisherService.getByCity(city);
    }

    @Operation(summary = "Get all books issued by a publisher")
    @GetMapping("/getBooks/{name}")
    public List<BookDto> getBooksByPublisher(@PathVariable String name) {
        return publisherService.getAllBooksByPublisher(name);
    }

}
