package bg.softuni.pathfinder.service;

import bg.softuni.pathfinder.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class CurrentUser {
    private User user;
    public boolean isLoggedIn(){
        return this.user != null;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
