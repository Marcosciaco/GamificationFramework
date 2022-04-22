package com.gamification.api;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String userName;
    private int points = 0;
    private List<String> badges = new ArrayList<String>();
    private String lastBadge;
    private int lastPoints;

    public User(String userName) {
        this.userName = userName;
    }

    public void addPoints(int points) {
        this.points += points;
        this.lastPoints = points;
    }

    public void removePoints(int points) {
        this.points -= points;
        this.lastPoints = -points;
    }

    public void addBadge(String badge) {
        this.badges.add(badge);
        this.lastBadge = badge;
    }

    public void removeBadge(String badge) {
        this.badges.remove(badge);
        this.lastBadge = badge;
    }

    public String getUserName() {
        return userName;
    }

    public int getPoints() {
        return points;
    }

    public List<String> getBadges() {
        return badges;
    }

    public String getLastBadge() {
        return lastBadge;
    }

    public int getLastPoints() {
        return lastPoints;
    }

    public void resetChanges() {
        this.lastPoints = 0;
        this.lastBadge = null;
    }

}
