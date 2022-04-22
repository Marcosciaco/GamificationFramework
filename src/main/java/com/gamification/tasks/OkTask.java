package com.gamification.tasks;

import com.gamification.api.FailedExecutionException;
import com.gamification.api.Task;

public class OkTask implements Task {

    private boolean ok;

    public OkTask(boolean ok) {
        this.ok = ok;
    }

    @Override
    public Object execute() throws FailedExecutionException {
        if (ok) {
            return "Ok";
        } else
            throw new FailedExecutionException();
    }
}
