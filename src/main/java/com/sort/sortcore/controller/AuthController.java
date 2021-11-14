package com.sort.sortcore.controller;

import com.sort.sortcore.Exception.RefreshTokenException;
import com.sort.sortcore.data.*;
import com.sort.sortcore.repository.ConfirmationTokenRepository;
import com.sort.sortcore.repository.RoleRepository;
import com.sort.sortcore.repository.UserRepository;
import com.sort.sortcore.security.jwt.JwtUtils;
import com.sort.sortcore.service.EmailSenderService;
import com.sort.sortcore.service.impl.RefreshTokenService;
import com.sort.sortcore.service.impl.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
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

    @Autowired
    JavaMailSender mailSender;

    @Autowired
    RefreshTokenService refreshTokenService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String jwt = jwtUtils.generateJwtToken(authentication);
        List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList());
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());

        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(),
                userDetails.getUsername(), userDetails.getEmail(), userDetails.getProvider().toString(), roles));
        // return ResponseEntity.ok(new JwtResponse(jwt, refreshToken.getToken(), userDetails.getId(),
        //userDetails.getUsername(), userDetails.getEmail(), userDetails.getProvider().toString(), roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        /*if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }*/

        String tempEmail = signUpRequest.getEmail();
        if ("GOOGLE".equalsIgnoreCase(signUpRequest.getProvider().toUpperCase())) {
            tempEmail = tempEmail + "@google.com";
        } else if ("FACEBOOK".equalsIgnoreCase(signUpRequest.getProvider().toUpperCase())) {
            tempEmail = tempEmail + "@facebook.com";
        } else if ("APPLE".equalsIgnoreCase(signUpRequest.getProvider().toUpperCase())) {
            tempEmail = tempEmail + "@apple.com";
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

        if ("GOOGLE".equalsIgnoreCase(signUpRequest.getProvider().toUpperCase()) || "FACEBOOK".equalsIgnoreCase(signUpRequest.getProvider().toUpperCase()) || "APPLE".equalsIgnoreCase(signUpRequest.getProvider().toUpperCase())) {
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
            try {
                MimeMessage mimeMessage = mailSender.createMimeMessage();
                MimeMessageHelper mailMessage = new MimeMessageHelper(mimeMessage, "utf-8");
                String emailURL = baseUrl + "/api/auth/confirm-account?token=" + confirmationToken.getConfirmationToken();
                String htmlEmailBody = "<p>Thank you for downloading SORT!</p><p>To complete your SORT registration, please click <a href=" + emailURL + ">here</a>.</p><p><strong><u>Please note:</u> We are in Beta and some features in the app might be inactive or not work properly. We will continue to update our app regularly during our Beta phase to give you a seamless experience.</strong></p>\n" +
                        "<p>Get in touch with us at <a href=\"mailto:info@sort.live\" target=\"_blank\">info@sort.live</a>.</p>";
                mailMessage.setTo(user.getEmail());
                mailMessage.setSubject("Complete your SORT registration!");
                mailMessage.setFrom(new InternetAddress("sortedjava@gmail.com", "SORT"));
                mailMessage.setText(htmlEmailBody, true);
                mailSender.send(mimeMessage);
            } catch (MessagingException | UnsupportedEncodingException ex) {
                return new ResponseEntity("Exception occurred during sending confirmation email", HttpStatus.INTERNAL_SERVER_ERROR);
            }
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

    @PostMapping("/refreshToken")
    public ResponseEntity<?> refreshtoken(@Valid @RequestBody RefreshTokenRequest request) {
        String requestRefreshToken = request.getRefreshToken();

        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = jwtUtils.generateJwtToken(authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())));
                    return ResponseEntity.ok(new RefreshTokenResponse(token, requestRefreshToken));
                })
                .orElseThrow(() -> new RefreshTokenException(requestRefreshToken,
                        "Refresh token is not present in database!"));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser(@Valid @RequestBody LogOutRequest logOutRequest) {
        refreshTokenService.deleteByUserId(logOutRequest.getUserId());
        return ResponseEntity.ok(new MessageResponse("Log out successful!"));
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
            try {
                MimeMessage mimeMessage = mailSender.createMimeMessage();
                MimeMessageHelper mailMessage = new MimeMessageHelper(mimeMessage, "utf-8");
                String emailURL = baseUrl + "/api/auth/change-password?token=" + confirmationToken1.getConfirmationToken() + "&reqId=" + encoder.encode(resetRequest.getPassword());
                String htmlEmailBody = "<p>To reset your account, please click <a href=" + emailURL + ">here</a>.</p><p><strong><u>Please note:</u> We are in Beta and some features in the app might be inactive or not work properly. We will continue to update our app regularly during our Beta phase to give you a seamless experience.</strong></p>\n" +
                        "<p>Get in touch with us at <a href=\"mailto:info@sort.live\" target=\"_blank\">info@sort.live</a>.</p>";
                mailMessage.setTo(user1.getEmail());
                mailMessage.setSubject("Reset your SORT password!");
                mailMessage.setFrom(new InternetAddress("sortedjava@gmail.com", "SORT"));
                mailMessage.setText(htmlEmailBody, true);
                mailSender.send(mimeMessage);
            } catch (MessagingException | UnsupportedEncodingException ex) {
                return new ResponseEntity("Exception occurred during sending confirmation email", HttpStatus.INTERNAL_SERVER_ERROR);
            }

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