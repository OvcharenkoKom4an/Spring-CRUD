package com.komasan.springcrud.controllers;

import com.komasan.springcrud.DTO.UserRequest;
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
        return ResponseEntity.ok(userMapper.toResponseDto(user));
    }


    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest userRequest) {
        //making Dto request entity
        UserClass userEntity = userMapper.toEntity(userRequest);
        // save through the service
        UserClass saveEntity = userService.createNewUser(userEntity);
        if(saveEntity == null) {
            return ResponseEntity.badRequest().build();
        }
        // making Dto to an entity
        UserResponse responseDto = userMapper.toResponseDto(saveEntity);

        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @Valid @RequestBody UserRequest userRequest) {
        UserClass existingUser = userService.getUserById(id);
        if(existingUser == null) {
            return ResponseEntity.notFound().build();
        }
        userMapper.updateEntity(userRequest, existingUser);
        UserClass updateEntity = userService.updateUser(id, existingUser);
        UserResponse responseDto = userMapper.toResponseDto(updateEntity);
        return ResponseEntity.ok(responseDto);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
