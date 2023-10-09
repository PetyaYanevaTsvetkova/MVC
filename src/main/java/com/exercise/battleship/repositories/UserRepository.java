package com.exercise.battleship.repositories;

import com.exercise.battleship.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> getByEmail(String email);

    Optional<User> getByUsername(String username);

    Optional<User> getByUsernameAndPassword(String username, String password);
}
