package com.shaikha.floodmanagement.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String rescueTeamEmail);

    Optional<User> findByUsernameAndEmail(String username, String email);
}
