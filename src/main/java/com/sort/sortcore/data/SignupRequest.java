package com.sort.sortcore.data;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 60, message = "username cannot be empty and should be between {min} and {max} characters")
    private String username;

    @NotBlank(message = "provider cannot be empty")
    private String provider;

    @NotBlank
    @Size(min = 3, max = 60, message = "email cannot be empty and be between {min} and {max} characters")
    private String email;

    private Set<String> role;

    @NotBlank
    @Size(min = 4, max = 120, message = "password cannot be empty and should be between {min} and {max} characters")
    private String password;
}