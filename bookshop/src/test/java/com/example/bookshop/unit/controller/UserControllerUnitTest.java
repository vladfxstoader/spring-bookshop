package com.example.bookshop.unit.controller;

import com.example.bookshop.controller.UserController;
import com.example.bookshop.dto.UserDto;
import com.example.bookshop.exception.EntityAlreadyExistsException;
import com.example.bookshop.exception.EntityNotFoundException;
import com.example.bookshop.model.User;
import com.example.bookshop.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserControllerUnitTest {
    private MockMvc mockMvc;
    @Mock
    private UserService userService;
    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void testGetAll_success() throws Exception {
        List<UserDto> userDtos = getDummyUserDtos();

        Mockito.when(userService.getAll())
                .thenReturn(userDtos);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/user"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(new ObjectMapper().writeValueAsString(userDtos)));
    }

    @Test
    public void testSave_success() throws Exception {
        UserDto userDto = getDummyUserDtoOne();
        userDto.setId(null);
        UserDto savedUserDto = getDummyUserDtoOne();
        Mockito.when(userService.save(Mockito.any(UserDto.class)))
                .thenReturn(savedUserDto);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(new ObjectMapper().writeValueAsString(savedUserDto)));
    }

    @Test
    public void testDelete() throws Exception {
        String email = "george@test.com";

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/user/delete")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(email))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetByEmail_success() throws Exception {
        String email = "george@test.com";
        UserDto userDto = getDummyUserDtoOne();

        Mockito.when(userService.getByEmail(Mockito.any()))
                .thenReturn(userDto);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/user/email")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(email))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(new ObjectMapper().writeValueAsString(userDto)));
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
