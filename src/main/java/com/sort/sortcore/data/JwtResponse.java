package com.sort.sortcore.data;

import lombok.Data;

import java.util.List;

@Data
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    //private String refreshToken;
    private Long id;
    private String username;
    private String email;
    private String provider;
    private List<String> roles;

    public JwtResponse(String accessToken, Long id, String username, String email, String provider, List<String> roles) {
        this.token = accessToken;
        //  this.refreshToken = refreshToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.provider = provider;
        this.roles = roles;
    }
}