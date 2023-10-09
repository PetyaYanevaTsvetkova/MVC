package com.exercise.battleship.repositories;

import com.exercise.battleship.models.Category;
import com.exercise.battleship.models.enums.ShipType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> getByName(ShipType name);

}
