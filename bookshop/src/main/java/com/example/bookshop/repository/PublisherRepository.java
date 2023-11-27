package com.example.bookshop.repository;

import com.example.bookshop.model.Publisher;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PublisherRepository {
    private List<Publisher> publishers = new ArrayList<>();

    @PostConstruct
    private void setUp() {
        publishers.add(new Publisher(1, "Trei", "Bucuresti"));
        publishers.add(new Publisher(2, "Corint", "Brasov"));
    }

    public List<Publisher> getAll() {
        return publishers;
    }

    public void add(Publisher publisher) {
        Integer newId = publishers.size() + 1;
        publisher.setId(newId);
        publishers.add(publisher);
    }

    public void updateWithPut(Integer id, Publisher newPublisher) {
        publishers.forEach(publisher -> {
            if(publisher.getId() == id) {
                publisher.setName(newPublisher.getName());
                publisher.setCity(newPublisher.getCity());
            }
        });
    }

    public void updateWithPatch(Integer id, Publisher newPublisher) {
        publishers.forEach(publisher -> {
            if(publisher.getId() == id) {
                if(newPublisher.getName() != null) {
                    publisher.setName(newPublisher.getName());
                }
                if(newPublisher.getCity() != null) {
                    publisher.setCity(newPublisher.getCity());
                }
            }
        });
    }

    public void delete(String name) {
        List<Publisher> newPublisherList = new ArrayList<>();
        publishers.forEach(publisher -> {
            if(!publisher.getName().equals(name)) {
                newPublisherList.add(publisher);
            }
        });
        publishers = newPublisherList;
    }

    public List<Publisher> getByCity(String city) {
        List<Publisher> publisherList = publishers.stream().filter(elem -> elem.getCity().toUpperCase().equals(city.toUpperCase())).collect(Collectors.toList());
        if (publisherList.size() == 0) {
            return publisherList;
        }
        return publisherList;
    }
}
