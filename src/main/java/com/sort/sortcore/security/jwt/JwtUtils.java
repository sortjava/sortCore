package com.sort.sortcore.security.jwt;

import com.sort.sortcore.service.impl.UserDetailsImpl;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    /*@Autowired
    AuthenticationManager authenticationManager;*/

    @Value("${sort.app.jwtSecret}")
    private String jwtSecret;

    @Value("${sort.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    @Value("${sort.app.jwtRefreshExpirationMs}")
    private int jwtRefreshExpirationMs;

    public String generateJwtToken(Authentication authentication) {
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        System.out.println("inside generateJwtToken method");
        return Jwts.builder()
                .setSubject((userPrincipal.getEmail()))
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
        /*return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();*/
    }

    /*public String doGenerateRefreshToken(Authentication authentication) {
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        return Jwts.builder().setSubject(userPrincipal.getEmail()).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtRefreshExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
    }*/

    public String getUserEmailFromJwtToken(String token) {
        System.out.println("inside getUserEmailFromJwtToken method");
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    /*public boolean validateJwtExpiration(String authToken) {
        System.out.println("inside validateJwtExpiration method");
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (ExpiredJwtException e) {
            System.out.println("inside ExpiredJwtException catch block");
            // this.doGenerateRefreshToken();
            logger.error("JWT token is nowwwww expired: {}", e.getMessage());
        } catch (Exception e) {
            logger.error("JWT generic exception occurred", e.getMessage());
        }
        return false;
    }*/

    public boolean validateJwtToken(String authToken) {
        System.out.println("inside validateJwtToken method");
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            /*Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(null, null));
            System.out.println("inside ExpiredJwtException catch block");
            String jwtTokentemp = this.generateJwtToken(authentication);
            System.out.println(jwtTokentemp);*/
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }
}