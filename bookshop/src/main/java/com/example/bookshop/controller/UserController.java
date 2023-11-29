package com.example.bookshop.controller;

import com.example.bookshop.model.User;
import com.example.bookshop.service.UserService;
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
    public List<User> getAll() {
        return userService.getAll();
    }

    @PostMapping
    public void add(@RequestBody User user) {
        userService.add(user);
    }

    @PutMapping("/{id}")
    public void updateWithPut(@PathVariable Integer id, @RequestBody User user) {
        userService.updateWithPut(id, user);
    }

    @PatchMapping("/{id}")
    public void updateWithPatch(@PathVariable Integer id, @RequestBody User user) {
        userService.updateWithPatch(id, user);
    }

    @DeleteMapping()
    public void delete(@RequestBody String email) {
        userService.delete(email);
    }

    @GetMapping("/email")
    public User getByEmail(@RequestBody String email) {
        return userService.getByEmail(email);
    }
}
