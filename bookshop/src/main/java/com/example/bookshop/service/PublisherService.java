package com.example.bookshop.service;

import com.example.bookshop.model.Publisher;
import com.example.bookshop.repository.PublisherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService {
    private PublisherRepository publisherRepository;

    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }
    
    public List<Publisher> getAll() {
        return publisherRepository.getAll();
    }
    
    public void add(Publisher publisher) {
        publisherRepository.add(publisher);
    }
    
    public void updateWithPut(Integer id, Publisher publisher) {
        publisherRepository.updateWithPut(id, publisher);
    }
    
    public void updateWithPatch(Integer id, Publisher publisher) {
        publisherRepository.updateWithPatch(id, publisher);
    }
    
    public void delete(String name) {
        publisherRepository.delete(name);
    }

    public List<Publisher> getByCity(String city) {
        return publisherRepository.getByCity(city);
    }
}
