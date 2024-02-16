package com.example.springdatacloud.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegistrationUserDto {
    @NotNull
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String confirmedPassword;
    private String email;
}
