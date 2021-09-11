package com.sort.sortcore.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.sort.sortcore.data.*;
import com.sort.sortcore.repository.ConfirmationTokenRepository;
import com.sort.sortcore.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.web.bind.annotation.*;

import com.sort.sortcore.repository.RoleRepository;
import com.sort.sortcore.repository.UserRepository;
import com.sort.sortcore.security.jwt.JwtUtils;
import com.sort.sortcore.service.impl.UserDetailsImpl;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    EmailSenderService emailSenderService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    HttpServletRequest servletRequest;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()), false, null);

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        ConfirmationToken confirmationToken = new ConfirmationToken(user);

        confirmationTokenRepository.save(confirmationToken);

        String baseUrl = ServletUriComponentsBuilder.fromRequestUri(servletRequest).replacePath(null).build().toUriString();

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Complete your SORT registration!");
        mailMessage.setFrom("sortedjava@gmail.com");
        mailMessage.setText("To confirm your account, please click here : "+baseUrl+"/api/auth/confirm-account?token="+confirmationToken.getConfirmationToken());

        emailSenderService.sendEmail(mailMessage);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!. Please check your email to verify your account."));
    }

    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> confirmUserAccount(@RequestParam("token")String confirmationToken) {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if(token != null) {
            User user = userRepository.findByEmail(token.getUser().getEmail()).get();
            user.setEnabled(true);
            userRepository.save(user);
        }
        else {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email link is invalid or broken!"));
        }
        return ResponseEntity.ok(new MessageResponse("Congratulations! Your account has been activated and email is verified."));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> sendResetEmail(@Valid @RequestBody ResetRequest resetRequest) {
        if (!userRepository.existsByEmail(resetRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: User does not exist!"));
        } else {
            User user1 = userRepository.findByEmail(resetRequest.getEmail()).get();
            ConfirmationToken confirmationToken1 = new ConfirmationToken(user1);
            confirmationTokenRepository.save(confirmationToken1);

            String baseUrl = ServletUriComponentsBuilder.fromRequestUri(servletRequest).replacePath(null).build().toUriString();

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(user1.getEmail());
            mailMessage.setSubject("Reset your SORT password!");
            mailMessage.setFrom("sortedjava@gmail.com");
            mailMessage.setText("To reset your account, please click here : "
                    + baseUrl + "/api/auth/change-password?token="
                    + confirmationToken1.getConfirmationToken()
                    + "&reqId=" + encoder.encode(resetRequest.getPassword()));

            emailSenderService.sendEmail(mailMessage);

            return ResponseEntity.ok(new MessageResponse("User reset password sent successfully!. Please check your email to reset your account."));
        }
    }

    @RequestMapping(value="/change-password", method= {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> addUserToken(@RequestParam("token")String confirmationToken, @RequestParam("reqId")String password) {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
        User user = userRepository.findByEmail(token.getUser().getEmail()).get();
        user.setResetToken(confirmationToken);
        userRepository.save(user);

        if(!user.isEnabled()){
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Please enable the user using signup email sent already before resetting password!"));
        }
        else if(user != null) {
            user.setPassword(password);
            user.setResetToken(null);
            userRepository.save(user);
        }
        else {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Reset Email link is invalid or broken!"));
        }
        return ResponseEntity.ok(new MessageResponse("Congratulations! Your password has been reset."));
    }
}