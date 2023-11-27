package com.example.bookshop.controller;

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
    public List<Publisher> getAll() {
        return publisherService.getAll();
    }

    @PostMapping
    public void add (@RequestBody Publisher publisher) {
        publisherService.add(publisher);
    }

    @PutMapping("/{id}")
    public void updateWithPut(@PathVariable Integer id, @RequestBody Publisher publisher) {
        publisherService.updateWithPut(id, publisher);
    }

    @PatchMapping("/{id}")
    public void updateWithPatch(@PathVariable Integer id, @RequestBody Publisher publisher) {
        publisherService.updateWithPatch(id, publisher);
    }

    @DeleteMapping("/{name}")
    public void delete(@PathVariable String name) {
        publisherService.delete(name);
    }

    @GetMapping("/city")
    public List<Publisher> getByCity(@RequestParam String city) {
        return publisherService.getByCity(city);
    }

}
