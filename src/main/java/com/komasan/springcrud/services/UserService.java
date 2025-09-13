package com.komasan.springcrud.services;

import com.komasan.springcrud.interfaces.UserRepository;
import com.komasan.springcrud.user.UserClass;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
@AllArgsConstructor
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public List<UserClass> getAllUsers() {
        return userRepository.findAll();
    }

    public UserClass getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException());
    }

    public UserClass createNewUser(UserClass user) {
        return userRepository.save(user);
    }

    public UserClass updateUser(Long id, UserClass userClass) {
        UserClass existingUser = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException());

        existingUser.setUsername(userClass.getUsername());
        existingUser.setAge(userClass.getAge());
        existingUser.setEmail(userClass.getEmail());

        return userRepository.save(existingUser);
    }

    public void deleteUser(Long id) {
         userRepository.deleteById(id);
    }
}
