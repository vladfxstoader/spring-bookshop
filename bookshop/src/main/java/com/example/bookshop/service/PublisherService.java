package com.example.bookshop.service;

import com.example.bookshop.exception.PublisherAlreadyExistsException;
import com.example.bookshop.exception.PublisherNotFoundException;
import com.example.bookshop.model.Publisher;
import com.example.bookshop.repository.PublisherRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublisherService {
    private PublisherRepository publisherRepository;

    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }
    
    public List<Publisher> getAll() {
        return publisherRepository.findAll();
    }
    
    public void save(Publisher publisher) {
        Optional<Publisher> optionalPublisher = publisherRepository.findByName(publisher.getName());
        if(!optionalPublisher.isPresent()) {
            publisherRepository.save(publisher);
        }
        else {
            throw new PublisherAlreadyExistsException("Publisher with name " + publisher.getName() + " already exists in the database");
        }
    }
    
//    public void updateWithPut(Integer id, Publisher publisher) {
//        publisherRepository.updateWithPut(id, publisher);
//    }
//
//    public void updateWithPatch(Integer id, Publisher publisher) {
//        publisherRepository.updateWithPatch(id, publisher);
//    }
//
    public void delete(Integer id) {
        publisherRepository.deleteById(id);
    }

    public List<Publisher> getByCity(String city) {
        List<Publisher> publisherList = publisherRepository.findAllByCity(city);
        System.out.println(publisherList.size());
        if(publisherList.size() == 0) {
            throw new PublisherNotFoundException("There are no publishers in " + city + ".");
        }
        return publisherList;
    }
}
