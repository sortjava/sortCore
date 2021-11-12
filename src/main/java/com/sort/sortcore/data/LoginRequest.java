package com.sort.sortcore.data;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class LoginRequest {
    private String username;

    @NotBlank
    @Size(min = 3, max = 60, message = "email cannot be empty and be between {min} and {max} characters")
    private String email;

    @NotBlank
    @Size(min = 4, max = 120, message = "password cannot be empty and should be between {min} and {max} characters")
    private String password;

    @NotBlank(message = "provider cannot be empty")
    private String provider;
}