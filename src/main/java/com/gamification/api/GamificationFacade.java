package com.gamification.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author marco.sciacovelli
 * The GamificationFacade is the main facade of the application. Here GameRules can be set and linked to tasks.
 * In addition, the GamificationFacade can be used to execute tasks and to get the result.
 */
public class GamificationFacade {

    private static GamificationFacade instance;
    private static final Map<GameCondition, List<GamificationListener>> gamificationListeners = new HashMap<>();

    /**
     * @return the instance of the GamificationFacade
     */
    public static synchronized GamificationFacade getInstance() {
        if(instance == null) {
            instance = new GamificationFacade();
        }
        return instance;
    }

    private GamificationFacade() {}

    private Map<Class<? extends Task>, List<GameRule>> rules = new HashMap<>();

    /**
     *
     * @param rule the rule to be added.
     * @param taskClass the task to be linked to the rule.
     */
    public void setGameRule(GameRule rule, Class<? extends Task> taskClass) {
        List<GameRule> rulesList;
        if(rules.containsKey(taskClass)) {
            rulesList = rules.get(taskClass);
        } else {
            rulesList = new ArrayList<>();
            rules.put(taskClass, rulesList);
        }
        rulesList.add(rule);
    }

    /**
     *
     * @param task the task to be executed.
     * @return the result of the execution.
     * @throws FailedExecutionException if the task cannot be executed.
     */
    public Object execute(Task task) throws FailedExecutionException {

        Object taskReturn;

        List<GameRule> rulesList = rules.get(task.getClass());
        rulesList.forEach(rule -> rule.beforeExecution(task));

        try {
            taskReturn = task.execute();
            rulesList.forEach(rule -> rule.afterExecution(task, taskReturn));
            executeListener();
        } catch (FailedExecutionException e) {
            rulesList.forEach(rule -> rule.afterFailedExecution(task, e));
            executeListener();
            throw e;
        }
        return taskReturn;
    }

    private void executeListener() {
        gamificationListeners.forEach((condition, listeners) -> {
            if(condition.isSatisfied()) {
                listeners.forEach(GamificationListener::onConditionTrue);
            }
        });
    }

    public void setCallback(GameCondition condition, GamificationListener listener) {
        List<GamificationListener> listeners;
        if(gamificationListeners.containsKey(condition)) {
            listeners = gamificationListeners.get(condition);
        }
        else {
            listeners = new ArrayList<>();
            gamificationListeners.put(condition, listeners);
        }
        listeners.add(listener);
    }

    public void cleanRules() {
        rules = new HashMap<>();
    }

}
