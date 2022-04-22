package com.gamification.conditions;

import com.gamification.api.GameCondition;
import com.gamification.api.UserRegistry;

public class HalfKPointsCondition implements GameCondition {

    @Override
    public boolean isSatisfied() {
        return UserRegistry.getCurrentUser().getPoints() >= 500 && !UserRegistry.getCurrentUser().getBadges().contains("Half K");
    }
}
