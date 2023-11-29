package com.example.bookshop.service;

import com.example.bookshop.model.User;
import com.example.bookshop.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAll() {
        return userRepository.getAll();
    }

    public void add(User user) {
        userRepository.add(user);
    }

    public void updateWithPut(Integer id, User user) {
        userRepository.updateWithPut(id, user);
    }

    public void updateWithPatch(Integer id, User user) {
        userRepository.updateWithPatch(id, user);
    }

    public void delete(String email) {
        userRepository.delete(email);
    }

    public User getByEmail(String email) {
        return userRepository.getByEmail(email);
    }

}
