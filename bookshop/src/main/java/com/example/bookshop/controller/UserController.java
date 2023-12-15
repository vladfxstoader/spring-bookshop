package com.example.bookshop.controller;

import com.example.bookshop.dto.UserDto;
import com.example.bookshop.model.User;
import com.example.bookshop.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("user")
@RestController
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDto> getAll() {
        return userService.getAll();
    }

    @PostMapping
    public UserDto save(@RequestBody @Valid UserDto userDto) {
        return userService.save(userDto);
    }
//
//    @PutMapping("/{id}")
//    public void updateWithPut(@PathVariable Integer id, @RequestBody User user) {
//        userService.updateWithPut(id, user);
//    }
//
//    @PatchMapping("/{id}")
//    public void updateWithPatch(@PathVariable Integer id, @RequestBody User user) {
//        userService.updateWithPatch(id, user);
//    }
//
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        userService.delete(id);
    }

    @GetMapping("/email")
    public UserDto getByEmail(@RequestBody String email) {
        return userService.getByEmail(email);
    }
}
