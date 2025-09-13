package com.komasan.springcrud.controllers;

import com.komasan.springcrud.DTO.UserResponse;
import com.komasan.springcrud.mappers.UserMapper;
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
    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserClass> users = userService.getAllUsers();
        if (users.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<UserResponse> responseDto = userMapper.toResponseDto(users);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        UserClass user = userService.getUserById(id);
        if(user == null) {
            return ResponseEntity.notFound().build();
        }
        UserResponse responseDto = userMapper.toResponseDto(user);
        return ResponseEntity.ok(responseDto);
    }


    @PostMapping
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
