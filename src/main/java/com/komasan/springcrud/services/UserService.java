package com.komasan.springcrud.services;

import com.komasan.springcrud.controllers.UserController;
import com.komasan.springcrud.mappers.UserMapper;
import com.komasan.springcrud.repository.UserLogRepository;
import com.komasan.springcrud.repository.UserRepository;
import com.komasan.springcrud.user.UserAuditLog;
import com.komasan.springcrud.user.UserClass;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserLogRepository userLogRepository;

    public List<UserClass> getAllUsers() {
        return userRepository.findAll();
    }

    public UserClass getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public UserClass createNewUser(UserClass user) {
        return userRepository.save(user);
    }

    public UserClass updateUser(Long id, UserClass userClass) {
        UserClass existingUser = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        existingUser.setUsername(userClass.getUsername());
        existingUser.setAge(userClass.getAge());
        existingUser.setEmail(userClass.getEmail());

        return userRepository.save(existingUser);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}

