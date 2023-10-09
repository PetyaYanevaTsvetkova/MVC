package com.exercise.battleship;

import com.exercise.battleship.models.Category;
import com.exercise.battleship.models.enums.ShipType;
import com.exercise.battleship.models.User;
import com.exercise.battleship.repositories.CategoryRepository;
import com.exercise.battleship.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@AllArgsConstructor
public class Seeder implements CommandLineRunner {
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        seedCategories();
        seedUsers();


    }

    private void seedUsers() {
        User user = new User();
        user.setEmail("pesho@gaim.com");
        user.setUsername("pesho1");
        user.setFullName("Pesho Peshev");
        user.setPassword("123");
        userRepository.save(user);
    }

    private void seedCategories() {
        if (this.categoryRepository.count() == 0) {
            List<Category> categories = Arrays.stream(ShipType.values())
                    .map(shipType -> {
                        Category category = new Category();
                        category.setName(shipType);
                        return category;
                    }).toList();
            this.categoryRepository.saveAll(categories);
        }
    }
}
