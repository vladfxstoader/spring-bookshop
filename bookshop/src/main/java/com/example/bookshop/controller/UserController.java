package com.example.bookshop.controller;

import com.example.bookshop.dto.UserDto;
import com.example.bookshop.model.User;
import com.example.bookshop.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "User", description = "User management APIs")
@RequestMapping("user")
@RestController
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Get all user accounts")
    @GetMapping
    public List<UserDto> getAll() {
        return userService.getAll();
    }

    @Operation(summary = "Create a new account")
    @PostMapping
    public UserDto save(@RequestBody @Valid UserDto userDto) {
        return userService.save(userDto);
    }

    @Operation(summary = "Delete an account by email")
    @DeleteMapping("/delete")
    public void delete(@RequestBody String email) {
        userService.deleteByEmail(email);
    }

    @Operation(summary = "Get an user profile using email")
    @GetMapping("/email")
    public UserDto getByEmail(@RequestBody String email) {
        return userService.getByEmail(email);
    }
}
