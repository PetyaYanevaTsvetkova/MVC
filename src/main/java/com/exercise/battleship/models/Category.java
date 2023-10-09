package com.exercise.battleship.models;

import com.exercise.battleship.models.enums.ShipType;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;

@Entity
@Table(name="categories")
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false, unique = true)
    private ShipType name;

    @Column(columnDefinition = "text")
    private String description;


}
