package com.komasan.springcrud.DTO;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    @Size(min = 3, max = 15)
    private String username;

    @NotNull
    private Integer age;

    @Email
    @NotBlank
    private String email;

    @Size(min = 3, max = 15)
    private String password;
}