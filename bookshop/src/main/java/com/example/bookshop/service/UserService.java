package com.example.bookshop.service;

import com.example.bookshop.dto.UserDto;
import com.example.bookshop.exception.EntityAlreadyExistsException;
import com.example.bookshop.exception.EntityNotFoundException;
import com.example.bookshop.mapper.UserMapper;
import com.example.bookshop.model.User;
import com.example.bookshop.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    private UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    public List<UserDto> getAll() {
        List<User> allUsers = userRepository.findAll();
        return userMapper.mapListToUserDto(allUsers);
    }

    public UserDto save(UserDto userDto) {
        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());
        if(!optionalUser.isPresent()) {
            User dbUser = userRepository.save(userMapper.map(userDto));
            return userMapper.map(dbUser);
        }
        else {
            throw new EntityAlreadyExistsException("User with email " + userDto.getEmail() + " already exists in the database.");
        }
    }

    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    public UserDto getByEmail(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(!optionalUser.isPresent()) {
            throw new EntityNotFoundException("User with email " + email + " does not exist in the database.");
        }
        else {
            return userMapper.map(optionalUser.get());
        }
    }
}
