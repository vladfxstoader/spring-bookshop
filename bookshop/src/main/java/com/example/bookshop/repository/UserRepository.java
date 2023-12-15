package com.example.bookshop.repository;

import com.example.bookshop.exception.UserAlreadyExistsException;
import com.example.bookshop.exception.UserNotFoundException;
import com.example.bookshop.model.Publisher;
import com.example.bookshop.model.User;
import jakarta.annotation.PostConstruct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
//    public void updateWithPut(Integer id, User newUser) {
//        users.forEach(user -> {
//            if(user.getId() == id) {
//                user.setEmail(newUser.getEmail());
//                user.setPassword(newUser.getPassword());
//                user.setFirstName(newUser.getFirstName());
//                user.setLastName(newUser.getLastName());
//                user.setPhoneNumber(newUser.getPhoneNumber());
//                user.setAddress(newUser.getAddress());
//                user.setCity(newUser.getCity());
//                user.setPostalCode(newUser.getPostalCode());
//                user.setCounty(newUser.getCounty());
//            }
//        });
//    }
//
//    public void updateWithPatch(Integer id, User newUser) {
//        users.forEach(user -> {
//            if (user.getId() == id) {
//                if(newUser.getEmail() != null) {
//                    user.setEmail(newUser.getEmail());
//                }
//                if(newUser.getPassword() != null) {
//                    user.setPassword(newUser.getPassword());
//                }
//                if(newUser.getFirstName() != null) {
//                    user.setFirstName(newUser.getFirstName());
//                }
//                if(newUser.getLastName() != null) {
//                    user.setLastName(newUser.getLastName());
//                }
//                if(newUser.getPhoneNumber() != null) {
//                    user.setPhoneNumber(newUser.getPhoneNumber());
//                }
//                if(newUser.getAddress() != null) {
//                    user.setAddress(newUser.getAddress());
//                }
//                if(newUser.getCity() != null) {
//                    user.setCity(newUser.getCity());
//                }
//                if(newUser.getPostalCode() != null) {
//                    user.setPostalCode(newUser.getPostalCode());
//                }
//                if(newUser.getCounty() != null) {
//                    user.setCounty(newUser.getCounty());
//                }
//            }
//        });
//    }

}
