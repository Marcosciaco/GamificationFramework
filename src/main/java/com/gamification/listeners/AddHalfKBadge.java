package com.gamification.listeners;

import com.gamification.api.GamificationListener;
import com.gamification.api.UserRegistry;

public class AddHalfKBadge implements GamificationListener {

    @Override
    public void onConditionTrue() {
        UserRegistry.getCurrentUser().addBadge("Half K");
    }
}
