package com.sort.sortcore.repository;

import com.sort.sortcore.data.Provider;
import com.sort.sortcore.data.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmailAndProvider(String email, Provider provider);

    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndProvider(String email, Provider provider);

    Optional<User> findByResetToken(String Token);
}