package com.exercise.battleship.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "ships")
@Data
public class Ship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private long health;

    private long power;

    private LocalDate created;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne
    private Category category;

}
