package com.example.bookshop.unit.service;

import com.example.bookshop.dto.AuthorDto;
import com.example.bookshop.dto.UserDto;
import com.example.bookshop.exception.EntityAlreadyExistsException;
import com.example.bookshop.exception.EntityNotFoundException;
import com.example.bookshop.mapper.UserMapper;
import com.example.bookshop.model.User;
import com.example.bookshop.repository.UserRepository;
import com.example.bookshop.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class UserServiceUnitTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;
    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAll_success() {
        List<User> users = getDummyUsers();
        List<UserDto> userDtos = getDummyUserDtos();
        Mockito.when(userRepository.findAll())
                .thenReturn(users);
        Mockito.when(userMapper.mapListToUserDto(users))
                .thenReturn(userDtos);

        List<UserDto> returnedUsers = userService.getAll();

        Assertions.assertNotNull(returnedUsers, "Returned list should not be null");
        Assertions.assertEquals(userDtos.size(), returnedUsers.size(), "Returned list size should match");

        for (int i = 0; i < returnedUsers.size(); i++) {
            UserDto expectedDto = userDtos.get(i);
            UserDto returnedDto = returnedUsers.get(i);

            Assertions.assertEquals(expectedDto.getId(), returnedDto.getId(), "User ID should match");
            Assertions.assertEquals(expectedDto.getFirstName(), returnedDto.getFirstName(), "User first name should match");
            Assertions.assertEquals(expectedDto.getLastName(), returnedDto.getLastName(), "User last name should match");
            Assertions.assertEquals(expectedDto.getEmail(), returnedDto.getEmail(), "User email should match");
            Assertions.assertEquals(expectedDto.getPhoneNumber(), returnedDto.getPhoneNumber(), "User phone number should match");
        }
    }

    @Test
    public void testSave_success() {
        UserDto userDto = getDummyUserDtoOne();
        User user = getDummyUserOne();
        userDto.setId(null);
        UserDto savedUserDto = getDummyUserDtoOne();

        Mockito.when(userRepository.findByEmail(Mockito.any()))
                .thenReturn(Optional.empty());
        Mockito.when(userMapper.map(userDto))
                .thenReturn(user);
        Mockito.when(userRepository.save(user))
                .thenReturn(user);
        Mockito.when(userMapper.map(user))
                .thenReturn(savedUserDto);

        UserDto returnedUser = userService.save(userDto);

        Assertions.assertEquals(savedUserDto.getId(), returnedUser.getId(), "User ID should match");
        Assertions.assertEquals(savedUserDto.getFirstName(), returnedUser.getFirstName(), "User first name should match");
        Assertions.assertEquals(savedUserDto.getLastName(), returnedUser.getLastName(), "User last name should match");
        Assertions.assertEquals(savedUserDto.getEmail(), returnedUser.getEmail(), "User email should match");
        Assertions.assertEquals(savedUserDto.getPhoneNumber(), returnedUser.getPhoneNumber(), "User phone number should match");
    }

    @Test
    public void testSave_exception() {
        UserDto userDto = getDummyUserDtoOne();
        User existingUser = getDummyUserOne();
        userDto.setId(null);

        Mockito.when(userRepository.findByEmail(Mockito.any()))
                .thenReturn(Optional.of(existingUser));
        Mockito.when(userMapper.map(userDto))
                .thenReturn(existingUser);

        Assertions.assertThrows(EntityAlreadyExistsException.class, () -> {userService.save(userDto);});
    }

    @Test
    public void testDeleteByEmail_success() {
        User user = getDummyUserOne();
        String userEmail = "george@test.com";
        Mockito.when(userRepository.findByEmail(Mockito.any()))
                .thenReturn(Optional.of(user));

        userService.deleteByEmail(userEmail);

        Mockito.verify(userRepository, Mockito.times(1)).deleteById(user.getId());
    }

    @Test
    public void testDeleteByEmail_exception() {
        String nonExistingEmail = "nonexistent@test.com";
        Mockito.when(userRepository.findByEmail(nonExistingEmail))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            userService.deleteByEmail(nonExistingEmail);
        });
    }

    @Test
    public void testGetByEmail_success() {
        User user = getDummyUserOne();
        UserDto userDto = getDummyUserDtoOne();
        Mockito.when(userRepository.findByEmail(Mockito.any()))
                .thenReturn(Optional.of(user));
        Mockito.when(userMapper.map(user))
                .thenReturn(userDto);

        UserDto returnedUser = userService.getByEmail("george@test.com");

        Assertions.assertEquals(userDto.getId(), returnedUser.getId(), "User ID should match");
        Assertions.assertEquals(userDto.getFirstName(), returnedUser.getFirstName(), "User first name should match");
        Assertions.assertEquals(userDto.getLastName(), returnedUser.getLastName(), "User last name should match");
        Assertions.assertEquals(userDto.getEmail(), returnedUser.getEmail(), "User email should match");
        Assertions.assertEquals(userDto.getPhoneNumber(), returnedUser.getPhoneNumber(), "User phone number should match");
    }

    @Test
    public void testGetByEmail_exception() {
        Mockito.when(userRepository.findByEmail(Mockito.any()))
                .thenReturn(Optional.empty());
        Assertions.assertThrows(EntityNotFoundException.class, () -> userService.getByEmail("nonexistent@test.com"));
    }

    public User getDummyUserOne() {
        User user = new User();
        user.setId(101);
        user.setFirstName("George");
        user.setLastName("Popescu");
        user.setEmail("george@test.com");
        user.setPhoneNumber("0774222111");
        return user;
    }

    public User getDummyUserTwo() {
        User user = new User();
        user.setId(101);
        user.setFirstName("Maria");
        user.setLastName("Ionescu");
        user.setEmail("mion@test.com");
        user.setPhoneNumber("0774223112");
        return user;
    }

    public UserDto getDummyUserDtoOne() {
        UserDto userDto = new UserDto();
        userDto.setId(101);
        userDto.setFirstName("George");
        userDto.setLastName("Popescu");
        userDto.setEmail("george@test.com");
        userDto.setPhoneNumber("0774222111");
        return userDto;
    }

    public UserDto getDummyUserDtoTwo() {
        UserDto userDto = new UserDto();
        userDto.setId(101);
        userDto.setFirstName("Maria");
        userDto.setLastName("Ionescu");
        userDto.setEmail("mion@test.com");
        userDto.setPhoneNumber("0774223112");
        return userDto;
    }

    public List<User> getDummyUsers() {
        return new ArrayList<>(Arrays.asList(getDummyUserOne(), getDummyUserTwo()));
    }

    public List<UserDto> getDummyUserDtos() {
        return new ArrayList<>(Arrays.asList(getDummyUserDtoOne(), getDummyUserDtoTwo()));
    }
}
