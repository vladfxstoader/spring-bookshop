package com.example.bookshop.mapper;

import com.example.bookshop.dto.UserDto;
import com.example.bookshop.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    private AddressMapper addressMapper;

    public UserMapper(AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
    }

    public User map(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setAddress(addressMapper.map(userDto.getAddress()));
        return user;
    }

    public UserDto map(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setAddress(addressMapper.map(user.getAddress()));
        return userDto;
    }

    public List<UserDto> mapListToUserDto(List<User> users) {
        return users.stream().map(user -> map(user)).collect(Collectors.toList());
    }

    public List<User> mapListToUser(List<UserDto> userDtos) {
        return userDtos.stream().map(userDto -> map(userDto)).collect(Collectors.toList());
    }
}
