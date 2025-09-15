package com.komasan.springcrud.repository;

import com.komasan.springcrud.user.UserClass;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserClass, Long> {
    UserClass findByUsername(String username);
    UserClass findByEmail(String email);
}