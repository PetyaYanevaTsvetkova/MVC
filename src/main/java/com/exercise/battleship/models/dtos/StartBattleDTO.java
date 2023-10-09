package com.exercise.battleship.models.dtos;

import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class StartBattleDTO {
    @Positive
    private long attackerId;

    @Positive
    private long defenderId;
}
