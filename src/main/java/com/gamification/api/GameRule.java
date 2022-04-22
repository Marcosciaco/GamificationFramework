package com.gamification.api;

/**
 * @author marco.sciacovelli
 * The GameRule interface is used to define the rules of the game. It defines what actions should be taken before
 * and after the execution of a task, also in case of a failed execution a special action should be taken.
 */
public interface GameRule {

    /**
     * Defines what should be done before the execution of the task.
     */
    void beforeExecution(Task task);

    /**
     * Defines what should be done after the execution of the task.
     */
    void afterExecution(Task task, Object taskReturn);

    /**
     * Defines what should be done if the task execution fails.
     */
    void afterFailedExecution(Task task, FailedExecutionException e);

}
