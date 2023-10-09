package com.exercise.battleship.controllers;

import com.exercise.battleship.models.dtos.StartBattleDTO;
import com.exercise.battleship.services.AuthService;
import com.exercise.battleship.services.BattleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class BattleController {

    private final BattleService battleService;
    private final AuthService authService;

    @PostMapping("/battle")
    public String battle(@Valid StartBattleDTO startBattleDTO,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {

        if (!this.authService.isLoggedIn()) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("startBattleDTO", startBattleDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.startBattleDTO", bindingResult);

            return "redirect:/home";
        }

        System.out.println(startBattleDTO.getAttackerId() +
                " " + startBattleDTO.getDefenderId());

        this.battleService.attack(startBattleDTO);

        return "redirect:/home";
    }
}
