package com.exercise.battleship.services;

import com.exercise.battleship.mapper.ShipMapper;
import com.exercise.battleship.models.Category;
import com.exercise.battleship.models.Ship;
import com.exercise.battleship.models.dtos.ShipDTO;
import com.exercise.battleship.models.enums.ShipType;
import com.exercise.battleship.models.User;
import com.exercise.battleship.models.dtos.CreateShipDTO;
import com.exercise.battleship.repositories.CategoryRepository;
import com.exercise.battleship.repositories.ShipRepository;
import com.exercise.battleship.repositories.UserRepository;
import com.exercise.battleship.session.LoggedUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShipService {
    private final ShipRepository shipRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final LoggedUser loggedUser;
    private final ShipMapper shipMapper;

    public boolean create(CreateShipDTO createShipDTO) {
        Optional<Ship> shipByNameOpt =
                this.shipRepository.findByName(createShipDTO.getName());

        if (shipByNameOpt.isPresent()) {
            return false;
        }

        Ship ship = new Ship();
        ship.setName(createShipDTO.getName());
        ship.setPower(createShipDTO.getPower());
        ship.setHealth(createShipDTO.getHealth());
        Optional<User> userOpt = userRepository.findById(loggedUser.getId());
        userOpt.ifPresent(ship::setUser);
        ship.setCreated(createShipDTO.getCreated());

        ShipType type = switch (createShipDTO.getCategory()) {
            case 0 -> ShipType.BATTLE;
            case 1 -> ShipType.CARGO;
            case 2 -> ShipType.PATROL;
            default -> ShipType.BATTLE;
        };
        Optional<Category> categoryOpt = categoryRepository.getByName(type);
        categoryOpt.ifPresent(ship::setCategory);

        shipRepository.save(ship);

        return true;
    }

    public List<ShipDTO> getShipsOwnedBy(Long ownerId) {
        return this.shipRepository.findByUserId(ownerId)
                .stream()
                .map(shipMapper::toShipDTO)
                .collect(Collectors.toList());
    }

    public List<ShipDTO> getShipsNotOwnedBy(Long ownerId) {
        return this.shipRepository.findByUserIdNot(ownerId)
                .stream()
                .map(shipMapper::toShipDTO)
                .collect(Collectors.toList());
    }

    public List<ShipDTO> getAllSorted() {
       return this.shipRepository.findByOrderByName()
                .stream()
                .map(shipMapper::toShipDTO)
                .collect(Collectors.toList());
    }

}
