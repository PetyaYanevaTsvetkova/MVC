package com.exercise.battleship.services;

import com.exercise.battleship.models.Ship;
import com.exercise.battleship.models.dtos.StartBattleDTO;
import com.exercise.battleship.repositories.ShipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BattleService {
    private final ShipRepository shipRepository;

    public void attack(StartBattleDTO attackData) {
        Optional<Ship> attackerOpt = this.shipRepository.findById(attackData.getAttackerId());
        Optional<Ship> defenderOpt = this.shipRepository.findById(attackData.getDefenderId());

        if (attackerOpt.isEmpty() || defenderOpt.isEmpty()) {
            throw new NoSuchElementException();
        }

        Ship attacker = attackerOpt.get();
        Ship defender = defenderOpt.get();

        long newDefenderHealth = defender.getHealth() - attacker.getPower();

        if (newDefenderHealth <= 0) {
            this.shipRepository.deleteById(defender.getId());
        } else {
            defender.setHealth(newDefenderHealth);
            this.shipRepository.save(defender);
        }
    }
}
