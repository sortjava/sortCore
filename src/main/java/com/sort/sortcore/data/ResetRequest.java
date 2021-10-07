package com.sort.sortcore.data;

import javax.validation.constraints.NotBlank;

public class ResetRequest {
    @NotBlank
    private String provider;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}