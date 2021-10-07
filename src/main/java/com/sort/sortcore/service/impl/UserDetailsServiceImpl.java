package com.sort.sortcore.service.impl;

import com.sort.sortcore.data.User;
import com.sort.sortcore.repository.RoleRepository;
import com.sort.sortcore.repository.UserRepository;
import com.sort.sortcore.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws RuntimeException {
        User user = userRepository.findByEmail(email).get();
        return UserDetailsImpl.build(user);
    }
/*
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        return UserDetailsImpl.build(user);
    }*/
}