package com.komasan.springcrud.mappers;

import com.komasan.springcrud.DTO.UserRequest;
import com.komasan.springcrud.DTO.UserResponse;
import com.komasan.springcrud.user.UserClass;
import org.apache.catalina.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

// mapper convert Dto to entity
// UserRequest -> UserClass -> UserResponse
// @MappingTarget updates existing entity with new values from Dto
@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse toResponseDto(UserClass user);
    List<UserResponse> toResponseDto(List<UserClass> users);
    UserClass toEntity(UserRequest dto);
    void updateEntity(UserRequest dto, @MappingTarget UserClass user);
    }
