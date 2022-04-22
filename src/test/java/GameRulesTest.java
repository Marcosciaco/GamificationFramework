import com.gamification.api.*;
import com.gamification.conditions.HalfKPointsCondition;
import com.gamification.gameRules.WinIfSuccessLoseIfFail;
import com.gamification.listeners.AddHalfKBadge;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.gamification.tasks.OkTask;

import static org.junit.jupiter.api.Assertions.*;

public class GameRulesTest {

    @AfterEach
    void removeUser() {
        UserRegistry.reset();
    }

    @AfterEach
    void cleanRules() {
        GamificationFacade.getInstance().cleanRules();
    }

    @BeforeEach
    void addUser() {
        User u = new User("admin");
        u.addPoints(100);
        UserRegistry.setCurrentUser(u);
    }

    @Test
    void testWinPointsWhenExecuteSuccessfully() throws FailedExecutionException {
        GamificationFacade gf = GamificationFacade.getInstance();
        gf.setGameRule(new WinIfSuccessLoseIfFail(20), OkTask.class);

        Task t = new OkTask(true);
        gf.execute(t);

        assertEquals(120, UserRegistry.getCurrentUser().getPoints());
    }

    @Test
    void testLosePointsWhenExecuteFailes() {
        try {
            ConfigurationFileLoader.loadConfiguration("src/main/resources/config.json");
            GamificationFacade gf = GamificationFacade.getInstance();
            Task t = new OkTask(true);
            gf.setCallback(new HalfKPointsCondition(), new AddHalfKBadge());
            gf.execute(t);

            assertEquals(700, UserRegistry.getCurrentUser().getPoints());
            assertTrue(UserRegistry.getCurrentUser().getBadges().contains("Half K"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
