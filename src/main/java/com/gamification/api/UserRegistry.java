package com.gamification.api;

public class UserRegistry {

    private static ThreadLocal<User> currentUser = new ThreadLocal<>();

    public static User getCurrentUser() {
        return currentUser.get();
    }

    public static void setCurrentUser(User u) {
        currentUser.set(u);
    }

    public static void reset() {
        currentUser.remove();
    }

}
