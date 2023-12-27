package com.example.bookshop.service;

import com.example.bookshop.dto.BookDto;
import com.example.bookshop.dto.PublisherDto;
import com.example.bookshop.exception.EntityAlreadyExistsException;
import com.example.bookshop.exception.EntityNotFoundException;
import com.example.bookshop.mapper.BookMapper;
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
    private BookMapper bookMapper;

    public PublisherService(PublisherRepository publisherRepository, PublisherMapper publisherMapper, BookMapper bookMapper) {
        this.publisherMapper = publisherMapper;
        this.publisherRepository = publisherRepository;
        this.bookMapper = bookMapper;
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
            throw new EntityAlreadyExistsException("Publisher with name " + publisherDto.getName() + " already exists in the database");
        }
    }

    public void delete(Integer id) {
        publisherRepository.deleteById(id);
    }

    public List<PublisherDto> getByCity(String city) {
        List<Publisher> publisherList = publisherRepository.findAllByCity(city);
        if(publisherList.size() == 0) {
            throw new EntityNotFoundException("There are no publishers in " + city + ".");
        }
        return publisherMapper.mapListToPublisherDto(publisherList);
    }

    public List<BookDto> getAllBooksByPublisher(String name) {
        Optional<Publisher> optionalPublisher = publisherRepository.findByName(name);
        if(!optionalPublisher.isPresent()) {
            throw new EntityNotFoundException("Publisher with name " + name + " does not exist in the database");
        }
        else {
            return bookMapper.mapListToBookDto(optionalPublisher.get().getBooks());
        }
    }
}
