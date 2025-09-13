package com.komasan.springcrud.interfaces;

import com.komasan.springcrud.user.UserClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends JpaRepository<UserClass, Long > {

}
