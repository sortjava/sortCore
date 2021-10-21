package com.sort.sortcore.controller;

import com.sort.sortcore.data.*;
import com.sort.sortcore.repository.ConfirmationTokenRepository;
import com.sort.sortcore.repository.RoleRepository;
import com.sort.sortcore.repository.UserRepository;
import com.sort.sortcore.security.jwt.JwtUtils;
import com.sort.sortcore.service.EmailSenderService;
import com.sort.sortcore.service.impl.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), userDetails.getProvider().toString(), roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        /*if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }*/

        String tempEmail = signUpRequest.getEmail();
        if ("GOOGLE".equalsIgnoreCase(signUpRequest.getProvider().toUpperCase())) {
            tempEmail = tempEmail + "@gmail.com";
        } else if ("FACEBOOK".equalsIgnoreCase(signUpRequest.getProvider().toUpperCase())) {
            tempEmail = tempEmail + "@facebook.com";
        } else {
        }

        if (("LOCAL".equalsIgnoreCase(signUpRequest.getProvider().toUpperCase())) && (userRepository.existsByEmailAndProvider(tempEmail, Provider.valueOf(signUpRequest.getProvider().toUpperCase())))) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(), tempEmail, encoder.encode(signUpRequest.getPassword()), false, null, Provider.valueOf(signUpRequest.getProvider().toUpperCase()));
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

        Profile profile = new Profile();
        profile.setEmail(user.getEmail());
        profile.setProvider(Provider.valueOf(signUpRequest.getProvider()));
        user.setProfile(profile);

        if (signUpRequest.getProvider().toUpperCase().equalsIgnoreCase("GOOGLE") || signUpRequest.getProvider().toUpperCase().equalsIgnoreCase("FACEBOOK")) {
            if (!(userRepository.existsByEmailAndProvider(tempEmail, Provider.valueOf(signUpRequest.getProvider().toUpperCase())))) {
                user.setEnabled(true);
                userRepository.save(user);
            }
            LoginRequest loginRequest1 = new LoginRequest();
            loginRequest1.setEmail(user.getEmail());
            loginRequest1.setProvider(signUpRequest.getProvider());
            loginRequest1.setPassword(signUpRequest.getPassword());
            return this.authenticateUser(loginRequest1);
        } else {
            userRepository.save(user);
            ConfirmationToken confirmationToken = new ConfirmationToken(user);
            confirmationTokenRepository.save(confirmationToken);
            String baseUrl = ServletUriComponentsBuilder.fromRequestUri(servletRequest).replacePath(null).build().toUriString();
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(user.getEmail());
            mailMessage.setSubject("Complete your SORT registration!");
            mailMessage.setFrom("sortedjava@gmail.com");
            mailMessage.setText("To confirm your account, please click here : " + baseUrl + "/api/auth/confirm-account?token=" + confirmationToken.getConfirmationToken());
            emailSenderService.sendEmail(mailMessage);
            return ResponseEntity.ok(new MessageResponse("User registered successfully!. Please check your email to verify your account."));
        }
    }

    /*@RequestMapping(value = "/confirm-account", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> confirmUserAccount(@RequestParam("token") String confirmationToken) {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if (token != null) {
            User user = userRepository.findByEmail(token.getUser().getEmail()).get();
            user.setEnabled(true);
            userRepository.save(user);
        } else {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email link is invalid or broken!"));
        }
        return ResponseEntity.ok(new MessageResponse("Congratulations! Your account has been activated and email is verified."));
    }*/

    @RequestMapping(value = "/confirm-account", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView confirmUserAccount(Model model, @RequestParam("token") String confirmationToken) {
        try {
            ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
            if (token != null) {
                User user = userRepository.findByEmailAndProvider(token.getUser().getEmail(), token.getUser().getProvider()).get();
                user.setEnabled(true);
                userRepository.save(user);
            } else {
                return new ModelAndView("SignUpErrorPage");
            }
        } catch (Exception e) {
            return new ModelAndView("SignUpErrorPage");
        }
        return new ModelAndView("SignUpConfirmation");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> sendResetEmail(@Valid @RequestBody ResetRequest resetRequest) {
        if (!userRepository.existsByEmailAndProvider(resetRequest.getEmail(), Provider.valueOf(resetRequest.getProvider().toUpperCase()))) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: User does not exist!"));
        } else {
            User user1 = userRepository.findByEmailAndProvider(resetRequest.getEmail(), Provider.valueOf(resetRequest.getProvider().toUpperCase())).get();
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

    /*@RequestMapping(value = "/change-password", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> addUserToken(@RequestParam("token") String confirmationToken, @RequestParam("reqId") String password) {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
        User user = userRepository.findByEmail(token.getUser().getEmail()).get();
        user.setResetToken(confirmationToken);
        userRepository.save(user);

        if (!user.isEnabled()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Please enable the user using signup email sent already before resetting password!"));
        } else if (user != null) {
            user.setPassword(password);
            user.setResetToken(null);
            userRepository.save(user);
        } else {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Reset Email link is invalid or broken!"));
        }
        return ResponseEntity.ok(new MessageResponse("Congratulations! Your password has been reset."));
    }*/

    @RequestMapping(value = "/change-password", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView addUserToken(Model model, @RequestParam("token") String confirmationToken, @RequestParam("reqId") String password) {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
        User user = userRepository.findByEmailAndProvider(token.getUser().getEmail(), token.getUser().getProvider()).get();
        user.setResetToken(confirmationToken);
        userRepository.save(user);

        if (!user.isEnabled()) {
            return new ModelAndView("ResetErrorPage");
        } else if (user != null) {
            user.setPassword(password);
            user.setResetToken(null);
            userRepository.save(user);
        } else {
            return new ModelAndView("SignUpErrorPage");
        }
        return new ModelAndView("ResetConfirmation");
    }
}