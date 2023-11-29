package com.example.bookshop.repository;

import com.example.bookshop.exception.UserAlreadyExistsException;
import com.example.bookshop.exception.UserNotFoundException;
import com.example.bookshop.model.User;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserRepository {

    private List<User> users = new ArrayList<>();

    @PostConstruct
    private void setUp() {
        users.add(new User(1, "vlad@gmail.com", "12345678", "Vlad", "Toader", "0751888213", "Drumul Taberei 55", "Bucuresti", "061363", "Bucuresti"));
        users.add(new User(2, "tavi@yahoo.com", "Parola123", "Octavian", "Toader", "0746222123", "Strada Valea lui Trandafir 474", "Voinesti", "117432", "Arges"));
    }

    public List<User> getAll() {
        return users;
    }

    public void add(User user) {
        if (users.stream().filter(elem -> elem.getEmail().equals(user.getEmail())).toList().size() > 0) {
            throw new UserAlreadyExistsException("User with email " + user.getEmail() + " already exists in the database.");
        }
        Integer newId = users.size() + 1;
        user.setId(newId);
        users.add(user);
    }

    public void updateWithPut(Integer id, User newUser) {
        users.forEach(user -> {
            if(user.getId() == id) {
                user.setEmail(newUser.getEmail());
                user.setPassword(newUser.getPassword());
                user.setFirstName(newUser.getFirstName());
                user.setLastName(newUser.getLastName());
                user.setPhoneNumber(newUser.getPhoneNumber());
                user.setAddress(newUser.getAddress());
                user.setCity(newUser.getCity());
                user.setPostalCode(newUser.getPostalCode());
                user.setCounty(newUser.getCounty());
            }
        });
    }

    public void updateWithPatch(Integer id, User newUser) {
        users.forEach(user -> {
            if (user.getId() == id) {
                if(newUser.getEmail() != null) {
                    user.setEmail(newUser.getEmail());
                }
                if(newUser.getPassword() != null) {
                    user.setPassword(newUser.getPassword());
                }
                if(newUser.getFirstName() != null) {
                    user.setFirstName(newUser.getFirstName());
                }
                if(newUser.getLastName() != null) {
                    user.setLastName(newUser.getLastName());
                }
                if(newUser.getPhoneNumber() != null) {
                    user.setPhoneNumber(newUser.getPhoneNumber());
                }
                if(newUser.getAddress() != null) {
                    user.setAddress(newUser.getAddress());
                }
                if(newUser.getCity() != null) {
                    user.setCity(newUser.getCity());
                }
                if(newUser.getPostalCode() != null) {
                    user.setPostalCode(newUser.getPostalCode());
                }
                if(newUser.getCounty() != null) {
                    user.setCounty(newUser.getCounty());
                }
            }
        });
    }

    public void delete(String email) {
        List<User> newUsersList = new ArrayList<>();
        users.forEach(user -> {
            if(!user.getEmail().equals(email)) {
                newUsersList.add(user);
            }
        });
        users = newUsersList;
    }

    public User getByEmail(String email) {
        List<User> userList = users.stream().filter(elem -> elem.getEmail().equals(email)).collect(Collectors.toList());
        if(userList.size() == 0) {
            throw new UserNotFoundException("No user with email " + email + " found in the database.");
        }
        return userList.get(0);
    }
}
