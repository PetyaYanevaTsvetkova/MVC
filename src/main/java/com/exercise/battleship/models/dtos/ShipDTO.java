package com.exercise.battleship.models.dtos;

import lombok.Data;

@Data
public class ShipDTO {
    private long id;
    private String name;
    private long health;
    private long power;
}
