package com.gamification.gameRules;

import com.gamification.api.FailedExecutionException;
import com.gamification.api.GameRule;
import com.gamification.api.Task;
import com.gamification.api.UserRegistry;

public class WinIfSuccessLoseIfFail implements GameRule {

	private int points;
	
	public WinIfSuccessLoseIfFail(int points) {
		this.points = points;
	}

	@Override
	public void beforeExecution(Task t) {
		
	}

	@Override
	public void afterExecution(Task t, Object taskReturn) {
		UserRegistry.getCurrentUser().addPoints(points);
	}

	@Override
	public void afterFailedExecution(Task t, FailedExecutionException e) {
		UserRegistry.getCurrentUser().removePoints(points);
	}

}
