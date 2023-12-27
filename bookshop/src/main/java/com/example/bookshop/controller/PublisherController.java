package com.example.bookshop.controller;

import com.example.bookshop.dto.BookDto;
import com.example.bookshop.dto.PublisherDto;
import com.example.bookshop.model.Publisher;
import com.example.bookshop.service.PublisherService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("publisher")
@RestController
public class PublisherController {
    private PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping
    public List<PublisherDto> getAll() {
        return publisherService.getAll();
    }

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

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        publisherService.delete(id);
    }

    @GetMapping("/city")
    public List<PublisherDto> getByCity(@RequestParam String city) {
        return publisherService.getByCity(city);
    }

    @GetMapping("/getBooks/{name}")
    public List<BookDto> getBooksByPublisher(@PathVariable String name) {
        return publisherService.getAllBooksByPublisher(name);
    }

}
