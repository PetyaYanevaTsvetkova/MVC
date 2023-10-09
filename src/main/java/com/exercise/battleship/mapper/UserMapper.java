package com.exercise.battleship.mapper;

import com.exercise.battleship.models.User;
import com.exercise.battleship.models.dtos.UserRegistrationDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User userToUserDTO(UserRegistrationDTO registerDTO);
}
