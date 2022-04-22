package com.gamification.api;

/**
 * This interface should be executed when an associated condition returns true.
 * Some common implementation can be to add a bonus based on the last points wined
 * or to get a badge, however it can also be some action specific for the
 * application. The student should design the interface API.
 */
public interface GamificationListener {

    public void onConditionTrue();

}
