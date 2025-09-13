package com.komasan.springcrud.controllers;

import com.komasan.springcrud.interfaces.UserRepository;
import com.komasan.springcrud.services.UserService;
import com.komasan.springcrud.user.UserClass;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping
    public List<UserClass> getUser() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserClass getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public UserClass createUser(@RequestBody UserClass userClass) {
        return userService.createNewUser(userClass);
    }

    @PutMapping("/put/{id}")
    public UserClass updateUser(@PathVariable Long id, @RequestBody UserClass userClass) {
        return userService.updateUser(id, userClass);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

}
