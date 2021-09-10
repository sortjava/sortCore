package com.sort.sortcore.security.jwt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        logger.error("Unauthorized error: {}", authException.getMessage());
        if("User is disabled" == authException.getMessage().toString()) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error: Your account is not activated yet. Kindly check your email and confirm your registration by clicking on account verification link.");
        } else if ( "Bad credentials" == authException.getMessage().toString()) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error: User email or Password is incorrect");
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error: Unauthorized");
        }
    }
}