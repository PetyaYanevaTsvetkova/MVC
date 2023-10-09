package com.exercise.battleship.services;

import com.exercise.battleship.mapper.UserMapper;
import com.exercise.battleship.models.User;
import com.exercise.battleship.models.dtos.LoginDTO;
import com.exercise.battleship.models.dtos.UserRegistrationDTO;
import com.exercise.battleship.repositories.UserRepository;
import com.exercise.battleship.session.LoggedUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final LoggedUser userSession;

    public boolean register(UserRegistrationDTO registrationDTO) {

        if (!registrationDTO.getPassword().equals(registrationDTO.getConfirmPassword())) {
            return false;
        }

        Optional<User> userByEmail = this.userRepository.getByEmail(registrationDTO.getEmail());
        if (userByEmail.isPresent()) {
            return false;
        }

        Optional<User> userByUsername = this.userRepository.getByUsername(registrationDTO.getUsername());
        if (userByUsername.isPresent()) {
            return false;
        }


        this.userRepository.save(userMapper.userToUserDTO(registrationDTO));
        return true;
    }

    public boolean login(LoginDTO loginDTO) {
        Optional<User> loggedInUser = this.userRepository
                .getByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword());

        if (loggedInUser.isEmpty()) {
            return false;
        }

        this.userSession.login(loggedInUser.get());

        return true;
    }

    public void logout() {
        this.userSession.logout();
    }

    public boolean isLoggedIn() {
        return this.userSession.getId() != null;
    }

    public long getLoggedUserId() {
        return this.userSession.getId();
    }
}
