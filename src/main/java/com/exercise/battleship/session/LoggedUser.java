package com.exercise.battleship.session;

import com.exercise.battleship.models.User;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Data
@Component
@SessionScope
public class LoggedUser {
    private Long id;
    private String fullName;

    public void logout() {
        this.id = null;
        this.fullName = null;
    }

    public void login(User user) {
        this.id = user.getId();
        this.fullName = user.getFullName();
    }

}
