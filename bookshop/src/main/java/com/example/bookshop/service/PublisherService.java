package com.example.bookshop.service;

import com.example.bookshop.dto.PublisherDto;
import com.example.bookshop.exception.PublisherAlreadyExistsException;
import com.example.bookshop.exception.PublisherNotFoundException;
import com.example.bookshop.mapper.PublisherMapper;
import com.example.bookshop.model.Publisher;
import com.example.bookshop.repository.PublisherRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublisherService {
    private PublisherRepository publisherRepository;
    private PublisherMapper publisherMapper;

    public PublisherService(PublisherRepository publisherRepository, PublisherMapper publisherMapper) {
        this.publisherMapper = publisherMapper;
        this.publisherRepository = publisherRepository;
    }
    
    public List<PublisherDto> getAll() {
        List<Publisher> allPublishers = publisherRepository.findAll();
        return publisherMapper.mapListToPublisherDto(allPublishers);
    }
    
    public PublisherDto save(PublisherDto publisherDto) {
        Optional<Publisher> optionalPublisher = publisherRepository.findByName(publisherDto.getName());
        if(!optionalPublisher.isPresent()) {
            Publisher dbPublisher = publisherRepository.save(publisherMapper.map(publisherDto));
            return publisherMapper.map(dbPublisher);
        }
        else {
            throw new PublisherAlreadyExistsException("Publisher with name " + publisherDto.getName() + " already exists in the database");
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

    public List<PublisherDto> getByCity(String city) {
        List<Publisher> publisherList = publisherRepository.findAllByCity(city);
        if(publisherList.size() == 0) {
            throw new PublisherNotFoundException("There are no publishers in " + city + ".");
        }
        return publisherMapper.mapListToPublisherDto(publisherList);
    }
}
