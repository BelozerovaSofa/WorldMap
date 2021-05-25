package by.pomidor.helpers;

import by.pomidor.domain.User;

public class Session {
    private static Session INSTANCE;
    private User currentUser;

    private Session() {
    }

    public static Session getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Session();
        }
        return INSTANCE;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
