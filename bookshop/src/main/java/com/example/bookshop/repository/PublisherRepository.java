package com.example.bookshop.repository;

import com.example.bookshop.exception.PublisherAlreadyExistsException;
import com.example.bookshop.exception.PublisherNotFoundException;
import com.example.bookshop.model.Publisher;
import jakarta.annotation.PostConstruct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Integer> {
    Optional<Publisher> findByName(String name);
    List<Publisher> findAllByCity(String city);
//    public void updateWithPut(Integer id, Publisher newPublisher) {
//        publishers.forEach(publisher -> {
//            if(publisher.getId() == id) {
//                publisher.setName(newPublisher.getName());
//                publisher.setCity(newPublisher.getCity());
//            }
//        });
//    }
//
//    public void updateWithPatch(Integer id, Publisher newPublisher) {
//        publishers.forEach(publisher -> {
//            if(publisher.getId() == id) {
//                if(newPublisher.getName() != null) {
//                    publisher.setName(newPublisher.getName());
//                }
//                if(newPublisher.getCity() != null) {
//                    publisher.setCity(newPublisher.getCity());
//                }
//            }
//        });
//    }
//    public List<Publisher> getByCity(String city) {
//        List<Publisher> publisherList = publishers.stream().filter(elem -> elem.getCity().toUpperCase().equals(city.toUpperCase())).collect(Collectors.toList());
//        if (publisherList.size() == 0) {
//            throw new PublisherNotFoundException("No publishers were found in " + city + ".");
//        }
//        return publisherList;
//    }
}
