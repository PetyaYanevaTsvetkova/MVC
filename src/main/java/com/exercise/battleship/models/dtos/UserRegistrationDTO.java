package com.exercise.battleship.models.dtos;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserRegistrationDTO {

    @Size(min = 3, max = 10)
    @NotBlank
    private String username;

    @Size(min = 5, max = 20)
    @NotNull
    private String fullName;

    @Email
    @NotBlank
    private String email;

    @Size(min = 3)
    @NotBlank
    private String password;

    private String confirmPassword;


}
