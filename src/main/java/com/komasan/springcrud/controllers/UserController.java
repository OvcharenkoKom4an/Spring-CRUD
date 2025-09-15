package com.komasan.springcrud.controllers;

import com.komasan.springcrud.DTO.UserRequest;
import com.komasan.springcrud.DTO.UserResponse;
import com.komasan.springcrud.mappers.UserMapper;
import com.komasan.springcrud.repository.UserLogRepository;
import com.komasan.springcrud.repository.UserRepository;
import com.komasan.springcrud.services.UserAuditLogService;
import com.komasan.springcrud.services.UserService;
import com.komasan.springcrud.user.UserClass;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final UserAuditLogService userAuditLogService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserClass> users = userService.getAllUsers();
        if (users.isEmpty()) {
            log.warn("No users found");
            return ResponseEntity.notFound().build();
        }
        List<UserResponse> responseDto = users
                .stream()
                .map(userMapper::toResponseDto)
                .collect(Collectors.toList());
        userAuditLogService.logAction("GET", "UserClass", null);

        log.info("{} Users was found", responseDto.size());
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        UserClass user = userService.getUserById(id);
        if (user == null) {
            log.warn("User with id {} not found", id);
            return ResponseEntity.notFound().build();
        }
        userAuditLogService.logAction("GET", "UserClass", id);

        log.info("User with id {} was created", id);
        return ResponseEntity.ok(userMapper.toResponseDto(user));
    }


    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest userRequest) {
        //making Dto request entity
        UserClass userEntity = userMapper.toEntity(userRequest);
        // save through the service
        UserClass saveEntity = userService.createNewUser(userEntity);
        if (saveEntity == null) {
            return ResponseEntity.badRequest().build();
        }
        // making Dto to an entity
        UserResponse responseDto = userMapper.toResponseDto(saveEntity);
        userAuditLogService.logAction("POST", "UserClass", saveEntity.getId());

        log.info("user with id {} was created", saveEntity.getId());
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @Valid @RequestBody UserRequest userRequest) {
        UserClass existingUser = userService.getUserById(id);
        if (existingUser == null) {
            return ResponseEntity.notFound().build();
        }
        userMapper.updateEntity(userRequest, existingUser);
        UserClass updateEntity = userService.updateUser(id, existingUser);
        UserResponse responseDto = userMapper.toResponseDto(updateEntity);
        userAuditLogService.logAction("PUT", "UserClass", updateEntity.getId());

        log.info("User with id {} was updated", id);
        return ResponseEntity.ok(responseDto);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        log.warn("No such user with id {}", id);
        userService.deleteUser(id);
        userAuditLogService.logAction("DELETE", "User", id);

        log.info("User with id {} was deleted", id);
        return ResponseEntity.noContent().build();
    }
}
