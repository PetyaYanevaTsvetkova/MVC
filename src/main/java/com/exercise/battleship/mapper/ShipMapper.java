package com.exercise.battleship.mapper;

import com.exercise.battleship.models.Ship;
import com.exercise.battleship.models.dtos.ShipDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ShipMapper {

    ShipDTO toShipDTO(Ship ship);
}
