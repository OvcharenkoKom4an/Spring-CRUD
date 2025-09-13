package com.komasan.springcrud.controllers;

import com.komasan.springcrud.repository.UserRepository;
import com.komasan.springcrud.services.UserService;
import com.komasan.springcrud.user.UserClass;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserClass>> getAllUsers() {
        List<UserClass> users = userService.getAllUsers();
        if(users.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserClass> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping("/post")
    public ResponseEntity<UserClass> createUser(@Valid @RequestBody UserClass userClass) {
        return ResponseEntity.ok(userService.createNewUser(userClass));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserClass> updateUser(@PathVariable Long id, @Valid @RequestBody UserClass userClass) {
        return ResponseEntity.ok(userService.updateUser(id, userClass));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
